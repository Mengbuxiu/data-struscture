package unionfind;

public class UnionFind2 implements UF{
    private int[] parent;

    /**
     * @param size 要处理多少个元素
     */
    public UnionFind2(int size){
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            //初始化，每个节点都指向自己，每个节点都是一个独立的树
            //初始化的时候没有任何的连接
            parent[i] = i;
        }
    }

    /**
     * 寻找根节点;查找元素p对应的集合编号
     * 复杂度为O(h)，h为树的高度，传入p时，则为p所在节点对应的树的高度
     * @param p
     * @return
     */
    private int find(int p){
        if (p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound");
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }
    @Override
    public int getSize() {
        return parent.length;
    }

    //查看元素p和元素q是否属于同一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并元素p和元素q所属的集合高度
    //复杂度为O(h)，h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot)
            return;
        parent[pRoot] = qRoot;
    }
}
