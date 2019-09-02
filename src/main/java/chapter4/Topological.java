package chapter4;
//it can be topologically sorted when a directed graph is DAG.
public class Topological {
    private Iterable<Integer> order;

    public Topological(DiGraph G){
        DirectedCycle directedCycle = new DirectedCycle(G);
        if(!directedCycle.hasCycle()){
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    public boolean isDAG(){
        return order != null;
    }

    public Iterable<Integer> order(){
        return order;
    }
}
