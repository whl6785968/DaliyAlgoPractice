package chapter4;

import chapter1.Queue;
import edu.princeton.cs.algs4.MinPQ;


//横切边是一条边that连接一个在树中的节点和一个不在树中的节点
public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightGraph G){
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        visit(G,0);
        while (!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if(!marked[v] && !marked[w]) continue;
            mst.push(e);
            if(!marked[v]){
                visit(G,v);
            }
            if(!marked[w]){
                visit(G,w);
            }
        }
    }

    public void visit(EdgeWeightGraph G,int v){
        marked[v] = true;

        for (Edge e:G.adj(v)){
            if(!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }
}
