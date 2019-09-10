package chapter4;

import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.DirectedEdge;

/**
 * 解决并行任务调度问题的关键路径方法的步骤:
 * 1.创建一个无环加权无向图，其中包含一个起始顶点s和一个结束顶点t，且每个顶点都对应两个节点
 * 2.对于每个任务都有一条从它的起始顶点指向结束顶点的边
 * 3.对于v->w，添加一条从v的结束顶点指向w的起始顶点且权重为0的边
 * 4.为每个任务添加一条从起点指向任务的起始顶点且权重为0和一条从任务结束节点指向终点权重为0的边
 * **/

public class CPM {
    public static void main(String[] args) {
        int N = StdIn.readInt();
        StdIn.readLine();
        EdgeWeightedDigraph G;
        G = new EdgeWeightedDigraph(2*N+2);
        int s = 2*N,t = 2*N+1;
        for (int i=0;i<N;i++){
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i,i+N,duration));
            G.addEdge(new DirectedEdge(s,i,0.0));
            G.addEdge(new DirectedEdge(i+N,t,0.0));
            for(int j=1;j<a.length;j++){
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i+N,successor,0.0));
            }
        }

        AcyclicLP lp = new AcyclicLP(G,s);
        System.out.println("Start times: ");
        for(int i=0;i<N;i++){
            StdOut.printf("%4d: %5.1f\n",i,lp.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n",lp.distTo(t));
    }
}
