package chapter4;

import chapter1.Queue;
import chapter1.UF;
import edu.princeton.cs.algs4.MinPQ;

public class KruskalMST {
    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightGraph G){
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>(G.V());
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size()<G.V() - 1){
            Edge e = pq.delMin();
            int v = e.either(); int w = e.other(v);
            if(uf.connection(v,w)) continue;
            uf.union(v,w);
            mst.push(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
}
