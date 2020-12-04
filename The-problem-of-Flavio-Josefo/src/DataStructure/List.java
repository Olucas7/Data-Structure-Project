package DataStructure;

import java.util.Iterator;

/**
 *
 * @author oscarlucas7
 * @param <E>
 */
public interface List<E> extends Iterable<E>{

    boolean addFirst(E e);

    boolean addLast(E e);

    E removeFirst();

    E removeLast();

    boolean remove(E element);

    int size();

    E getFirst();

    E getLast();

    void clear();

    E remove(int index);

    E get(int index);

}
