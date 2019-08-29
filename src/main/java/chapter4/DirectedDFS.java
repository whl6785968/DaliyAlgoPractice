package chapter4;

public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(DiGraph G,int s){
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    public DirectedDFS(DiGraph G,Iterable<Integer> source){
        marked = new boolean[G.V()];
        for(int v:source){
            if(!marked(v)){
                dfs(G,v);
            }
        }
    }

    public void dfs(DiGraph G,int v){
        marked[v] = true;
        for(int w:G.adj(v)){
            if(!marked(w)){
                dfs(G,w);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }
}
