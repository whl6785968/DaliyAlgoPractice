package chapter4;

import chapter1.Queue;
import edu.princeton.cs.algs4.Stack;

public class BreadthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstSearch(Graph G,int s){
        marked = new boolean[G.V()];
        this.s = s;
        edgeTo = new int[G.V()];
        bfs(G,s);
    }

    private void bfs(Graph G,int s){
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.push(s);
        while (!queue.isEmpty()){
            int v = queue.pop();
            for(int w:G.adj(v)){
                if(!marked[v]){
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.push(w);
                }
            }
        }

    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<Integer> stack = new Stack<Integer>();
        for(int x=v;x!=s;x=edgeTo[v]){
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
