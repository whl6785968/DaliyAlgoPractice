package chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Sort {
    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    private static void exch(Comparable[] a,int i,int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void show(Comparable[] a){
        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }
    }

    public static boolean isSorted(Comparable[] a){
        for(int i=0;i<a.length;i++){
            if(a[i].compareTo(a[i+1])>0){
                return false;
            }
        }
        return true;
    }
    public static double time(String alg,Comparable[] a){
        Stopwatch stopwatch = new Stopwatch();
        if(alg.equals("Insertion")) insertion(a);
        if(alg.equals("Selection")) selection(a);
        if(alg.equals("Shell")) shell(a);
        return stopwatch.elapsedTime();

    }

    public static double timeRandomInput(String alg,int N,int T){
        double total = 0;
        Double[] a = new Double[N];
        for(int i=0;i<T;i++){
            for(int j=0;j<N;j++){
                a[j] = StdRandom.uniform();
            }
            double time = time(alg, a);
            total += time;
        }

        return total;
    }

    public static void selection(Comparable[] a){
        for(int i=0;i<a.length;i++){
            int min = i;
            for(int j=i+1;j<a.length;j++){
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

    public static void insertion(Comparable[] a){
        for(int i=1;i<a.length;i++){
            for (int j=i;j>0&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }

    public static void shell(Comparable[] a){
        int n = a.length;
        int h = 1;
        while (h<n/3){
            h = 3*h+1;
        }

        while(h>=1){
            for(int i=h;i<n;i++){
                for(int j=i;j>=h&&less(a[j],a[j-h]);j -= h){
                    exch(a,j,j-h);
                }
            }
            h = h/3;
        }
    }

    public static void main(String[] args) {
//        String alg1 = args[0];
//        String alg2 = args[1];
//        String alg3 = args[2];
//
//        int N = Integer.parseInt(args[3]);
//        int T = Integer.parseInt(args[4]);
//
//        double v1 = timeRandomInput(alg1, N, T);
//        double v2 = timeRandomInput(alg2, N, T);
//        double v3 = timeRandomInput(alg3,N,T);
//
//        System.out.println("v1 = " + v1);
//        System.out.println("v2 = " + v2);
//        System.out.println("v3 = " + v3);
//        System.out.println("Insertion is " +v2/v1 + " faster than Selection");
//
        Comparable[] a = {3,7,2,5,8,4,1};
        shell(a);
//        insertion(a);
        show(a);
    }

}
