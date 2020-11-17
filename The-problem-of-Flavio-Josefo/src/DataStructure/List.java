package DataStructure;

/**
 *
 * @author oscarlucas7
 */
public interface List<E> {
    
    public E addFirst(E element);
    
    public E addLast(E element);
    
    public E add(int index, E element);
    
    public E removeFirst();
    
    public E removeLast();
    
    public E remove(int index);
    
    public E get(int index);
    
    public E set(int index, E element);
    
    public void clear();
    
    public boolean isEmpty();
    
    public int size();
    
    
    
}
