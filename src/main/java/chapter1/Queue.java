package chapter1;

//The difference between the push operation of queue and stack is like
//queue:where is the bucket come from?
//stack:where was water has been poured out?
public class Queue<Item> {
    private Node first;
    private Node last;
    private int N = 0;

    private class Node{
        Item item;
        Node next;
    }

    public void push(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }
        else {
            oldLast.next = last;
        }
        N++;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last = null;
        }
        N--;
        return item;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return N == 0;
    }


}
