package deque;

import org.junit.platform.commons.util.ToStringBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    Array DeList;
    private class Array{
        T[] array;
        int Size;
        int j=8;
        private Array(){
            array=(T[])new Object[j];
            array[0]=null;
            Size=0;
        }
        private Array(int i){
            array=(T[])new Object[j*2];
            array[0]=null;
            j*=2;

        }
    }
    public ArrayDeque61B() {
        DeList=new Array();
    }
    private void addlength(){
        Array p=DeList;
        DeList=new Array(1);
        DeList.Size= p.Size;
        System.arraycopy(p.array,0,DeList.array,0,p.array.length);
    }

    @Override
    public void addFirst(T x) {
        if(DeList.Size==0){DeList.array[1]=x;
            DeList.Size++;}
        else{
            DeList.Size++;
            if(DeList.Size<DeList.array.length-1){
                Array p=DeList;
                //addlength();
                System.arraycopy(p.array,1,DeList.array,2,DeList.Size);
                // DeList.array[0]=null;
                DeList.array[1]=x;
            }
            else {
                Array p=DeList;
                addlength();
                System.arraycopy(p.array,1,DeList.array,2,p.Size);
                // DeList.array[0]=null;
                DeList.array[1]=x;
            }}
    }

    @Override
    public void addLast(T x) {
        DeList.Size++;
        if(DeList.Size<DeList.array.length){
            DeList.array[DeList.Size]=x;
        }
        else{addlength();
            DeList.array[DeList.Size]=x;

        }
    }

    @Override
    public List<T> toList() {
        ArrayList<T> p=new ArrayList<T>();
        for(int i=0;i<=DeList.Size;i++){
            p.add(DeList.array[i]);
        }
        return p;
    }

    @Override
    public boolean isEmpty() {
        if(DeList.Size==0)return true;
        else return false;
    }

    @Override
    public int size() {
        return DeList.Size;
    }

    @Override
    public T removeFirst() {
        T j=DeList.array[1];
        Array p=DeList;
        System.arraycopy(p.array,2,DeList.array,1,p.Size-1);
        return j;
    }

    @Override
    public T removeLast() {
        T j=DeList.array[DeList.Size];
        DeList.array[DeList.Size]=null;
        return j;
    }

    @Override
    public T get(int index) {
        if(index>0 && index<=DeList.Size){
            T j=DeList.array[index];
            return j;}
        else return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    @Override
    public Iterator<T> iterator() {

        return new observer<T>();
    }

    private class observer<T> implements Iterator<T>
    {
        int Observer=1;

        @Override
        public boolean hasNext() {
            if(Observer<=DeList.Size) {
                return true;}
            else return false;
       }
//public boolean hasNext(){
//    if(DeList.array[Observer]!=null) return true;//如果该元素非null返回true
//    else return false;

        @Override
        public T next() {
                T n= (T) DeList.array[Observer];
                Observer++;
                return n;
        }
    }
    @Override
    public boolean equals(Object x){
        if(x instanceof ArrayDeque61B<?> otherArray ){
            if(DeList.Size==otherArray.size()){
                int cnt=0;
                for(T j:DeList.array){
                    if(j==otherArray.get(cnt)){
                        cnt++;
                        if(cnt==DeList.array.length+1) return true;
                    }
                    return false;
                }
            }
            else  return false;
        }
        else return false;
        return false;
    }
    @Override
    public String ToString(){
        StringBuilder x=new StringBuilder();
        x.append("[");
        int n=1;
        for(T j:DeList.array){
            if(j==null)continue;
            if(n<=DeList.Size){
                n++;
                x.append(j);}
            if(n<=size())x.append(",");
        }
        x.append("]");
        return x.toString();}

//public String ToString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("[");
//
//    // 使用传统的 for 循环，确保只遍历有效元素
//    for (int i = 0; i < DeList.Size; i++) {
//        sb.append(DeList.array[i]);
//        if (i < DeList.Size - 1) {
//            sb.append(", ");
//        }
//    }
//
//    sb.append("]");
//    return sb.toString();
//}

    static void main() {
        ArrayDeque61B j=new ArrayDeque61B();
        j.addFirst(1);
        j.addLast(12);
        j.addFirst("jj");
        System.out.println(j.ToString());
        System.out.println(j.size());
    }
}
