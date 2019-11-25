package HanLpLearning.BinTrie;

import org.apache.jena.base.Sys;

public class NodeByImitate<V> extends BaseNodeImitate {

    protected boolean addChild(BaseNodeImitate node) {
        boolean add = false;
        if(child == null){
            child = new BaseNodeImitate[0];
        }

        int index = ArrayTooImitate.binarySearch(child, node);

        if(index >= 0){
            BaseNodeImitate target = child[index];

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
        else {
            BaseNodeImitate newbaseNodeImitate[] = new BaseNodeImitate[child.length + 1];
            int insert = -(index + 1);
            System.arraycopy(child,0,newbaseNodeImitate,0,insert);
            System.arraycopy(child,insert,newbaseNodeImitate,insert+1,child.length-insert);
            newbaseNodeImitate[insert] = node;
            child = newbaseNodeImitate;
            add = true;
        }

        return add;
    }

    public NodeByImitate(char c,V value,Status status){
        this.c = c;
        this.value = value;
        this.status = status;
    }

    public NodeByImitate(){}

    public BaseNodeImitate getChild(char c) {
        if(child == null)
        {
            return null;
        }

        int index = ArrayTooImitate.binarySearch(child, c);
        if(index < 0) return null;

        return child[index];
    }

    public int compareTo(Object o) {
        return 0;
    }
}
