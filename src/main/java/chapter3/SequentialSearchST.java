package chapter3;

import chapter1.Queue;

import java.util.Iterator;

public class SequentialSearchST<Key,Value> {
    private class Node{
        Key key;
        Value value;
        Node next;


        public Node(Key key,Value value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node first;
    int N = 0;

    public Value get(Key key){
        for(Node node=first;node!=null;node=node.next){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    public void put(Key key,Value value){
        for(Node node=first;node!=null;node=node.next){
            if(node.key.equals(key)){
                node.value = value;
                return;
            }
        }

        first = new Node(key,value,first);
        N++;
    }

    public void delete(Key key){
        if(key == null){
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first,key);
    }

    public Node delete(Node x,Key key){
        if(key.equals(x.key)){
            N--;
            return x.next;
        }
        x.next = delete(x.next,key);
        return x;
    }

    public Iterable<Key> keys(){
        Queue<Key> queue = new Queue<Key>();
        for(Node x = first; x != null;x=x.next){
            queue.push(x.key);
        }
        return queue;
    }

//    public Iterator<Key> keys(){
//        return new ListIterator();
//    }
//
//    public class ListIterator implements Iterator<Key> {
//        private NodeByImitate current = first;
//
//        public boolean hasNext() {
//            return current == null;
//        }
//
//        public Key next() {
//            Key key = current.key;
//            current = current.next;
//            return key;
//        }
//
//        public void remove() {
//
//        }
//    }

    public boolean contains(Key key){
        return get(key) != null;
    }
}
