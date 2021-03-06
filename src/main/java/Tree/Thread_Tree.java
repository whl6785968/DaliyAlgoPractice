package Tree;

class BiThTree<E>{
    E data;
    BiThTree<E> lchid,rchild;
    boolean ltag,rtag;

    public BiThTree(E data){
       this.data = data;
       this.lchid = null;
       this.rchild = null;
       this.ltag = false;
       this.rtag = false;
    }

}

public class Thread_Tree<T> {
    BiThTree<T> root;
    boolean link = false,thread = true;

    public Thread_Tree(){
        root = null;
    }

    BiThTree pre;

    public void inThreading(){
        inThreading(root);
    }
    //边中序遍历 边建立线索
    //当遍历到某个节点时，若该节点的前驱不为null且前驱的右孩子为null，则将前驱节点的右孩子设置为该节点
    //即记录每个节点的前驱节点，对于某节点的后继节点，找到再赋予

    //ltag为true表示其左孩子为该节点的前驱节点
    //rtag为true表示其右孩子为该节点的后继节点
    //先遍历到前驱设置其前驱，遍历到其后继设置其后继
    public void inThreading(BiThTree p){
        if(p!=null){
            inThreading(p.lchid);
            if(p.lchid == null){
                p.lchid = pre;
                p.ltag = thread;
            }

            if(pre !=null && pre.rchild == null){
                pre.rtag = thread;
                pre.rchild = p;
            }

            pre = p;

            inThreading(p.rchild);
        }
    }

    public void inOrderTraverse(){
        BiThTree p = root;
        while(p!=null){
            while (p.ltag == link){
                p = p.lchid;
            }
            System.out.println(p.data);

            while (p.rtag  == thread){
                p = p.rchild;
                System.out.println(p.data);
            }

            p = p.rchild;
            System.out.println();
        }
    }

    public static void main(String[] args) {


        Thread_Tree<String> aBiThrTree = new Thread_Tree<String>();
        aBiThrTree.root=new BiThTree<String>("A");                   //      A
        aBiThrTree.root.lchid=new BiThTree<String>("B");            //    /   \
        aBiThrTree.root.lchid.lchid=new BiThTree<String>("C");     //   B     D
        aBiThrTree.root.rchild=new BiThTree<String>("D");            //  /     / \
        aBiThrTree.root.rchild.lchid=new BiThTree<String>("E");     // C     E   F
        aBiThrTree.root.rchild.rchild=new BiThTree<String>("F");
        aBiThrTree.inThreading();
        long start = System.currentTimeMillis();
        aBiThrTree.inOrderTraverse();

        long end = System.currentTimeMillis();
        System.out.println(end-start);
//        aBiThrTree.inOrderTraverse2();
    }

}
