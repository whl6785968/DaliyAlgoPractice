package chapter4;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Topological;

//按照拓扑顺序放松顶点，就能在V+E成正比的时间内解决无环加权有向图的单点最短路径问题
//用深度优先搜索得到图的顶点和拓扑排序，再用DijkstraSP松弛每个顶点得到最短路径
//每个顶点只会被松弛一次
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        distTo[s] = 0.0;
        for(int v=0;v<G.V();v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }

        Topological topological = new Topological(G);
    }

  /*  public void relax(EdgeWeightedDigraph G,int v){
        for(edu.princeton.cs.algs4.DirectedEdge e:G.adj(v)){
            int w = e.to();
            if(distTo[w]>e.weight() + distTo[v]){
                distTo[w] = e.weight() + distTo[v];
                edgeTo[w] = e;
            }
        }
    }*/
}
