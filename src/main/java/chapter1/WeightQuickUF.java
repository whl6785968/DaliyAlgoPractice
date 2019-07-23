package chapter1;

public class WeightQuickUF {
    private int[] id;
    private int[] size;
    private int count;

    public WeightQuickUF(int N){
        count = N;
        id = new int[N];
        size = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
            size[i] = 1;
        }
    }

    public int find(int p){
        if(p!=id[p]) p = id[p];
        return p;
    }

    public void union(int p,int q){
        int i = find(p);
        int j = find(q);
        if(i == j) return;

        if(size[i]<size[j]){
            id[i]=j;
            size[j] += size[i];
        }
        else {
            id[j] = i;
            size[i] += size[j];
        }
        count--;
    }
}
