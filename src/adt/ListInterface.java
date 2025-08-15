/**
 *
 * @author Choi Kah Wai
 */

package adt;

public interface ListInterface<T> {
    T get(int index);
    int size();
    void add(T item);
    boolean isEmpty();

}
