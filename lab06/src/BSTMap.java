import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    BSTtreeNode root;
    int Size=0;


    public class BSTtreeNode{
        K key=null;
        V vlu=null;
        BSTtreeNode left=null;
        BSTtreeNode right=null;
    }
    public BSTMap(){
        root=null;
    }
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null");
        }
        root=insert(key,value,root);
                                        //        1. new 永远意味着“新地址”
                                        //        无论变量叫什么名字（root、node、myString、temp），只要代码里出现了 new 类名()：
                                        //        Java 虚拟机（JVM） 都会立刻在内存里圈出一块从未被使用过的空地。
                                        //        生成 这块新空地的地址（比如 0x999）。
                                        //        返回 这个新地址。
                                    //为什么要加=？
                                    //java是值传递，你传入的root是一个副本（地址），假如你把一个null的地方new了一个新的东西（new相当于向系统申请一块内存），所以root的地址就变了，
                                    //如果你不加=，外部的root本体不会变化
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null");
        }
        BSTtreeNode p=find(key,root);
        if(p==null) return null;
        return  p.vlu;



    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null");
        }
//        if(key.compareTo(find(key,root).key)==0) return true;
//        return false;
        // 关键修复：不要去比较 key，直接看 find 的结果是不是 null
        return find(key, root) != null;
    }

    @Override
    public int size() {
        return Size;
    }

    @Override
    public void clear() {
            Size=0;
            root=null;
    }

    @Override
    public Set<K> keySet() {
        //return Set.of();
        HashSet keys=new HashSet<>();
        Iot(root,keys);
        return keys;

    }

    @Override
    public V remove(K key) {
        //return null;
        if (key == null) {
            throw new IllegalArgumentException("Keys cannot be null");
        }
        V cnt=get(key);
        if(cnt==null) return null;
        root=removeNode(key,root);
        Size--;
        return cnt;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
    private BSTtreeNode insert(K k,V v,BSTtreeNode root){
        if(root==null && k!=null) {
            Size++;
            root=new BSTtreeNode();
            root.key=k;
            root.vlu=v;}
        int cmp = k.compareTo(root.key);
        if(cmp>0) {
            root.right=insert(k,v,root.right);}
        else if(cmp<0) {
            root.left=insert(k,v,root.left);}
        else {
            root.vlu=v;}
        return root;
        }

    private BSTtreeNode find(K k,BSTtreeNode root){
        if(root==null) return null;
        int cmp=k.compareTo((K) root.key);
        if(cmp==0) return root;
        else if(cmp>0) return find(k,root.right);
        else  return find(k,root.left);
    }
    private BSTtreeNode removeNode(K k,BSTtreeNode root){
                if(root==null) return null;
                int cmp=k.compareTo(root.key);
                if(cmp>0){
                    root.right=removeNode(k,root.right);
                } else if (cmp<0) {
                    root.left=removeNode(k,root.left);
                } else{
                    if(root.left==null) return root.right;
                    if(root.right==null) return root.left;
                    BSTtreeNode leftmin=min(k,root.right);
                    root.key=leftmin.key;
                    root.vlu=leftmin.vlu;
                    root.right=removeNode(leftmin.key,root.right);
        }
        return root;
    }
    private BSTtreeNode min(K k,BSTtreeNode node){
        if(node.key==k) return node;
        return min(k,node.left);
    }
    private void Iot(BSTtreeNode root,HashSet k){
        if(root==null) return;
        Iot(root.left,k);
        k.add(root.key);
        Iot(root.right,k);
    }
}
