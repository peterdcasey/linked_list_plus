/**
 * Write a description of interface IList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface IList<T> {
    boolean add(int index, T item);
    boolean addFirst(T item);
    boolean addLast(T item);
    int size();
    void clear();
    boolean contains(T item);
    T get(int index);
    T getFirst();
    T getLast();
}
