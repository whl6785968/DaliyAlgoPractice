package chapter4;

import edu.princeton.cs.algs4.IndexMinPQ;
import org.jcp.xml.dsig.internal.dom.DOMBase64Transform;

import java.util.Stack;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ<Double> pq;

    public DijkstraSP(DirectedEdgeWeightGraph G,int s){
        pq = new IndexMinPQ<Double>(G.V());
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        pq.insert(s,0.0);
        for (int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        while(!pq.isEmpty()){
            relax(G,pq.delMin());
        }

    }

    public void relax(DirectedEdgeWeightGraph G,int v){
        for(DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[w]>e.weight() + distTo[v]){
                distTo[w] = e.weight() + distTo[v];
                edgeTo[w] = e;

                if(pq.contains(w)) pq.changeKey(w,distTo[w]);
                else pq.insert(w,distTo[w]);
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v]<Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> stack = new Stack<DirectedEdge>();

        for(DirectedEdge e=edgeTo[v];e!=null;e=edgeTo[e.from()]){
            stack.push(e);
        }

        return stack;
    }
}
