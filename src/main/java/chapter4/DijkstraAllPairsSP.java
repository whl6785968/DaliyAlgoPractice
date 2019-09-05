package chapter4;

public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(DirectedEdgeWeightGraph G){
        all = new DijkstraSP[G.V()];
        for(int v=0;v<G.V();v++){
            all[v] = new DijkstraSP(G,v);
        }
    }

    public Iterable<DirectedEdge> pathTo(int s,int t){
        return all[s].pathTo(t);
    }

    public double distTo(int s,int t){
        return all[s].distTo(t);
    }
}
