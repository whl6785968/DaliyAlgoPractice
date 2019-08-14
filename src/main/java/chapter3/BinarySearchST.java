package chapter3;

public class BinarySearchST<Key extends Comparable<Key>,Value> {
    private Key[] keys;
    private Value[] values;
    private int N;

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
        N = capacity;
    }

    public int rank(Key key,int lo,int hi){
        int mid = lo + (hi-lo)/2;

        if(key.compareTo(keys[mid])>0){
            lo = mid + 1;
            return rank(key,lo,hi);
        }
        else if(key.compareTo(keys[mid])<0){
            hi = mid -1 ;
            return rank(key,lo,hi);
        }
        else {
            return mid;
        }

    }

    public int rank(Key key){
        return rank(key,0,keys.length-1);
    }

    public Value get(Key key){
        if(isEmpty()){
            return null;
        }

        int i = rank(key);
        if(keys[i].compareTo(key)==0 && i<N){
            return values[i];
        }
        else return null;
    }

    public void put(Key key,Value value){
        int i = rank(key);

        if(i< N && keys[i].compareTo(key)==0){
            values[i] = value;
        }

        for(int j=N;j>i;j--){
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }

        keys[i] = key;
        values[i] = value;

        N++;
    }

    public boolean isEmpty(){
        return N==0;
    }
}
