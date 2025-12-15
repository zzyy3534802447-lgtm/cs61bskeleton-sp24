import edu.princeton.cs.algs4.WeightedQuickUnionUF;

//public Percolation(int N)                // 创建一个 N×N 的网格，所有位置最初都处于阻塞状态
//public void open(int row, int col)       // 如果位置 (row, col) 尚未打开，则打开该位置
//public boolean isOpen(int row, int col)  // 位置 (row, col) 是否已打开？
//public boolean isFull(int row, int col)  // 位置 (row, col) 是否已充满？
//public int numberOfOpenSites()           // 已打开位置的数量
//public boolean percolates()              // 系统是否已渗透？
//public class WeightedQuickUnionUF {
//    public WeightedQuickUnionUF(int n)      // 创建一个包含 n 个元素的并查集
//    public int count()                      // 返回不相交集合的数量
//    public int find(int p)                  // 返回元素 p 所在集合的根节点
//    public boolean connected(int p, int q)  // 判断元素 p 和 q 是否在同一个集合中
//    public void union(int p, int q)         // 将元素 p 和 q 所在的集合合并
//}
public class Percolation extends WeightedQuickUnionUF {
    // TODO: Add any necessary instance variables.
    int open=0;
    set Unionset;
    int cnt;
    public class set  {
       // boolean [] setOfwart;
        boolean [] setOfstate;
        public set(int N){
            //setOfwart=new boolean[N*N];
            setOfstate=new boolean[N*N];
        }
    }
    public Percolation(int N){
        super(N*N+2);
        cnt=N;
        Unionset=new set(N);
        for(int i=0;i<N*N;i++){
            Unionset.setOfstate[i]=false;
            //Unionset.setOfwart[i]=false;
        }
    }
    public void open(int row, int col) {
        Unionset.setOfstate[row*cnt+col]=true;
        if(row==0) {//Unionset.setOfwart[col]=true;
        union(col,cnt*cnt);}
        if(row==cnt-1) union((cnt-1)*cnt+col,cnt*cnt+1);
        open++;
        if (row > 0 && isOpen(row - 1, col)) {  // 上面
            merge(row * cnt + col, (row - 1) * cnt + col);
        }
        if (row < cnt - 1 && isOpen(row + 1, col)) {  // 下面
            merge(row * cnt + col, (row + 1) * cnt + col);
        }
        if (col > 0 && isOpen(row, col - 1)) {  // 左边
            merge(row * cnt + col, row * cnt + (col - 1));
        }
        if (col < cnt - 1 && isOpen(row, col + 1)) {  // 右边
            merge(row * cnt + col, row * cnt + (col + 1));
        }

    }
    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        if(Unionset.setOfstate[row*cnt+col]) return true;
        else return false;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
       // if(Unionset.setOfwart[row*cnt+col]) return true;
       //else return false;
//        int idx = row * cnt + col;
//        // 使用 find() 来获取根节点，并判断是否和顶行相连
//        return isOpen(row, col) && Unionset.setOfwart[find(idx)];
        if(connected(row*cnt+col,cnt*cnt)) return true;
        else return false;
    }

    public int numberOfOpenSites() {
        return open;
    }

    public boolean percolates() {
        // TODO: Fill in this method
//        if(judegepercolates()) return true;
//        else return false;
        if(connected(cnt*cnt,cnt*cnt+1)) return true;
        else return false;
    }
    public boolean judegemerge(int row,int col){
        if(isOpen(row,col)) return true;
        else return false;
    }
    public void merge(int p,int q){
        if(judegemerge(p/cnt,p%cnt) && judegemerge(q/cnt,q%cnt)){
            if(p%cnt==q%cnt&&(p/cnt-q/cnt==1 || p/cnt-q/cnt==-1)||((p-q==1 ||p-q==-1)&&(p%cnt!=cnt-1 && q%cnt!=0)||(q%cnt!=cnt-1 && p%cnt!=0)))
                union(p,q);
        //if(isFull(p/cnt,p%cnt)) {Unionset.setOfwart[q]=true;}
        //if(isFull(q/cnt,q%cnt)) {Unionset.setOfwart[p]=true;}
        }
    }
//    public boolean judegepercolates(){
//        for(int i=0;i<cnt;i++){
//           // if(Unionset.setOfwart[i])
//            if(isFull(0,i)){
//            int x=find(i);
//                for(int j=0;j<cnt;j++){
//                if(isOpen(cnt - 1, j) && x==find(cnt*(cnt-1)+j))return true;
//                }
//            }
//        }
//        return false;
//    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
