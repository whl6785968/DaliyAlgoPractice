package chapter1;

import edu.princeton.cs.algs4.StdIn;

public class UF {
    private int[] id;
    private int count;

    public UF(int N){
        count = N;
        id = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
        }
    }

    public boolean connection(int p,int q){
        return find(p) == find(q);
    }

    public int count(){
        return count;
    }

    public int find(int p){
        return id[p];
    }

    public void union(int p,int q){
        int pID = find(p);
        int qID = find(q);
        if(pID == qID){
            return;
        }
        for(int i=0;i<id.length;i++){
            if(id[i] == pID){
                id[i] = qID;
            }
        }
        count--;
    }

    //find root node.
    //explain:it must become a children node in a tree if p != id[p]
    //so we hope fint its parent,we continue parent of parent if current parent is not root.
    //until root.
    public int quickFind(int p){
        while (p != id[p]) p = id[p];
        return p;
    }

    public void quickUnion(int p,int q){
        int pRoot = quickFind(p);
        int qRoot = quickFind(q);
        if(pRoot == qRoot) return;

        id[pRoot] = qRoot;
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf= new UF(N);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();

            if(uf.connection(p,q)) continue;

            uf.union(p,q);
            System.out.println(p + ":" + q);
        }
        System.out.println(uf.count() + "components");
    }
}
