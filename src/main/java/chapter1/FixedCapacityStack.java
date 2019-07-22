package chapter1;

import com.sun.deploy.panel.ITreeNode;
//queue:
//leave the queue make head + 1,enter the queue make tail + 1
//object freedom is some reference that do not have object or memory address.
//we can avoid it by make the reference equals null,just like a[reference]=null
//N has been changing with push(N++) or pop(--N)
public class FixedCapacityStack<Item> {
    private Item[] a;
    private int N;

    public FixedCapacityStack(int cap){
        a = (Item[])new Object[cap];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void push(Item item){
        if(N == a.length){
            resize(2*a.length);
        }
        a[N++] = item;
    }

    //length/4 become length/2 of new array if new array is half-fold;
    public Item pop(){
        Item item = a[--N];
        a[N] = null;
        if(N == a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    public void resize(int max){
        Item[] temp = (Item[]) new Object[max];
        for(int i=0;i<N;i++){
            temp[i] = a[i];
        }
        a = temp;
    }
}
