package chapter3;


import com.sun.org.apache.regexp.internal.RE;
import edu.princeton.cs.algs4.BlackFilter;

/**
 * RBT contains r&b link and satisfy condition below:
 * 1.all red link is left link
 * 2.there is not any node that is connect with two red links.(because the node is 4-node if the node have)
 * 3.the rbt is perfectly black balance.(the number of black link is same in the path of any null link to root )
 *
 * **/
public class RBT<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node{
        Key key;
        Value value;
        Node left,right;
        int N;
        boolean color;

        Node(Key key,Value value,int N,boolean color){
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x){
        if(x == null) return false;
        return x.color == RED;
    }

    public int size(){
        return size(root);
    }

    public int size(Node x){
        if(x == null) return 0;
        else return x.N;
    }

    public boolean isEmpty(){
        return root.N == 0;
    }

    public Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) +1;
        return x;
    }

    public Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) +1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    public Node min(Node x){
        if(x.left == null) return x;
        else return min(x.left);
    }

    public void flipColor(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public Value get(Key key){
        return get(root,key);
    }

    public Value get(Node x,Key key){
        if(x==null) return null;
        else {
            int cmp = key.compareTo(x.key);
            if(cmp<0) return get(x.left,key);
            else if(cmp>0) return get(x.right,key);
            else return x.value;
        }
    }

    public void put(Key key,Value val){
        root = put(root,key,val);
        root.color = BLACK;
    }

    public Node put(Node h,Key key,Value value){
        if (h == null){
            return new Node(key,value,1,RED);
        };

        int cmp = key.compareTo(h.key);
        if(cmp<0)  h.left = put(h.left,key,value);
        else if(cmp>0)  h.right = put(h.right,key,value);
        else {
            h.value = value;
        }

        if(isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if(!isRed(h.left) && isRed(h.right)){
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.right)){
            flipColor(h);
        }

        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

    public Node balance(Node h){
        if(isRed(h.right)){
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.left.left)){
            h = rotateRight(h);
        }
        if(!isRed(h.left) && isRed(h.right)){
            h = rotateLeft(h);
        }
        if(isRed(h.left) && isRed(h.right)){
            flipColor(h);
        }

        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

    private Node moveRedLeft(Node h){
        flipColor(h);
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }

        return h;
    }

    public void deleteMin(){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root);
        if(!isEmpty()){
            root.color = BLACK;
        }
    }

    public Node deleteMin(Node h){
        if(h.left == null){
            return null;
        }                                                //                b
        if(!isRed(h.left) && !isRed(h.left.left)){       //               / \
            h = moveRedLeft(h);                          //              a   d
        }                                                //             / \ / \
        h.left = deleteMin(h.left);                      //                c   e
        return balance(h);
    }

    private Node moveRedRight(Node h){
        flipColor(h);
        if(!isRed(h.left.left)){
            h = rotateRight(h);
        }

        return h;
    }

    public void deleteMax(){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMax(root);

        if(!isEmpty()){
            root.color = BLACK;
        }
    }

    public Node deleteMax(Node h){
        if(isRed(h.left)){
            h = rotateRight(h);
        }
        if(h.right == null){
            return null;
        }
        if(!isRed(h.right) && !isRed(h.right.left)){
            h = moveRedRight(h);
        }

        h.right = deleteMax(h.right);

        return balance(h);
    }

    public void delete(Key key){
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = delete(root,key);

        if(!isEmpty()){
            root.color = BLACK;
        }
    }

    public Node delete(Node h,Key key){
        if(key.compareTo(h.key)<0){
            if(!isRed(h.left) && !isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left,key);
        }
        else {
            if(isRed(h.left)){
                h = moveRedRight(h);
            }
            if(key.compareTo(h.key) == 0 && h.right == null){
                return null;
            }
            if(!isRed(h.right) && !isRed(h.left.right)){
                h = moveRedRight(h);
            }
            if(key.compareTo(h.key) == 0){
                h.value = get(h.right,min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else {
                h.right = delete(h.right,key);
            }
        }

        return balance(h);
    }

    public static void main(String[] args) {
        Integer a = 20;
        String s = Integer.toBinaryString(a);
        int i = Integer.numberOfLeadingZeros(20);
        System.out.println(i);
        System.out.println(s);
    }
}
