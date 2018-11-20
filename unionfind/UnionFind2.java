package unionfind;

public class UnionFind2 implements UF{
    private int[] parent;

    /**
     * @param size 要处理多少个元素
     */
    public UnionFind2(int size){
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;//初始化，每个节点都指向自己，每个节点都是一个独立的树
        }
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return false;
    }

    @Override
    public void unionElements(int p, int q) {

    }
}
