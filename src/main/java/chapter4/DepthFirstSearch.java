package chapter4;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

//when you are visiting a node:
//mark it as visited
//recursion visit all neighbor node of the node that still not be visited
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;
    private int[] edgeTo;
    private int s;

    public DepthFirstSearch(Graph G,int s){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G,int v){
        marked[v] = true;
        count++;
        for(int w:G.adj(v)){
            if(!marked(w)){
                edgeTo[w] = v;
                dfs(G,w);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;

        Stack<Integer> stack = new Stack<Integer>();
        //0 to 1 : 0-2-1
        //1:who can reach 1? edge[1] = 2  answer:2 x=2
        //2:who can reach 2? edge[2] = 0  answer:0 x=0
        //x==s exit so,the result is 0-2-1
        for(int x=v;x!=s;x = edgeTo[x]){
            stack.push(x);
        }
        stack.push(s);

        return stack;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch dfs = new DepthFirstSearch(g,s);

        for(int v=0;v<g.V();v++){
            System.out.println(s + " to " + v +":");
            if(dfs.hasPathTo(v)){
                for(int x:dfs.pathTo(v)){
                    if(x == s){
                        System.out.println(v);
                    }
                    else {
                        System.out.println("-" + x);
                    }
                }
            }
            System.out.println();
        }
    }


}
