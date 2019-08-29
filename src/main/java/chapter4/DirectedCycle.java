package chapter4;

import edu.princeton.cs.algs4.Stack;

public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(DiGraph G){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        for(int v=0;v<G.V();v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }

    }

    public void dfs(DiGraph G,int v){
        onStack[v] = true;
        marked[v] = true;

        for(int w:G.adj(v)){
            if(this.hasCycle()){
                return;
            }
            else if(!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
            else {
                cycle = new Stack<Integer>();
                for(int x = v; x!=w;x=edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }

        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }


}
