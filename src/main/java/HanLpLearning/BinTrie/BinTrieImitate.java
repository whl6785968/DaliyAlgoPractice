package HanLpLearning.BinTrie;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BinTrieImitate<V> extends BaseNodeImitate<V> implements ITrieImitate, Externalizable {
    int size;

    public BinTrieImitate(){
        child = new BaseNodeImitate[65535+1];
        size = 0;
        status = Status.NOT_WORD_1;
    }

    public void put(String key,V value){
        if(key.length() == 0) return;
        BaseNodeImitate branch = this;
        char[] chars = key.toCharArray();

        for(int i=0;i<chars.length-1;i++){
            branch.addChild(new NodeByImitate(chars[i],null,Status.NOT_WORD_1));
            branch = branch.getChild(chars[i]);
        }

        if(branch.addChild(new NodeByImitate(chars[key.length()-1], value, Status.WORD_END_3))){
            ++size;
        };
    }

    @Override
    protected boolean addChild(BaseNodeImitate node) {
        boolean add = false;

        BaseNodeImitate target = child[node.getChar()];
        if(target == null){
            child[node.getChar()] = node;
            add = true;
        }
        else {
            switch (node.getStatus()){
                case UNDEFINED_0:
                    if(target.status != Status.NOT_WORD_1){
                        target.status = Status.NOT_WORD_1;
                        target.value = null;
                        add = true;
                    }
                    break;

                case NOT_WORD_1:
                    if(target.status == Status.WORD_END_3){
                        target.status = Status.WORD_MIDDLE_2;
                    }
                    break;

                case WORD_END_3:
                    if(target.status != Status.WORD_END_3){
                        target.status = Status.WORD_MIDDLE_2;
                    }

                    if(target.getValue() == null){
                        add = true;
                    }

                    target.value = node.getValue();
                    break;
            }
        }
        return add;
    }

    @Override
    public BaseNodeImitate getChild(char c) {
        return child[c];
    }

    @Override
    public boolean containsKey(String key) {
        BaseNodeImitate branch = this;
        char[] chars = key.toCharArray();

        for(int i = 0;i<chars.length;i++){
            if(branch == null) return false;
            branch = branch.getChild(chars[i]);
        }

        if(branch == null) return false;

        return true;
    }

    public Set<Map.Entry<String ,V>> prefixSearch(String key){
        StringBuilder sb = new StringBuilder(key.substring(0, key.length() - 1));
        Set<Map.Entry<String, V>> entrySet = new TreeSet<Map.Entry<String, V>>();

        char[] chars = key.toCharArray();
        BaseNodeImitate branch = this;
        for(int i = 0;i<chars.length;i++){
            if (branch == null) return entrySet;
            branch = branch.getChild(chars[i]);
        }

        if(branch == null) return entrySet;
        branch.walk(sb,entrySet);
        return entrySet;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
