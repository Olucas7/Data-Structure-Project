package DataStructure;

import java.util.Iterator;

/**
 *
 * @author oscarlucas7
 * @param <E>
 */
public interface List<E> {

    public boolean addFirst(E e);

    public boolean addLast(E e);

    public E removeFirst();

    public E removeLast();

    public int size();

    public boolean isEmpty();

    public void clear();

    public void add(int index, E element);

    public E remove(int index);

    public E get(int index);

    public boolean set(int index, E element);

    @Override
    public String toString();

}
