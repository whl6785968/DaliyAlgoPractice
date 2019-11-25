package Tire;

import java.util.Map;
import java.util.TreeMap;

public class Trie {
    public Node root;
    public int size;

    private static class Node{
        public boolean isWord;
        public Map<Character,Node> next;

        public Node(){
            next = new TreeMap<Character, Node>();
        }

        public Node(boolean isWord){
            this();
            this.isWord = isWord;
        }
    }

    public Trie(){
        root = new Node();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(String word){
        Node curr = root;
        char[] chars = word.toCharArray();

        for (char c:chars){
            Node next = curr.next.get(c);
            if(next == null){
                curr.next.put(c,new Node());
            }
            curr = curr.next.get(c);
        }

        if(!curr.isWord){
            size++;
            curr.isWord = true;
        }
    }

    public boolean contains(String word){
        Node curr = root;
        char[] chars = word.toCharArray();
        for (int i=0;i<word.length();i++){
            Node next = curr.next.get(chars[i]);
            if (next == null){
                return false;
            }
            curr = curr.next.get(chars[i]);
        }

        return curr.isWord;
    }

    public boolean containsPrefix(String prefix){
        Node curr = root;
        for (int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            Node next = curr.next.get(c);
            if (next == null){
                return false;
            }
            curr = curr.next.get(c);
        }

        return curr.isWord;
    }

    //三种情况
    //1.如果该单词为另一个单词前缀，只需要把该单词的最后一各节点的isword改为false
    //2.如果该单词的每一个节点都没有分支，删除整个单词
    //3.如果单词除了最后一个节点都有分支，删除最后一个节点
    public boolean remove(String word){
        Node multiChildNode = null;
        int multiChildNodeINdex = -1;

        Node curr = root;
        for (int i=0;i<word.length();i++){
            Node child = curr.next.get(word.charAt(i));
            if(child == null){
                return false;
            }

            if (child.next.size() > 0){
                multiChildNodeINdex = i;
                multiChildNode = curr;
            }

            curr = curr.next.get(word.charAt((i)));
        }

        if(curr.next.size()>0){
            if(curr.isWord){
                curr.isWord = false;
                size--;
                return true;
            }
            return false;
        }

        if(multiChildNodeINdex == -1){
            root.next.remove(word.charAt(0));
            size--;
            return true;
        }

        if(multiChildNodeINdex != word.length() - 1){
            multiChildNode.next.remove(word.charAt(multiChildNodeINdex+1));
            size--;
            return true;
        }

        return false;

    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.isEmpty());
        trie.add("see");
        trie.add("panda");
        trie.add("pain");
        trie.add("pad");
        trie.add("dog");

//        System.out.println(trie);
        System.out.println(trie.size());
        boolean pan = trie.remove("pad");
        System.out.println(pan);
        System.out.println(trie.size());
        System.out.println(trie.containsPrefix("pan"));
        System.out.println(trie.contains("pain"));
    }





}
