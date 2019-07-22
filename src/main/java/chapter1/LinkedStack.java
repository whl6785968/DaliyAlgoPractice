package chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LinkedStack<Item> {
    private int N = 0;
    private Node first;
    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public int size(){
        return N;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<String>();

        while(!StdIn.isEmpty()){
            String item = StdIn.readString();
            if(!item.equals("-")){
                stack.push(item);
            }
            else if(!stack.isEmpty()){
                System.out.println(stack.pop() + " ");
            }
        }

        StdOut.println("("+ stack.size() + "left on stack )");
    }
}
