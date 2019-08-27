package chapter3;

import chapter1.Queue;

import javax.xml.transform.dom.DOMLocator;
import java.util.Iterator;

public class SeparateChainingHashST<Key,Value> {
    private int N;
    private int M;

    private SequentialSearchST<Key,Value>[] st;

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int M){
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i=0;i<M;i++){
            st[i] = new SequentialSearchST<Key, Value>();
        }
    }

    public int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key){
        return st[hash(key)].get(key);
    }

    public void put(Key key,Value value){
        st[hash(key)].put(key,value);
    }

    public static void main(String[] args) {
        SeparateChainingHashST<Integer, Double> st1 = new SeparateChainingHashST<Integer, Double>();
        System.out.println(st1.hash(50));
        System.out.println(st1.hash(4));

        SeparateChainingHashST st2 = new SeparateChainingHashST<String, Double>();
        System.out.println(st2.hash("hello"));
        System.out.println(st2.hash("hellp"));
        System.out.println(st2.hash("world"));
    }

    public boolean contains(Key key){
        if(get(key)!=null){
            return true;
        }
        return false;
    }

    public int size(){
        return M;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<Key>();
        for(int i=0;i<M;i++){
            for(Key key : st[i].keys()){
                queue.push(key);
            }
        }
        return queue;
    }






}
