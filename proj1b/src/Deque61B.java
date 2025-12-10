import java.util.List;

/**
 * Created by hug on 2/4/2017. Methods are provided in the suggested order
 * that they should be completed.
 */
public interface Deque61B<T> {

    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    /**

     * 将 {@code x} 添加到双端队列的前端。假设 {@code x} 永远不会为空。

     *
     * @param x 要添加的项

     */
    void addFirst(T x);

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    void addLast(T x);

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    /**

     * 返回双端队列的列表副本。不会修改双端队列本身。

     *
     * @return 双端队列的新列表副本。

     */
    List<T> toList();

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    /**

     * 如果双端队列为空，则返回 true。不会修改双端队列的内容。

     *
     * @return 如果双端队列没有元素，则返回 true；否则返回 false。

     */
    boolean isEmpty();

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    int size();

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    /**

     * 如果双端队列的头部元素存在，则移除并返回该元素。

     *
     * @return 移除的元素，否则返回 {@code null}。

     */
    T removeFirst();

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    T removeLast();

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    /**

     * Deque61B 抽象数据类型通常没有 get 方法，

     * 但我们添加了这个额外的操作，以便为您提供一些

     * 额外的编程练习。迭代地获取元素。返回

     * 如果索引超出范围，则返回 null。不会修改双端队列。

     *
     * @param index 要获取的索引

     * @return 双端队列中位于 {@code index} 处的元素

     */
    T get(int index);

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque;
     */
    T getRecursive(int index);
}