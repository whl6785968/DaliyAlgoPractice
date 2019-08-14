package chapter2;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;
    public MaxPQ(){

    }
    public MaxPQ(int max){
        pq = (Key[]) new Comparable[max];
    }

    //most lgN+1 compare
    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key max(){
        return null;
    }

    //most lg2N compare
    public Key delMax(){
        //get root
        Key max = pq[1];
        exch(1,N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<0;
    }

    public void exch(int i,int j){
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public void swim(int k){
        while(k>1&&less(k/2,k)){
            exch(k/2,k);
            k = k/2;
        }
    }

    public void sink(int k){
        while (2*k <= N){
            int j = 2*k;
            if(less(2*k,2*k+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }
    public void sink(Comparable[] a,int k,int N){
        while (k>0){

        }
    }

    public void sort(Comparable[] a){
        int N = a.length;
        for(int k=N/2;k>=1;k--){
            sink(a,k,N);
        }

        while (N>1){
//            exch(a,1,N--);
            sink(a,1,N);
        }
    }


}
