package main.java.Tree;

import java.util.ArrayList;
import java.util.List;

public class Binary_Tree {
    public Binary_Tree left;
    public Binary_Tree right;
    public Binary_Tree root;

    public Object data;
    public List<Binary_Tree> datas;

    public Binary_Tree(Binary_Tree left, Binary_Tree right, Object data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public Binary_Tree(){}

    public Binary_Tree(Object object){
        this(null,null,object);
    }

    public void buildTree(Object[] objects){
        datas = new ArrayList<Binary_Tree>();

        for(Object o : objects){
            datas.add(new Binary_Tree(o));
        }
        root = datas.get(0);
        for(int i=0;i<datas.size()/2;i++){
           datas.get(i).left = datas.get(2*i+1);

           if(2*i+2<datas.size()){
               datas.get(i).right = datas.get(2*i+2);
           }

        }
    }

    //前序遍历
    //找到就输出，先左后右(同级先输出)
    //简而言之，甭管左右都有没有节点，都先输出当前节点
    //若左右都没有节点，结束函数
    public void preOrder(Binary_Tree root){
        if(root!=null){
            System.out.println(root.data);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    //遍历到底，直到左边的节点没有或者全部遍历完此环才结束，然后输出，再遍历右边的节点
    //若右边也没有节点，结束函数，上一环就能继续
    //简而言之，左面没有输出，右面没有结束
    public void inOrder(Binary_Tree root){
        if(root!=null){
            inOrder(root.left);
            System.out.println(root.data);
            inOrder(root.right);
        }
    }

    //左右都没有，才输出
    public void aftOrder(Binary_Tree root){
        if(root!=null){
            aftOrder(root.left);
            aftOrder(root.right);
            System.out.println(root.data);
        }
    }


    public static void main(String[] args) {
        Binary_Tree binary_tree = new Binary_Tree();
        Object[] objects = {2,4,5,7,1,6,12,32,51,22};
        binary_tree.buildTree(objects);
        binary_tree.preOrder(binary_tree.root);
    }
}
