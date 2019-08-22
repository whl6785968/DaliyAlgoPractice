package chapter3;

import java.util.Iterator;

public class SparseVector {
    private LinearProbingHashST<Integer,Double> st;

    public SparseVector(){
        st = new LinearProbingHashST<Integer, Double>(16);
    }

    public int size(){
        return st.size();
    }

    public void put(Integer i,Double value){
        st.put(i, value);
    }

    public double get(int i){
        if(!st.contains(i)){
            return 0.0;
        }else {
            return st.get(i);
        }
    }

    public double dot(double[] that){
        double sum = 0.0;
        Iterator<Integer> keys = st.keys();
        for (Iterator<Integer> it = keys; it.hasNext(); ) {
            int i = it.next();
            sum += that[i]*this.get(i);
        }
        return sum;
    }
}
