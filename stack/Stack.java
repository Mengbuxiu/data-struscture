package stack;

public interface Stack<E> {
    int getSize();
    boolean isEmpty();

    /**
     * 压入
     * @param e
     */
    void push(E e);

    /**
     * 弹出
     * @return
     */
    E pop();

    /**
     * 查看
     * @return
     */
    E peek();

}
