package segmentrtree;

public class SegmentTree<E> {
    private E[] tree;//线段树数据
    private E[] data;//数组数据
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        //一个集合有N个元素，线段树需要4N的空间才可以装下
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0,0,arr.length - 1);
    }

    public int getSize(){
        return data.length;
    }
    public E get(int index){
        if (index < 0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2 * index + 1;
    }
    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2 * index + 2;
    }
    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r){
        if (l == r){ //当 l == r 时说明节点段中只剩下一个节点元素，赋值，返回
            tree[treeIndex] = data[l];
            return;
        }
        //左子树节点元素
        int leftTreeIndex = leftChild(treeIndex);
        //右子树节点元素
        int rightTreeIndex = rightChild(treeIndex);
        //一刀两断
        int mid = l + (r - l)/2; //防止整型溢出
        buildSegmentTree(leftTreeIndex,l,mid);
        buildSegmentTree(rightTreeIndex,mid + 1,r);

        //添加父节点元素
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
        //System.out.println(tree[treeIndex]);
    }
    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR){
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal");
        return query(0, 0, data.length - 1, queryL, queryR);
    }
    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];
        int mid = l + (r - l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        //如果查询区间在右区间
        if (queryL >= mid + 1)
            return query(rightTreeIndex,mid + 1,r,queryL,queryR);
        //如果查询区间在左区间
        else if (queryR <= mid)
            return query(leftTreeIndex,l,mid,queryL,queryR);
        //如果查询区间在同时在左右区间
        E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
        E rightResult = query(rightTreeIndex,mid + 1,r,mid + 1,queryR);
        return merger.merge(leftResult,rightResult);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null){
                res.append(tree[i]);
            }
            else
                res.append("null");
            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
