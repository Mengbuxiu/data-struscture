package Array;

public class Array {
    private int[] data;//存储的数据
    private int size;//数组长度
    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity){
        data = new int[capacity];
        size = 0;
    }
    // 无参数的构造函数，默认数组的容量capacity=10
    public Array(){
        data = new int[10];
    }
    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组中的元素个数

    public int getSize() {
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0 ? true : false;
    }
    // 向所有元素后添加一个新元素
    public void addLast(int e){
        add(size,e);
    }
    // 在所有元素前添加一个新元素
    public void addFirst(int e){
        add(0,e);
    }
    // 在index索引的位置插入一个新元素e
    public void add(int index, int e){
        if (index < 0 || index > data.length)
            throw new IllegalArgumentException("数组下标越界");
        if (size == data.length){
           /* //扩容
            data = new int[(int) (getCapacity() * 1.25)];
            //复制元素
            for (int i = 0; i <data.length ; i++) {
                this.data[i] = data[i];
            }*/
            throw new IllegalArgumentException("Add failed. Array is 饱和.");
        }
        //从size的最大数到要插入的位置遍历，把前一个元素赋值给后一个元素
        for (int i = size - 1; i >= index; i--) {
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }
    public int get(int index){
        if (index < 0 || index > data.length)
            throw new IllegalArgumentException("数组下标越界");
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int e,int index){
        if (index < 0 || index > data.length)
            throw new IllegalArgumentException("数组下标越界");
        data[index] = e;
    }
    // 查找数组中是否有元素e
    public boolean contains(int e){
        for (int i = 0; i < size ; i++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }
    // 从数组中删除index位置的元素, 返回删除的元素
    // 从数组中删除第一个元素, 返回删除的元素
    // 从数组中删除最后一个元素, 返回删除的元素
    // 从数组中删除元素e
    public int remove(int index){
        if (index < 0 || index > data.length)
            throw new IllegalArgumentException("数组下标越界");
        int val = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i-1] = data[i];
            System.out.println(data[i]);
        }
        size --;
        return val;
    }



    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Array has [ ");
        for (int i = 0; i < data.length; i++) {
            stringBuilder.append(data[i]+" ");
        }
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}
