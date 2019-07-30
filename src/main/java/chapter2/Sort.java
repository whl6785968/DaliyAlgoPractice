package chapter2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Sort {
    private static Comparable[] aux;
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

    public static void merge(Comparable[] a,int lo,int mid,int hi){
        int i = lo;
        int j = mid+1;

        for(int k = lo;k<=hi;k++){
            aux[k] = a[k];
        }

        for(int k = lo;k<=hi;k++){
            if(i>mid){
                a[k] = aux[j++];
            }
            else if(j>hi) a[k] = aux[i++];
            else if(less(aux[j],aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a){
        aux= new Comparable[a.length];
        sort(a,0,a.length-1);

    }
    //1/2NlgN-NlgN compare
    //the time merge sort costs and NlgN in the direct ratio
    //from top to bottom
    public static void sort(Comparable[] a,int lo,int hi){
        if(lo>=hi) return;
        int mid = lo+(hi-lo)/2;
        sort(a, lo, mid);
        sort(a,mid+1,hi);
        //the min of right side greater than the max of left,skip merge
        if(less(a[mid],a[mid+1])) return;
        merge(a,lo,mid,hi);
    }

    //from bottom to top
    public static void sort2(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        //sz is size of array
        //the process is like 1 1=2 ,2 2=4,4 4=8
        //the size of array is doubling until complete sort
        for(int sz=1;sz<N;sz=sz+sz){
            for (int lo=0;lo<N-sz;lo+=sz+sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,(N-1)));
            }
        }
    }
    public static void quick(Comparable[] a){
        StdRandom.shuffle(a);
//        quick(a,0,a.length-1);
        quick3Way(a,0,a.length-1);
    }
    public static void quick(Comparable[] a,int lo,int hi){
        if(lo >= hi) return;
        //make the element at left side of split element less than split element
        //make the element at right side of split element greater than split element
        int j = partition(a,lo,hi);
        quick(a,lo,j-1);
        quick(a,j+1,hi);
    }

    public static int partition(Comparable[] a,int lo,int hi){
        int i = lo;
        int j = hi + 1;

        Comparable v = a[lo];
        while (true){
            while (less(a[++i],v)){
                if(i == hi) {
                    break;
                }
            }

            while (less(v,a[--j])){
                if(j == lo) {
                    break;
                }
            }

            if(i >= j) break;

            exch(a,i,j);
        }

        exch(a,lo,j);
        return j;
    }

    public static void quick3Way(Comparable[] a,int lo,int hi){
        if(lo>=hi) return;
        int lt = lo,i = lo + 1,gt = hi;
        Comparable v = a[lo];
        while (i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp<0) exch(a,lt++,i++);
            else if(cmp>0) exch(a,i,gt--);
            else i++;
        }

        quick3Way(a,lo,lt-1);
        quick3Way(a,gt+1,hi);
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
        Comparable[] a = {3,7,2,5,8,4,1,3};
//        shell(a);
//        insertion(a);
//        sort2(a);
        quick(a);
        show(a);
    }

}
