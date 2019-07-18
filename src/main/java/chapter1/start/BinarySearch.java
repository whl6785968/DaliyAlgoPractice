package chapter1.start;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BinarySearch {
    public static int rank(int key,int[] a){
        int lo = 0;
        int hi = a.length-1;

        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(key < a[mid]){
                hi = mid -1 ;
            }else if(key > a[mid]){
                lo = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        int[] whiteList = in.readAllInts();
//        for (int i : whiteList){
//            System.out.println(i);
//        }
        Arrays.sort(whiteList);

        while(!StdIn.isEmpty()){
            int key = StdIn.readInt();
            if(rank(key,whiteList)<0){
//                System.out.println(key);
                StdOut.println(key);
            }
        }
        System.out.println(1);
    }
}
