package Tree;

import org.omg.CORBA.NO_IMPLEMENT;

public class BST<Key extends Comparable<Key>,Value> {
    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        public int N;

        public Node(Key key,Value value,int N){
            this.key = key;
            this.value = value;
            this.N = N;
        }
    }

    private Node root;

    public int size(){
        return size(root);
    }

    public int size(Node x){
        if(x == null) return 0;
        else return x.N;
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

    public Node put(Key key,Value value){
        return put(root,key,value);
    }

    public Node put(Node x, Key key, Value value){
        if(x==null){
            new Node(key,value,1);
        }

        int cmp = key.compareTo(x.key);

        if(cmp<0) return put(x.left,key,value);
        else if(cmp>0) return put(x.right,key,value);
        else {
            x.value = value;
        }
        x.N = size(x.left)+size(x.right)+1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    public Node min(Node x){
        if(x.left == null) return x;
        else return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    public Node max(Node x){
        if(x.right == null) return x;
        else return max(x.right);
    }

    public Key floor(Key key){
        return floor(root,key).key;
    }

    public Node floor(Node x,Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return floor(x.left,key);
        if(cmp == 0) return x;

        Node node = floor(x.right,key);
        if(node != null) return node;
        else return x;
    }

    public Key select(int k){
        return select(root,k).key;
    }

    public Node select(Node x,int k){
        if(x == null) return null;

        int t = size(x.left);
        if(t>k){
            return select(x.left,k);
        }
        else if(t<k){
            return select(x.right,k-t-1);
        }
        else return x;
    }

    public int rank(Key key){
        return rank(root,key);
    }

    public int rank(Node x,Key key){
        if(x == null) return 0;

        int cmp = key.compareTo(x.key);
        if(cmp<0){
            return rank(x.left,key);
        }
        else if(cmp>0){
            return 1 + size(x.left) + rank(x.right,key);
        }
        else {
            return size(x.left);
        }
    }

    public Key deleteMin(){
        return deleteMin(root).key;
    }

    public Node deleteMin(Node x){
        if(x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key delete(Key key){
        return delete(root,key).key;
    }

    public Node delete(Node x,Key key){
        int cmp = key.compareTo(x.key);

        if(cmp<0){
            x.left = delete(x.left,key);
            return delete(x.left,key);
        }
        else if(cmp>0){
            x.right = delete(x.right,key);
            return delete(x.right,key);
        }
        else {
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;

    }




}
