package com.furui.tree;

/**
 * 二叉树结点
 * @author furui
 * @date 2018/3/7 0007
 */
public class BinaryNode<T extends Comparable>{

    /**
     * 左结点
     */
    public BinaryNode<T> left;

    /**
     * 右结点
     */
    public BinaryNode<T> right;

    public T data;

    public BinaryNode(T data, BinaryNode left, BinaryNode right){
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data){
        this(data,null,null);

    }

    /**
     * 判断是否为叶子结点
     * @return
     */
    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }
}
