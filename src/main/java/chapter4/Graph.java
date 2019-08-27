package chapter4;

import chapter1.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int i=0;i<V;i++){
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in){
        this(in.readInt());
        int E = in.readInt();
        for(int i=0;i<E;i++){
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v,w);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public String toString(){
        String s = V + "vertices, " + E +"edges\n" ;
        for(int v=0;v<V;v++){
            s+= v + ":";
            for(int w:this.adj(v)){
                s+=w+",";
            }
            s+="\n";
        }

        return null;
    }

    public static int degree(Graph G,int v){
        int degree = 0;
        for (Object i:G.adj(v)){
            degree++;
        }
        return degree;
    }

    public static int maxDegree(Graph G){
        int max = 0;
        for(int v=0;v<G.V();v++){
            if(degree(G,v)>max){
                max = degree(G,v);
            }
        }

        return max;
    }

    public static double avgDegree(Graph G){
        return 2*G.E()/G.V();
    }

    public static int numberOfSelfLoop(Graph G){
        int count = 0;
        for(int v=0;v<G.V();v++){
            for(Object w:G.adj(v)){
                if(Integer.parseInt(w.toString()) == v){
                    count++;
                }
            }
        }

        return count;
    }



}
