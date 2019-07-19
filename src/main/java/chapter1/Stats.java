package chapter1;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Stats {
    public static void main(String[] args) {
        Bag<Double> bag = new Bag<Double>();
        while(!StdIn.isEmpty()){
            bag.add(StdIn.readDouble());
        }

        int N = bag.size();

        double sum = 0.0;
        for(Double item : bag){
            sum += item;
        }
        double mean = sum/N;

        sum = 0.0;
        for(double item : bag){
            sum += (item - mean)*(item + mean);
        }

        double std = Math.sqrt(sum/N-1);

        StdOut.printf("Mean: %.2f\n",mean);
        StdOut.printf("Std: %.2f\n",std);
    }
}
