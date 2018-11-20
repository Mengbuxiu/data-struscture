package unionfind;

public class UnionFind1 implements UF{
    private int[] id;
    public UnionFind1(int size){
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    private int find(int p){
        if (p < 0 || p >= id.length)
            throw new IllegalArgumentException("p is out of bound");
        return id[p];
    }
    //查看 元素 p 和 q 是否属于一个集合
    // O(1)复杂度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
    //合并元素p和元素q所属的集合
    // O(n)复杂度
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID){
            return;
        }
        //合并过程需遍历一遍所有元素，将两个元素的所属集合编号合并
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID)
                id[i] = qID;
        }
    }
}
