package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    NGramMap p;
    public HistoryHandler(NGramMap map){
    p=map;}

    @Override
    public String handle(NgordnetQuery q) {
        //parabola.put(x,y)
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int n=words.size();
        ArrayList<TimeSeries>word = new ArrayList<>();
        ArrayList<String> name=new ArrayList<>();
        for(int i=0;i<n;i++){
            TimeSeries c=new TimeSeries();
            TimeSeries cmp=p.weightHistory(words.get(1),startYear,endYear);
            name.add(words.get(i));
            word.add(cmp);

//            List<Double> wordsOfweight=p.weightHistory(words.get(i),startYear,endYear).data();
//            int a=0;
//            for(int j=startYear;j<endYear;j++){
//                c.put(j,wordsOfweight.get(a));
//                a++;
//            }
//            word.add(c);
        }
        XYChart chart = Plotter.generateTimeSeriesChart(name,word);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;

    }
}
