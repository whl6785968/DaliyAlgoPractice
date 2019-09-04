package chapter4;

import edu.princeton.cs.algs4.Stack;

public class SPT {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public SPT(DirectedEdgeWeightGraph G){
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];

        distTo[0] = 0;
        edgeTo[0] = null;

        for(int v=1;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
    }

    public void relax(DirectedEdge e){
        int v = e.from(),w = e.to();

        if(distTo[w]>distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    //v顶点比n顶点到w的距离大的话，无效化v->w，反之替换最小distTo
    public void relax(DirectedEdgeWeightGraph G,int v){
        for (DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[w]>distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> stack = new Stack<DirectedEdge>();

        for(DirectedEdge e = edgeTo[v];e!=null;e=edgeTo[e.from()]){
            stack.push(e);
        }

        return stack;
    }
}
