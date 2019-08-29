package chapter4;

import chapter1.Bag;

public class DiGraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public DiGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int i=0;i<V;i++){
            adj[i] = new Bag<Integer>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEDGE(int v,int w){
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public DiGraph reverse(){
        DiGraph R = new DiGraph(V);
        for(int i=0;i<V;i++){
            for(int w:adj(i)){
                R.addEDGE(w,i);
            }
        }

        return R;
    }


}
