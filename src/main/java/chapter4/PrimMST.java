package chapter4;

import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    private boolean[] marked;
    private Edge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightGraph G){
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        for(int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0,distTo[0]);
        while(!pq.isEmpty()){
            visit(G,pq.delMin());
        }
    }


    public void visit(EdgeWeightGraph G,int v){
        marked[v] = true;

        for(Edge e:G.adj(v)){
            int w = e.other(v);
            if(marked[w]) continue;

            if(e.weight()<distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if(pq.contains(w)){
                    pq.changeKey(w,distTo[w]);
                }
                else{
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }
}
