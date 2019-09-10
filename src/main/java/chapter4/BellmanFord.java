package chapter4;

import chapter1.Queue;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

public class BellmanFord {
    private double[] distTo;
    private edu.princeton.cs.algs4.DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFord(EdgeWeightedDigraph G,int s){
        distTo = new double[G.V()];
        edgeTo = new edu.princeton.cs.algs4.DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<Integer>();
        for(int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        queue.push(s);
        onQ[s] = true;

        while(!queue.isEmpty()){
            int v = queue.pop();
            onQ[v] = false;
            relax(G,v);
        }
    }

    public void relax(EdgeWeightedDigraph G,int v){
        for(edu.princeton.cs.algs4.DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(!onQ[w]){
                    queue.push(w);
                    onQ[w] = true;
                }
            }

            if(cost++ % G.V() == 0){

            }
        }
    }

    private void findNegativeCycle(){
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for(int v=0;v<V;v++){
            if(edgeTo[v] != null){
                spt.addEdge(edgeTo[v]);
            }
        }

        
    }

    public boolean hasNegCycle(){
        return false;
    }





}
