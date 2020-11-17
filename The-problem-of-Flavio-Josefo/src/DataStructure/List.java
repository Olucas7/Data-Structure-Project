package DataStructure;

/**
 *
 * @author oscarlucas7
 */
public interface List<E> {
    
    public E addFirst(E element);
    
    public E addLast(E element);
    
    public E add(int index, E element);
    
    public E removeFirst(E element);
    
    public E removeLast(E element);
    
    public E remove(int index, E element);
    
    public E get(int index);
    
    public E set(int index, E element);
    
    public void clear();
    
    public boolean isEmpty();
    
    public int size();
    
    
    
}
