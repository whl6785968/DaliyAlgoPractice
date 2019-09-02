package chapter4;

//explain:
//强连通分量中节点s到任意节点v都是强连通的
//设v为dfs(G,s)到达的某个顶点，则必然存在s->v
//如果证明s->v是强连通的，则要证明存在v->s
//上句可以看作为从G的reverse图中寻找s->v
//故只要证明，在深度优先搜索中，检查v在检查s结束之前已经结束了
//故存在两种情况
/**
 * 1.在dfs(G,s)调用之前结束
 * 由于Gr中存在v->s，即dfs(G,s)在dfs(G,v)之前结束，不可能的情况
 * 2.在dfs(G,s)调用之后结束
 * 说明Gr中存在一条s->v的路径，恰好证明了存在s->v，故需要采用Gr的reversePost()进行dfs
 *
 * */
public class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(DiGraph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G.reverse());

        for (int v:depthFirstOrder.reversePost()){
            if(!marked[v]){
                dfs(G,v);
                count++;
            }
        }
    }

    public void dfs(DiGraph G,int v){
        marked[v] = true;
        id[v] = count;

        for (int w:G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
    }

    public boolean stronglyConnected(int v,int w){
        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }
}
