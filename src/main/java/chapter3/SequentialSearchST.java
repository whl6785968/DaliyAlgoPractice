package chapter3;

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
            }
        }

        first = new Node(key,value,first);
    }

    public Iterator<Key> keys(){
        return new ListIterator();
    }

    public class ListIterator implements Iterator<Key> {
        private Node current = first;

        public boolean hasNext() {
            return current == null;
        }

        public Key next() {
            Key key = current.key;
            current = current.next;
            return key;
        }

        public void remove() {

        }
    }

    public boolean contains(Key key){
        return get(key) != null;
    }
}
