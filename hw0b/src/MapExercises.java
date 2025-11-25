import java.util.*;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character,Integer> map=new HashMap<>();
         map.put('a',1);
         map.put('b',2);

         return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer,Integer> squares=new HashMap<>() ;
        squares.put(1,1);
        squares.put(3,9);
        squares.put(6,36);
        squares.put(7,49);
        return squares;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String,Integer> Ch=new HashMap<>();
        for(int i=0;i<words.size();i++){
            Ch.put(words.get(i),Ch.getOrDefault(words.get(i),0)+1);
        }
        return Ch;
    }


public static void main(String[] args) {
    List<String> words=new ArrayList<>(Arrays.asList("banana","dog","apple","cat","banana","fish","duck","apple"));
    HashMap<String,Integer> jj=new HashMap<>();
    jj= (HashMap<String, Integer>) countWords(words);

    System.out.println(jj.get("dog"));
}
}
//ovoo