package com.furui.tree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author furui
 * @date 2018/3/7 0007
 */
public class BinarySearchTree<T extends Comparable> implements Tree<T>  {
    /**
     * 根结点
     */
    private BinaryNode<T> root;

    public BinarySearchTree(BinaryNode<T> binaryNode){
        this.root = binaryNode;
    }

    public BinarySearchTree(String preOrPost, T[] list,T[] inList,int start,int end,int inStart,int inEnd) {
        if ("pre".equals(preOrPost)) {
            root = createBinarySearchTreeByPreIn(list, inList, start, end, inStart, inEnd);
        } else {
            root = createBinarySearchTreeByPostIn(list, inList, start, end, inStart, inEnd);
        }
    }
    /**
     * 判空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * 二叉树的结点个数
     *
     * @return
     */
    @Override
    public int size() {
        return size(root);
    }

    /**
     * 返回二叉树的高度或者深度,即结点的最大层次
     *
     * @return
     */
    @Override
    public int height() {
        return height(root);
    }

    /**
     * 先根次序遍历
     */
    @Override
    public String preOrder() {
//        String sb = preOrder(root);
//        if(sb != null && sb != "") {
//            sb = sb.toString().substring(0, sb.length() - 1);
//        }
//        return sb;
        return preOrderTraverse(root);
    }

    /**
     * 中根次序遍历
     */
    @Override
    public String inOrder() {
//        String sb = inOrder(root);
//        if(sb != null && sb != "") {
//            sb = sb.toString().substring(0, sb.length() - 1);
//        }
//        return sb;
        return inOrderTraverse(root);
    }

    /**
     * 后根次序遍历
     */
    @Override
    public String postOrder() {
//        String sb = postOrder(root);
//        if(sb != null && sb != "") {
//            sb = sb.toString().substring(0, sb.length() - 1);
//        }
//        return sb;
        return postOrderTraverse(root);
    }

    /**
     * 层次遍历
     */
    @Override
    public String levelOrder() {
        /**
         * 存放需要遍历的结点,左结点一定优先右节点遍历
         */
        Queue<BinaryNode<T>> queue=new LinkedBlockingDeque<>();
        StringBuffer sb = new StringBuffer();
        BinaryNode<T> p = this.root;

        while (p != null){
            //记录经过的结点
            sb.append("-" + p.data);

            //先按层次遍历结点,左结点一定在右结点之前访问
            if(p.left != null){
                //孩子结点入队
                queue.add(p.left);
            }

            if (p.right != null){
                queue.add(p.right);
            }
            //访问下一个结点
            p = queue.poll();
        }
        return sb.toString().replaceFirst("-", "");
    }

    /**
     * 将data 插入
     *
     * @param data
     * @return
     */
    @Override
    public void insert(T data) {
        if (data != null) {
            //插入操作
            root = insert(data, root);
        }
    }

    /**
     * 删除一个节点<br/>
     * ① 如果要删除的结点q恰好是叶子结点，那么它可以立即被删除<br/>
     * ② 如果要删除的结点q拥有一个孩子结点，则应该调整要被删除的父结点(p.left 或 p.right)指向被删除结点的孩子结点（q.left 或 q.right）<br/>
     * ③ 如果要删除的结点q拥有两个孩子结点，则删除策略是用q的右子树的最小的数据替代要被删除结点的数据，并递归删除用于替换的结点(此时该结点已为空)，此时二叉查找树的结构并不会被打乱，其特性仍旧生效。
     * 采用这样策略的主要原因是右子树的最小结点的数据替换要被删除的结点后可以满足维持二叉查找树的结构和特性，又因为右子树最小结点不可能有左孩子，删除起来也相对简单些。
     * @param data
     */
    @Override
    public void remove(T data) {
        if (data != null) {
            //删除操作
            root = delete(data, root);
        }
    }

    /**
     * 查找最大值
     *
     * @return
     */
    @Override
    public T findMin() {
        if(isEmpty()) {
            return null;
        }
        return findMin(root).data;
    }

    /**
     * 查找最小值
     *
     * @return
     */
    @Override
    public T findMax() {
        if(isEmpty()) {
            return null;
        }
        return findMax(root).data;
    }

    /**
     * 根据值找到结点
     *
     * @param data
     * @return
     */
    @Override
    public BinaryNode findNode(T data) {
        return findNode(data, root);
    }

    /**
     * 是否包含某个值
     *
     * @param data
     * @return
     */
    @Override
    public boolean contains(T data){
        return findNode(data, root) != null;
    }

    /**
     * 清空
     */
    @Override
    public void clear() {

    }

    /**
     * 前根/中根遍历构建二叉树
     * @param preList 前根遍历序列
     * @param inList 中根遍历序列
     * @param preStart 前续遍历序列头
     * @param preEnd 前续遍历序列尾
     * @param inStart 中续遍历序列头
     * @param inEnd 中续遍历序列尾
     * @return 根结点
     */
    private BinaryNode<T> createBinarySearchTreeByPreIn(T[] preList,T[] inList,int preStart,int preEnd,int inStart,int inEnd){

        //构建根结点
        BinaryNode<T> p = new BinaryNode<>(preList[preStart]);

        if(preStart == preEnd && inStart == inEnd){
            return p;
        }

        //查找中根序列的根结点下标root
        int root = 0;

        for (root = inStart; root < inEnd; root++){
            //查找到
            if (preList[preStart].compareTo(inList[root]) == 0){
                break;
            }
        }

        //左子树的长度
        int leftLenght = root - inStart;
        //右子树的长度
        int rightLenght = inEnd - root;

        //递归构建左子树
        if(leftLenght > 0){
            //preStart+leftLenght:前根左子树的结束下标
            p.left = createBinarySearchTreeByPreIn(preList, inList, preStart + 1,preStart + leftLenght, inStart, root - 1);
        }

        //递归构建右子树
        if(rightLenght > 0){
            p.right = createBinarySearchTreeByPreIn(preList, inList,preStart + leftLenght + 1,preEnd - 1,root + 1, inEnd);
        }

        return p;
    }


    /**
     * 后根/中根遍历构建二叉树
     * @param postList 后根遍历序列
     * @param inList 中根遍历序列
     * @param postStart 后续遍历序列头
     * @param postEnd 后续遍历序列尾
     * @param inStart 中续遍历序列头
     * @param inEnd 中续遍历序列尾
     * @return 根结点
     */
    private BinaryNode<T> createBinarySearchTreeByPostIn(T[] postList,T[] inList,int postStart,int postEnd,int inStart,int inEnd){

        //构建根结点
        BinaryNode<T> p = new BinaryNode<>(postList[postEnd]);

        if(postStart == postEnd && inStart == inEnd){
            return p;
        }

        //查找中根序列的根结点下标root
        int root = 0;

        for (root = inStart; root < inEnd; root++){
            //查找到
            if (postList[postEnd].compareTo(inList[root]) == 0){
                break;
            }
        }

        //左子树的长度
        int leftLenght = root - inStart;
        //右子树的长度
        int rightLenght = inEnd - root;

        //递归构建左子树
        if(leftLenght > 0){
            //postStart+leftLenght-1:后根左子树的结束下标
            p.left = createBinarySearchTreeByPostIn(postList, inList, postStart,postStart + leftLenght - 1, inStart,root-1);
        }

        //递归构建右子树
        if(rightLenght > 0){
            p.right = createBinarySearchTreeByPostIn(postList, inList,postStart+leftLenght,postEnd - 1,root+1, inEnd);
        }

        return p;
    }


    /**
     * 插入操作,递归实现
     * @param data 需要插入的数据
     * @param binaryNode 插入树的根节点
     * @return 插入后的根节点
     */
    private BinaryNode<T> insert(T data, BinaryNode<T> binaryNode){
        if(binaryNode == null){
            binaryNode = new BinaryNode<>(data,null,null);
        }
        //比较插入结点的值，决定向左子树还是右子树搜索
        int compareResult = data.compareTo(binaryNode.data);

        if (compareResult < 0){
            //左
            binaryNode.left = insert(data, binaryNode.left);
        }else if(compareResult > 0){
            //右
            binaryNode.right = insert(data, binaryNode.right);
        }
        //已有元素就没必要重复插入了
        return binaryNode;
    }

    /**
     * 删除操作,递归实现
     * 1.删除叶子结点(也就是没有孩子结点)
     * 2.删除拥有一个孩子结点的结点(可能是左孩子也可能是右孩子)
     * 3.删除拥有两个孩子结点的结点
     * @param data 需要删除的数据
     * @param binaryNode 树的根节点
     * @return 删除后的根节点
     */
    private BinaryNode<T> delete(T data, BinaryNode<T> binaryNode){
        if(binaryNode == null){
            return binaryNode;
        }
        //比较删除结点的值，决定向左子树还是右子树搜索
        int compareResult = data.compareTo(binaryNode.data);

        if (compareResult < 0){
            //左边查找删除结点
            binaryNode.left = delete(data, binaryNode.left);
        }else if(compareResult > 0){
            //右边查找删除结点
            binaryNode.right = delete(data, binaryNode.right);
        }else if (binaryNode.left != null && binaryNode.right != null){
            //已找到结点并判断是否有两个子结点(情况3)
            //中继替换，找到右子树中最小的元素并替换需要删除的元素值
            binaryNode.data = findMin(binaryNode.right).data;
            //移除用于替换的结点
            binaryNode.right = delete(binaryNode.data, binaryNode.right);
        }else {
            //拥有一个孩子结点的结点和叶子结点的情况
            binaryNode = (binaryNode.left != null) ? binaryNode.left : binaryNode.right;
        }
        return binaryNode;
    }

    /**
     * 查找最小值结点
     * @param binaryNode
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> binaryNode){
        if (binaryNode == null){
            //结束条件
            return null;
        } else if (binaryNode.left == null) {
            //如果没有左结点,那么t就是最小的
            return binaryNode;
        }
        return findMin(binaryNode.left);
    }

    /**
     * 查找最大值结点
     * @param binaryNode
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> binaryNode){
        if (binaryNode == null) {
            return null;
        } else if (binaryNode.right == null) {
            return binaryNode;
        }
        return findMax(binaryNode.right);
    }

    /**
     * 计算树的节点数（递归实现）
     * @param subtree
     * @return
     */
    private int size(BinaryNode<T> subtree){
        if (subtree == null){
            return 0;
        }else {
            int l = size(subtree.left);
            int r = size(subtree.right);
            //返回并加上根节点
            return l + r + 1;
        }
    }

    /**
     * 计算树的高度（递归实现）
     * @param subtree
     * @return
     */
    private int height(BinaryNode<T> subtree){
        if (subtree == null){
            return 0;
        }else {
            int l = height(subtree.left);
            int r = height(subtree.right);
            //返回并加上当前层
            return (l > r) ? (l + 1) : ( r + 1);
        }
    }

    /**
     * 先序遍历（递归实现）
     * @param subtree
     * @return
     */
    private String preOrder(BinaryNode<T> subtree){
        StringBuilder sb = new StringBuilder();
        if (subtree != null){
            //先访问根节点
            sb.append(subtree.data + "-");
            //再访问左子树
            sb.append(preOrder(subtree.left));
            //最后访问右子树
            sb.append(preOrder(subtree.right));
        }
        return sb.toString();
    }

    /**
     * 非递归的先根遍历<br/>
     * p从根结点开始，设置辅助容器Stack，当p非空或者栈非空时，循环执行下述操作，直到栈和二叉查找树为空： <br/>
     * ①若p非空，表示恰好到达p结点，访问p结点，再将p入栈，进入p的左子树。 <br/>
     * ②进入p的左子树后，若p为空但栈不为空，则表示已完整走完一条路径，则需返回寻找另一条路径，而此时返回的结点恰恰是刚才经过的最后一个结点，它已保存在栈顶，因此出栈该结点，赋值给p，再遍历p的右子树。<br/>
     * @return
     */
    private String preOrderTraverse(BinaryNode<T> subtree){
        StringBuffer sb = new StringBuffer();
        //构建用于存放结点的栈
        Stack<BinaryNode<T>> stack = new Stack<>();
        BinaryNode<T> p = subtree;
        while (p != null || !stack.isEmpty()){
            if (p != null){
                //访问该结点
                sb.append("-" + p.data );
                //将已访问过的结点入栈
                stack.push(p);
                //继续访问其左孩子，直到p为null
                p = p.left;
            }else { //若p=null 栈不为空,则说明已沿左子树访问完一条路径, 从栈中弹出栈顶结点,并访问其右孩子
                //获取已访问过的结点记录
                p = stack.pop();
                p = p.right;
            }
        }
        return sb.toString().replaceFirst("-", "");
    }

    /**
     * 中序遍历（递归实现）
     * @param subtree
     * @return
     */
    private String inOrder(BinaryNode<T> subtree){
        StringBuilder sb = new StringBuilder();
        if (subtree != null){
            //先访问左子树
            sb.append(inOrder(subtree.left));
            //再访问根节点
            sb.append(subtree.data + "-");
            //最后访问右子树
            sb.append(inOrder(subtree.right));
        }
        return sb.toString();
    }

    /**
     * 非递归的中序遍历<br/>
     * p从根结点开始，设置辅助容器Stack，当p非空或者栈非空时，循环执行下述操作，直到栈和二叉查找树为空： <br/>
     * ①若p非空，表示恰好到达p结点，访问p结点，再将p入栈，进入p的左子树。 <br/>
     * ②进入p的左子树后，若p为空但栈不为空，则表示已完整走完一条路径，则需返回寻找另一条路径，而此时返回的结点恰恰是刚才经过的最后一个结点，它已保存在栈顶，因此出栈该结点，赋值给p，再遍历p的右子树。<br/>
     * @return
     */
    private String inOrderTraverse(BinaryNode<T> subtree){
        StringBuffer sb = new StringBuffer();
        //构建用于存放结点的栈
        Stack<BinaryNode<T>> stack = new Stack<>();
        BinaryNode<T> p = subtree;
        while (p != null || !stack.isEmpty()){
            if (p != null){
                //将已访问过的结点入栈
                stack.push(p);
                //继续访问其左孩子，直到p为null
                p = p.left;
            }else { //若p=null 栈不为空,则说明已沿左子树访问完一条路径, 从栈中弹出栈顶结点,并访问其右孩子
                //获取已访问过的结点记录
                p = stack.pop();
                sb.append("-" + p.data );
                p = p.right;
            }
        }
        return sb.toString().replaceFirst("-", "");
    }

    /**
     * 后序遍历（递归实现）
     * @param subtree
     * @return
     */
    private String postOrder(BinaryNode<T> subtree){
        StringBuilder sb = new StringBuilder();
        if (subtree != null){
            //先访问左子树
            sb.append(postOrder(subtree.left));
            //再访问右子树
            sb.append(postOrder(subtree.right));
            //最后访问根节点
            sb.append(subtree.data + "-");

        }
        return sb.toString();
    }

    /**
     * 非递归的后序遍历<br/>
     * @return
     */
    private String postOrderTraverse(BinaryNode<T> subtree){
        StringBuffer sb = new StringBuffer();
        //构建用于存放结点的栈
        Stack<BinaryNode<T>> stack = new Stack<>();

        BinaryNode<T> currentNode = this.root;
        BinaryNode<T> prev=this.root;

        while (currentNode != null || !stack.isEmpty()){
            //把左子树加入栈中,直到叶子结点为止
            while (currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.left;
            }

            //开始访问当前结点父结点的右孩子
            if(!stack.isEmpty()){
                //获取右孩子，先不弹出
                BinaryNode<T> temp=stack.peek().right;
                //先判断是否有右孩子或者右孩子是否已被访问过
                if(temp == null || temp == prev){
                    //如果没有右孩子或者右孩子已被访问,则弹出父结点并访问
                    currentNode = stack.pop();
                    //访问
                    sb.append("-" + currentNode.data);
                    //记录已访问过的结点
                    prev = currentNode;
                    //置空当前结点
                    currentNode = null;
                }else {
                    //有右孩子,则开始遍历右子树
                    currentNode = temp;
                }
            }

        }
        return sb.toString().replaceFirst("-", "");
    }

    /**
     * 根据值查找节点（递归）
     * @param data
     * @param binaryNode
     * @return
     */
    private BinaryNode<T> findNode(T data, BinaryNode<T> binaryNode){
        BinaryNode<T> targetNode = binaryNode;
        if(data == null || binaryNode == null){
            return null;
        }else{
            int compareResult = data.compareTo(binaryNode.data);
            if(compareResult < 0){
                targetNode = findNode(data, binaryNode.left);
            } else if (compareResult > 0) {
                targetNode = findNode(data, binaryNode.right);
            }else {
                targetNode = binaryNode;
            }
        }
        return targetNode;
    }
}
