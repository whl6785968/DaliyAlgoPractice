package HanLpLearning.BinTrie;

import jdk.nashorn.internal.ir.BaseNode;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Set;

public abstract class BaseNodeImitate<V> implements Comparable<BaseNodeImitate> {
    static final Status[] ARRAY_STATUS = Status.values();

    protected BaseNodeImitate[] child;

    protected char c;

    protected V value;

    protected Status status;

    public Status getStatus(){
        return status;
    }

    public BaseNodeImitate<V> transition(String path,int begin){
        BaseNodeImitate curr = this;
        for(int i = begin;i<path.length();i++){
            curr = curr.getChild(path.charAt(i));
            if(curr == null || curr.status == Status.UNDEFINED_0) return null;
        }

        return curr;
    }

    public BaseNodeImitate<V> transition(char[] path,int begin){
        BaseNodeImitate curr = this;
        for(int i=begin;i<path.length;i++){
            curr = curr.getChild(path[i]);
            if(curr == null || curr.status == Status.UNDEFINED_0) return null;
        }

        return curr;
    }

    public BaseNodeImitate<V> transition(char path){
        BaseNodeImitate curr = this;
        curr = curr.getChild(path);
        if(curr == null || curr.status == Status.UNDEFINED_0) return null;
        return curr;
    }

    protected void walk(StringBuilder sb, Set<Map.Entry<String,V>> entrySet){
        sb.append(c);
        if(status == Status.WORD_MIDDLE_2 || status == Status.WORD_END_3){
            entrySet.add(new TrieEntry(sb.toString(),value));
        }
        if(child == null){
            return;
        }

        for(BaseNodeImitate node:child){
            if(node == null){
                continue;
            }

            node.walk(new StringBuilder(sb.toString()),entrySet);
        }
    }

    protected abstract boolean addChild(BaseNodeImitate baseNodeImitate);

    protected boolean hasChild(char c){
        return getChild(c) != null;
    }

    protected final V getValue(){
        return value;
    }

    protected final void setValue(V v){
        this.value = v;
    }

    protected char getChar(){
        return c;
    }

    public abstract BaseNodeImitate getChild(char c);

    public int compareTo(BaseNodeImitate o) {
        return compareTo(o.getChar());
    }

    public int compareTo(char other){
        if(this.c > other){
            return 1;
        }
        else if(this.c < other){
            return -1;
        }
        else {
            return 0;
        }
    }
    public class TrieEntry extends AbstractMap.SimpleEntry<String,V> implements Comparable<TrieEntry>{

        public TrieEntry(String key, V value) {
            super(key, value);
        }

        public int compareTo(TrieEntry o) {
            return getKey().compareTo(o.getKey());
        }
    }


    public enum Status{
        UNDEFINED_0,
        NOT_WORD_1,
        WORD_MIDDLE_2,
        WORD_END_3
    }
}
