package chapter3;

import java.util.Iterator;

//number of hit 1/2(1+(1/(1+a)))
//number of unhit 1/2(1+(1/(1+a)pow(2)))
public class LinearProbingHashST<Key,Value> {
    private int N; //total of k-v in the character table
    private int M; //size of linear probing table
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashST(int cap){
        M = cap;
        keys = (Key[]) new Object[M];
        values = (Value[]) new Object[M];
    }

    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff)%M;
    }

    public int size(){
        return N;
    }

    private void resize(int cap){
        LinearProbingHashST<Key,Value> t;
        t = new LinearProbingHashST<Key, Value>(cap);
        for(int i=0;i<cap;i++){
            if(keys[i] != null){
                t.put(keys[i],values[i]);
            }
        }

        keys = t.keys;
        values = t.values;
        M = t.M;
    }

    public Value get(Key key){
        for(int i=hash(key);keys[i]!=null;i=(i+1)%M){
            if(keys[i].equals(key)){
                return values[i];
            }
        }
        return null;
    }

    public void put(Key key,Value value){
       if(N>M/2){
           resize(2*M);
       }
       int i;
       for(i=hash(key);keys[i]!=null;i=(i+1)%M){
           if(keys[i].equals(key)){
               values[i] = value;
               return;
           }
       }

       keys[i] = key;
       values[i] = value;
       N++;
    }

    public boolean contains(Key key){
        for(int i=hash(key);keys[i]!=null;i=(i+1)%M){
            if(keys[i].equals(key)){
                return true;
            }
        }
        return false;
    }

    public void delete(Key key){
        if(!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i])){
            i = (i+1)%M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i+1)%M;
        while(keys[i] != null){
            Key keyRe = keys[i];
            Value valueRe = values[i];
            keys[i] = null;
            values[i] = null;
            put(keyRe,valueRe);
            i = (i+1)%M;
        }
        N--;
        if(N >0 && N == M/8){
            resize(M/2);
        }
    }

    public Iterator<Key> keys(){
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Key>{
        int i = 0;
        Key current = keys[i];
        public boolean hasNext() {
            return current != null;
        }

        public Key next() {
            Key key = keys[i++];
            current = key;
            return key;
        }

        public void remove() {

        }
    }

}
