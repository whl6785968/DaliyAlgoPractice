package chapter1.draw;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class FunctionDraw {
    public static void main(String[] args) {
        drawSort();
//        System.out.println(Math.log(100));
    }

    public static void drawByFunction(){
        int N = 100;
        StdDraw.setXscale(0,N);
        StdDraw.setYscale(0,N*N);
        StdDraw.setPenRadius(.01);

        for(int i=1;i<=N;i++){
            StdDraw.point(i,i);
            StdDraw.point(i,i*i);
            StdDraw.point(i,i*Math.log(i));
        }
    }

    public static void drawSort(){
        int N = 50;
        double[] a = new double[N];
        for(int i = 0;i<N;i++){
            a[i] = StdRandom.random();
        }

        Arrays.sort(a);

        for(int i=0;i<N;i++){
            double x = 1.0*i/N;
            double y = a[i]/2.0;
            double rw = 0.5/N;
            double rh = a[i]/2.0;
            StdDraw.filledRectangle(x,y,rw,rh);
        }
    }
}
