package binarytree;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;//根节点
    private int size;

    public BST(){
        root = null;
        size = 0;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void add(E e){
            root = add(root,e);
    }
    //向以node为根的二分搜索树中插入元素，递归
    private Node add(Node node, E e){
        //此处提取了共性-》判空，如果不提取共性，
        // 那么判空的位置有：1.一颗完全空的二叉树，要添加第一个节点时
        //2.给左子树添加节点
        //3.右子树添加节点
        if (node == null){
            size ++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0){
            node.left = add(node.left,e);
        }
        else if (e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }
        return node;
    }
    public boolean contains(E e){
        return contains(root,e);
    }
    //是否包含元素
    private boolean contains(Node node, E e) {
        if (node == null)
            return false;
        if (e.compareTo(node.e) == 0){
            return true;
        }
        else if (e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }
        else
            return contains(node.right,e);
    }
    /**
     * 递归前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 递归中序遍历
     */
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.print("[" + node.e + "], ");
        inOrder(node.right);
    }

    /**
     * 递归后序遍历
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.print("<" + node.e + ">, ");
    }

    /**
     * 非递归前序遍历
     */
    public void preOrderNR(){
        if (root == null)
            return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.print(cur.e +" ");
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    /**
     * 层序遍历,队列
     * 思路：每次 add -> remove -> add -> add -> remove -> add -> add
     * 移除一个节点的同时会将这两个节点的左右子树都加入到队列中
     */
    public void levelOrder(){
        if (root == null)
            return;
       Queue<Node> q = new LinkedList<>();
       q.add(root);
       while(!q.isEmpty()){
           Node cur = q.remove();
           System.out.print(cur.e + " ");
           if (cur.left != null)
               q.add(cur.left);
           if (cur.right != null)
               q.add(cur.right);
       }
        System.out.println();
    }
    public void remove(E e){
        root = remove(root,e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if (e.compareTo(node.e) > 0 ){
            node.right = remove(node.right, e);
            return node;
        }
        else {
            // 待删除节点左子树为空的情况
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = miniNum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    /**
     * 删除最小值
     * @return
     */
    public E removeMin(){
        E ret = miniNum();
        root = removeMin(root);
        return ret;
    }

    /**
     * 思路：最小值为左下角的值，如果是满二叉树直接删左下角的值，但是不满时，
     * 比如没有左子树但是有右子树，这时候的节点为最小值，需保存右子树，然后删除右子树，然后把右子树拼到上个节点的左子树去
     * 整体思路：无论是否是满二叉，都需要 node.left == null 这个条件来判断当前结点是否是最小值
     * 只要 == null，就说明当前节点就是最小值，至于右节点是否为空并不影响结果，为什么？
     * ① 右节点不为空，执行if内的语句就可置空（删除）右子树，并返回右子树
     * ② 为空，则说明当前节点就是最小值，return null，给上层节点的左子树赋值时，会直接赋值为null，相当于删除了最小值
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null){//判断当前结点是否是最小值
            Node rightNode = node.right;//保存右子树
            node.right = null;//删除右子树
            size --;
            return rightNode;
        }
        node.left = removeMin(node.left);//把右子树拼到上个节点的左子树去
        return node;
    }

    public E removeMax(){
        E ret = maxNum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null){//判断当前结点是否是最小值
            Node rNode = node.left;//保存左子树
            node.left = null;//删除左子树
            size --;
            return rNode;
        }
        node.right = removeMax(node.right);//把左子树拼到上个节点的右子树去
        return node;
    }

    /**
     * 最大值即最右下角的值
     * @return
     */
   public E maxNum(){
       if (root == null)
           throw new IllegalArgumentException("root is null");
       return maxNum(root).e;
   }

    private Node maxNum(Node node) {
       if (node.right == null)
           return node;
       return maxNum(node.right);
    }

    /**
     * 正常二叉树的最小值，就是最左下角节点的值
     * 递归的本质：将问题细化为更小的问题
     * 写法：有出口，有需重复执行的逻辑
     * @return
     */
    public E miniNum(){
        if (root == null)
            throw new IllegalArgumentException("root is null");
        return miniNum(root).e;
    }

    private Node miniNum(Node node) {
       if (node.left == null)
           return node;
        return  miniNum(node.left);
    }

   /* @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateString(root, 0, res);
        return res.toString();
    }*/

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateString(node.left, depth + 1, res);
        generateString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for(int i = 0 ; i < depth ; i ++)
            res.append("-");
        return res.toString();
    }
}
