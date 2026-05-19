package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import main.Graph;
import main.WordNet;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;
import java.util.stream.Collectors;

import static browser.NgordnetQueryType.ANCESTORS;
import static browser.NgordnetQueryType.HYPONYMS;

public class HyponymsHandler extends NgordnetQueryHandler {
    WordNet WordNet1;
    Graph Graph1;
    NGramMap NGramMap1;
    TimeSeries TimeSeries1;
    public HyponymsHandler(String synsetFile, String hyponymFile,String wordFile,String countFile){
        WordNet1=new WordNet(synsetFile);
        Graph1=new Graph(hyponymFile);
        NGramMap1 = new NGramMap(wordFile, countFile);

    }
    HashSet<Integer> ids;

    public Set<String> helpOfhandle(String word) {
        Graph1.resOfId.clear();
       ids=WordNet1.ConvertOfWToM(word);
       for(Integer id :ids){
            Graph1.Find(id);
       }
       return WordNet1.GetResult(Graph1.resOfId);
    }

    public Set<String> helpOfhandleReverse(String word) {
        Graph1.resOfId.clear();
        ids=WordNet1.ConvertOfWToM(word);
        for(Integer id :ids){
            Graph1.ReverseFind(id);
        }
        return WordNet1.GetResult(Graph1.resOfId);
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words=q.words();
        int endYear=q.endYear();
        int startYear=q.startYear();
        int k=q.k();
        NgordnetQueryType body=q.ngordnetQueryType();
        Set<String> res;
        if(body.equals(HYPONYMS)){
        res=helpOfhandle(words.getFirst());
        if(words.size()>1) {
            for (int i=1;i<words.size();i++) {
                res.retainAll(helpOfhandle(words.get(i)));
            }
        }
        }else{ res=helpOfhandleReverse(words.getFirst());
            if(words.size()>1) {
                for (int i=1;i<words.size();i++) {
                    res.retainAll(helpOfhandleReverse(words.get(i)));
                }
            }

        }
        List<String> list=new ArrayList<>();
        list.addAll(res);
        Collections.sort(list);
        HashMap<String,Double> mindOfresult=new HashMap<>();

        for(String word :list){
            double val=0;
            List<Double> wordOfweight=NGramMap1.weightHistory(word,startYear,endYear).data();
            for(Double wordOfval :wordOfweight){
                val+=wordOfval;
            }
            mindOfresult.put(word,val);
        }
        //把map降序排列
        LinkedHashMap<String, Double> sortedMap = mindOfresult.entrySet().stream()
                //.sorted(Map.Entry.comparingByValue())
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (newValue, oldValue) -> oldValue,
                        LinkedHashMap::new // 必须使用 LinkedHashMap 保持顺序
                ));
        if(k!=0){
            Map<String, Double> sortedAndCopiedMap = sortedMap.entrySet().stream()
                    // 按照 Map 的 value 进行排序 (升序)
                    //.sorted(Map.Entry.comparingByValue())
                    // 取出前 k 个
                    .limit(k)
                    // 收集到 LinkedHashMap 中以保持顺序
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e2, e1) -> e1,
                            LinkedHashMap::new
                    ));
            Set<String> resOfwords=sortedAndCopiedMap.keySet();
            List<String> list2=new ArrayList<>();
            list2.addAll(sortedAndCopiedMap.keySet());
            Collections.sort(list2);
            return list2.toString();
        }else{return  list.toString();}
    }
}
