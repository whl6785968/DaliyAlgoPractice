package chapter1;

import chapter1.start.BinarySearch;
import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class SumFast {
    public static int twoSumFast(int[] a){
        int count = 0;
        Arrays.sort(a);
        for(int i=0;i<a.length;i++){
            if(BinarySearch.rank(-a[i],a)>i){
                count++;
            }
        }
        return count++;
    }

    public static int threeSumFast(int[] a){
        int count = 0;
        Arrays.sort(a);
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                if(BinarySearch.rank((-a[i]-a[j]),a)>i){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] a = in.readAllInts();
        int count = twoSumFast(a);
        System.out.println(count);
    }
}
