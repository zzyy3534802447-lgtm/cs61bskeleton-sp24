import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ArrayDeque61B <T>implements Deque61B<T>{
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

    static void main() {
            ArrayDeque61B jj=new ArrayDeque61B();
           //
         jj.addFirst(1);
            jj.addLast(2);
            jj.addLast(3);
            jj.addLast(4);
            jj.addLast(5);
            jj.addLast(6);
           jj.addLast(7);
           jj.addLast(8);
           jj.addLast(9);
        jj.addLast(10);
            jj.addFirst(99);
            System.out.println(jj.toList());
            System.out.println(jj.isEmpty());
            System.out.println(jj.size());
            System.out.println(jj.get(78));
    }
}
