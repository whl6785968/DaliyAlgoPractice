package Tire;

import java.util.ArrayList;
import java.util.List;

public class DoubleArrayTrieImatate {
    private final static int BUF_SIZE = 16384;
    private final static int UNIT_SIZE = 8;

    public static class Node{
        int code;
        int depth;
        int left;
        int right;
    }

    private int check[];
    private int base[];
    private boolean used[];
    private int size;
    private int alloSize;
    private List<String> key;
    private int keySize;
    private int length[];
    private int value[];
    private int progress;
    private int nextCheckPos;
    int error_;


    public DoubleArrayTrieImatate(){
        check = null;
        base = null;
        used = null;
        size = 0;
        alloSize = 0;
        error_ = 0;
    }

    private int resize(int newSize){
        int base1[] = new int[newSize];
        int check1[] = new int[newSize];
        boolean used1[] = new boolean[newSize];

        if(alloSize>0){
            System.arraycopy(base,0,base1,0,alloSize);
            System.arraycopy(check,0,check1,0,alloSize);
            System.arraycopy(used,0,used1,0,alloSize);
        }

        base = base1;
        check = check1;
        used = used1;

        return alloSize = newSize;
    }

    public int build(List<String> key){
        return build(key,null,null,key.size());
    }

    public int build(List<String> _key,int _length[],int _value[],int _keySize ){
        if(_key.size()<_keySize || _key == null){
            return 0;
        }

        key = _key;
        length = _length;
        value = _value;
        keySize = _keySize;
        progress = 0;

        resize(65532*32);

        base[0] = 1;
        nextCheckPos = 0;
        Node root = new Node();
        root.left = 0;
        root.right = keySize;
        root.depth = 0;

        List<Node> sibling = new ArrayList<Node>();
        fetch(root,sibling);
        insert(sibling);

        return 0;
    }

    public int fetch(Node parent,List<Node> sibling){
        if(error_<0){
            return 0;
        }
        //前驱节点位置
        int prev = 0;

        for(int i=parent.left;i<parent.right;i++){
            //若词的长度小于父节点的深度，不做处理，因为词的每个字是一个节点，如一举，深度一定为2，若其父节点
            //的深度一定小于2
            if((length != null?length[i]:key.get(i).length()) < parent.depth){
                continue;
            }
            String tmp = key.get(i);

            //保存当前节点的编码code
            int cur = 0;
            //如果当前词的长度等于parent的深度，表示该词为终结词
            //即本来depth到3了，应该是长度为3的词，但是该词还是2，表明该词已经到了结尾
            if((length != null?length[i]:tmp.length()) != parent.depth){
                cur = (int) tmp.charAt(parent.depth) + 1;
            }

            //如果前驱节点大于当前位置，报错
            if(prev > cur){
                error_ = 3;
                return 0;
            }

            if(cur != prev || sibling.size() == 0){
                Node curr_node = new Node();
                curr_node.depth = parent.depth + 1;
                curr_node.code = cur;
                curr_node.left = i;
                //若当前节点不等于前驱，但是兄弟节点不为空
                //后一个兄弟节点的left等于前一兄弟节点的right
                if(sibling.size() != 0){
                    sibling.get(sibling.size()-1).right = i;
                }
                sibling.add(curr_node);
            }

            prev = cur;
        }

        if(sibling.size() != 0){
            sibling.get(sibling.size()-1).right = parent.right;
        }
        return sibling.size();
    }
    //两个数组
    //base记住节点的begin值
    //check记录词转移
    private int insert(List<Node> sibling){
        if(error_ < 0){
            return 0;
        }

        int begin = 0;

        int pos = ((sibling.get(0).code > nextCheckPos) ? sibling.get(0).code + 1:nextCheckPos)-1;

        int nonZeronum = 0;
        int first = 0;

        if(alloSize < pos){
            resize(pos+1);
        }

        outer:while (true){
            pos++;

            if(alloSize < pos){
                resize(pos + 1);
            }

            if(check[pos] != 0){
                nonZeronum++;
                continue ;
            }
            //若是第一个兄弟节点，则将第一个位置记录下来
            else if(first == 0){
                first = 1;
                nextCheckPos = pos;
            }

            //找到一个使得check[begin + a1...an] = 0的begin值;
            //且该begin值要满足不被使用
            //即寻找一个begin值，在该begin处没被使用且能够使得check[begin + a1...an] = 0
            begin = pos - sibling.get(0).code;

            if (alloSize <= (begin + sibling.get(sibling.size() - 1).code)) {
                // progress can be zero
                double l = (1.05 > 1.0 * keySize / (progress + 1)) ? 1.05 : 1.0
                        * keySize / (progress + 1);
                resize((int) (alloSize * l));
            }

            if(used[begin]){
                continue ;
            }
            for(int i=1;i<sibling.size();i++){
                if(check[begin+sibling.get(i).code] != 0){
                    continue outer;
                }
            }

            break ;
        }

        //如果在next_check_pos和check之间check 的非0数量大于某一个阈值
        //用check写入新的next_check_pos
        if(1.0*nonZeronum / (pos-nextCheckPos+1)>=0.95){
            nextCheckPos = pos;
        }

        used[begin] = true;

        size = (size > begin + sibling.get(sibling.size() - 1).code + 1) ? size
                : begin + sibling.get(sibling.size() - 1).code + 1;

        //词转移
        for(int i=0;i<sibling.size();i++){
            check[begin + sibling.get(i).code] = begin;
        }

        for (int i=0;i<sibling.size();i++){
            List<Node> newSibiling = new ArrayList<Node>();
            //如果没有子节点了，则返回一个负值标志着到了叶节点，即找到完整单词串
            if(fetch(sibling.get(i),newSibiling) == 0){
                base[begin + sibling.get(i).code] = value != null ? (-value[begin+sibling.get(i).left]-1):
                        (-sibling.get(i).left-1);
                //防止越界，即左节点不可能小于0
                if (value != null && (-value[sibling.get(i).left] - 1) >= 0) {
                    error_ = -2;
                    return 0;
                }

                progress++;
            }
            else {
                int h = insert(newSibiling);
                //即父节点接受了一个begin转移至子节点的开始
                base[begin + sibling.get(i).code] = h;
            }
        }
        return begin;
    }

    public List<Integer> commonPrefixSearch(String key){
        return commonPrefixSearch(key,0,0,0);
    }

    public List<Integer> commonPrefixSearch(String key,int pos,int len,int nodePos){
        if(len <= 0){
            len = key.length();
        }
        if(nodePos <= 0){
            nodePos = 0;
        }
        System.out.println("nodePos is " + nodePos);

        List<Integer> result = new ArrayList<Integer>();
        char[] keyChars = key.toCharArray();

        int b = base[nodePos];
        int p;
        int n;

        for(int i=pos;i<len;i++){
            //因为父节点的位置值是子节点传递的，而父节点的位置值是子节点找到的(check)
            //故先把父节点的位置赋予一个暂时变量里，然后根据该位置值去找子节点的check值和base值
            p = b;
            //查询自己的子节点
            n = base[p];
            //满足父子关系且
            if(b == check[p] && n<0){
                result.add(-n-1);
            }

            p = b + (int)(keyChars[i])+1;
            //提前终止查询，都不是一个前缀，就不用再找了
            if(b == check[p]){
                b = base[p];
            }
            else {
                return result;
            }
        }

        p = b;
        n = base[p];

        if (b == check[p] && n < 0) {
            result.add(-n - 1);
        }

        return result;
    }
}
