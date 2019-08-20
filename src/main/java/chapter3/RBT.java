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

    public void filpColor(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
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
            filpColor(h);
        }

        h.N = size(h.left) + size(h.right) + 1;

        return h;
    }

    public static void main(String[] args) {

    }
}
