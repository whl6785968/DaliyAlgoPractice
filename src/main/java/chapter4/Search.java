package chapter4;

import edu.princeton.cs.algs4.In;

public class Search {
    public Search(Graph G,int s){

    }

    public boolean marked(int v){
        return false;
    }

    public int count(){
        return 0;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);

        Search search = new Search(G,s);
        for(int v=0;v<G.V();v++){
            if(search.marked(v)){
                System.out.print(v + " ");
            }
        }

        System.out.println();

        if(search.count() != G.V()){
            System.out.println("NOT CONNECTED");
        }
        System.out.println("CONNECTED");
    }
}
