package chapter3;

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class ST<Key extends Comparable<Key>,Value> {
    public void put(Key key,Value val){

    }

    public Value get(Key key){
        return null;
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
        return 0;
    }

    public int size(int lo,int hi){
        return 0;
    }

    public Iterator<Key> keys(){
        return null;
    }

    public Iterator<Key> keys(int lo,int hi){
        return null;
    }

    public Key min(){
        return null;
    }

    public Key max(){
        return null;
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
