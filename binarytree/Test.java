package binarytree;

public class Test {
    public static void main(String[] args){
        BST<Integer> bst = new BST<>();
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(1);
        bst.add(4);
        bst.add(6);
        bst.add(9);
        bst.levelOrder();
        bst.remove(7);
        bst.levelOrder();
//        System.out.println(bst.miniNum());
//        System.out.println(bst.maxNum());
        //bst.levelOrder();
        //System.out.println(bst.contains(9));
        //System.out.println(bst.size());
        //bst.preOrder();
        //System.out.println();
        //bst.inOrder();

        //bst.inOrderNR();
        //bst.postOrder();
        //System.out.println();
        //bst.preOrder();
       // System.out.println();
       // bst.preOrderNR();
    }
}
