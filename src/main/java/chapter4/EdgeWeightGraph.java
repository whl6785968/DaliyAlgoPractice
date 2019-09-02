package chapter4;

import chapter1.Bag;

public class EdgeWeightGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int i=0;i<V;i++){
            adj[i] = new Bag<Edge>();
        }
    }

    public int E(){
        return E;
    }

    public int V(){
        return V;
    }

    public void addEdge(Edge e){
        int v = e.either(); int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<Edge>();

        for (int v=0;v<V;v++){
            for(Edge e:adj(v)){
                if(e.other(v)>v){
                    b.add(e);
                }
            }
        }

        return b;
    }
}
