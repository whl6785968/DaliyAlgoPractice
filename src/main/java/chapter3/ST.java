package chapter3;

import edu.princeton.cs.algs4.StdIn;

import java.lang.ref.PhantomReference;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>,Value> {
    private TreeMap<Key,Value> st;

    public ST(){
        st = new TreeMap<Key, Value>();
    }

    public void put(Key key,Value val){
        if (key == null) throw new IllegalArgumentException("calls put() with null key");
        if (val == null) st.remove(key);
        else st.put(key,val);
    }

    public Value get(Key key){
        if (key == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }

    public void delete(Key key){
        put(key,null);
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public int size(){
        return st.size();
    }

    public int size(int lo,int hi){
        return 0;
    }

    public Iterable<Key> keys(){
        return st.keySet();
    }


    public Key min(){
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return st.firstKey();
    }

    public Key max(){
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return st.lastKey();
    }

    public Key floor(Key key){
        return null;
    }

    public Key ceil(Key key){
        return null;
    }

    public int rank(Key key){
        return 0;
    }

    public Key select(int k){
        return null;
    }

    public void deleteMin(){

    }

    public void deleteMax(){

    }

    public static void main(String[] args) {
        ST<String,Integer> st;
        st = new ST<String, Integer>();

        for(int i=0;!StdIn.isEmpty();i++){
            String key = StdIn.readString();
            st.put(key,i);
        }


    }

}
