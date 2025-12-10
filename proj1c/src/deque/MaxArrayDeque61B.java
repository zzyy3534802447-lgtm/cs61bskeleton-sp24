package deque;


import java.util.Comparator;


public class MaxArrayDeque61B<T>  extends ArrayDeque61B<T>{
    Comparator<T> comparator;
    public MaxArrayDeque61B(Comparator<T> c){
        super();
    comparator=c;
    }
    public T max(){
        if(isEmpty())return null;
        T maxtiem=get(1);
        for(int i=2;i<=size();i++){
            if(comparator.compare(get(i),maxtiem)>0) maxtiem=get(i);
        }
        return maxtiem;
    }
    public T max(Comparator<T> c){
        if(isEmpty()) return null;
        T maxitem=get(1);
        for(int i=2;i<=size();i++){
            if(c.compare(get(i),maxitem)>0) maxitem=get(i);
        }
        return maxitem;
    }
}
