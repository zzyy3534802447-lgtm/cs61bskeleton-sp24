import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int Sum=0;
        for(int i=0;i<L.size();i++){
            int x=L.get(i);
            Sum+=x;
        }
        return Sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> evenlist=new ArrayList<>();
        for(Integer i:L){
            if(i%2==0){evenlist.add(i);}
        }
        return evenlist;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> commonlist=new ArrayList<>();
        for(Integer i:L1){
            for(Integer k:L2){
                if(i.equals(k)){
                    commonlist.add(i);
                }
            }
        }
        return commonlist;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int n=0;
        //String word= new String();
        for(String i:words){
            //word=i;
            for(int j=0;j<i.length();j++){
                if(i.charAt(j)==c){n++;}
            }
        }
        return n;
    }
}
