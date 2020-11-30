package DataStructure;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author oscarlucas7
 */
public class DoubleLinkedCL<E> implements List<E> {

    //References
    private NodeCircularList<E> last;
    //Size Control
    private int effetiveSize;

    public DoubleLinkedCL() {
        this.last = null;
        effetiveSize = 0;
    }

    @Override
    public E addFirst(E element) {
        if (element != null) {
            NodeCircularList<E> newNode = new NodeCircularList<>(element);
            if (!isEmpty()) {
                if (effetiveSize == 1) {
                    newNode.setNext(last);
                    newNode.setPrevious(last);
                    last.setPrevious(newNode);
                    last.setNext(newNode);
                    effetiveSize++;
                    return last.getNext().getContent();
                } else {
                    newNode.setNext(last.getNext());
                    newNode.setPrevious(last);
                    last.setNext(newNode);
                    effetiveSize++;
                    return last.getNext().getContent();
                }
            } else {
                last = newNode;
                effetiveSize++;
                return last.getContent();
            }
        }
        else{
            return null;
        }
    }

    @Override
    public E addLast(E element) {
        if(element != null){
            if(isEmpty()){
                return addFirst(element);
            }else{
                NodeCircularList<E> newNode = new NodeCircularList<>(element);
                newNode.setNext(last.getNext());
                newNode.setPrevious(last);
                last.setNext(newNode);
                last=newNode;
                effetiveSize++;
                return last.getContent();
            }
        }else{
            return null;
        }
    }

    @Override
    public E add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return effetiveSize == 0;
    }

    @Override
    public int size() {
        return effetiveSize;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator iterator = new Iterator() {
            int cursor = 0;
            E element;
            NodeCircularList<E> node = last;
            @Override
            public boolean hasNext() {
                return cursor != effetiveSize;
            }

            @Override
            public E next() {
                element = node.getContent();
                node = node.getNext();
                cursor++;
                return element;
            }
        };
        return iterator;
    }
    
    public ListIterator<E> listIterator(){
        return null;
        
    }

    @Override
    public String toString() {
        if(!isEmpty()){
            String Circularlist = "[ ";
            for (NodeCircularList i = last.getNext(); i != last; i = i.getNext()) {
                
                    Circularlist += i.getContent() + ", ";
                
            }
            Circularlist += last.getContent() + " ]";
            return Circularlist;
        }
        return null;
    }
    
    public static void main(String [] args){
        DoubleLinkedCL<Integer> c1 = new DoubleLinkedCL<>();
//        c1.addLast(1);
//        c1.addLast(2);
//        c1.addLast(3);
        c1.addLast(4);
        c1.addFirst(0);
        c1.addFirst(-1);
//        c1.addLast(5);
        System.out.println(c1.size());
        System.out.println(c1);
    }
    

}
