package DataStructure;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author oscarlucas7
 */
public class CircularDouble<E> implements List<E> {

    private NodeListDouble<E> header;
    private NodeListDouble<E> last;
    private int realSize;

    @Override
    public boolean addFirst(E e) {
        NodeListDouble<E> addNewF = new NodeListDouble(e);

        if (this.isEmpty()) {
            addNewF.setNext(addNewF);
            addNewF.setPrevious(addNewF);
            setHeader(addNewF);
            setLast(addNewF);
            realSize++;

        } else {
            addNewF.setNext(header);
            addNewF.setPrevious(last);
            header.setPrevious(addNewF);
            last.setNext(header);
            setHeader(addNewF);
            realSize++;

        }

        return true;
    }

    @Override
    public boolean addLast(E e) {
        NodeListDouble<E> addNewL = new NodeListDouble(e);

        if (this.isEmpty()) {
            addNewL.setNext(addNewL);
            addNewL.setPrevious(addNewL);
            setHeader(addNewL);
            setLast(addNewL);
            realSize++;

        } else {
            addNewL.setNext(header);
            addNewL.setPrevious(last);
            header.setPrevious(addNewL);
            last.setNext(addNewL);
            setLast(addNewL);
            realSize++;
        }
        return true;
    }

    @Override
    public E removeFirst() {

        if (isEmpty()) {
            return null;
        } else {
            E eliminateF = getHeader().getContent();
            last.setNext(header.getNext());
            header.getNext().setPrevious(last);
            header.setNext(null);
            header.setPrevious(null);
            setHeader(header.getNext());
            realSize--;
            return eliminateF;
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            E eliminateLast = getLast().getContent();
            header.setPrevious(last.getPrevious());
            last.getPrevious().setNext(header);
            last.setNext(null);
            last.setPrevious(null);
            setLast(last.getPrevious());
            realSize--;
            return eliminateLast;
        }
    }

    @Override
    public int size() {
        return realSize;
    }

    @Override
    public boolean isEmpty() {
        return header == null;
    }

    @Override
    public void clear() {
        setHeader(null);
        setLast(null);
        realSize = 0;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E remove(int index) {
        if (isEmpty() || index < 0 || index >= realSize) {
            return null;
        }
        NodeListDouble<E> traveller = header;
        if (header == last) {
            header = last = null;
        } else if (index == 0) {
            NodeListDouble<E> temp = header;
            header = header.getNext();
            temp.setNext(null);
            realSize--;
            return temp.getContent();
        } else if (index == realSize - 1) {
            NodeListDouble<E> temp2 = last;
            last = last.getPrevious();
            last.setNext(null);
            temp2.setPrevious(null);
            realSize--;
            return temp2.getContent();
        } else {
            for (int i = 0; i < realSize; i++) {
                if (i + 1 == index) {
                    NodeListDouble<E> noWanted = traveller.getNext();
                    traveller.setNext(noWanted.getNext());
                    noWanted.getNext().setPrevious(traveller);
                    noWanted.setNext(null);
                    noWanted.setPrevious(null);
                    realSize--;
                    return noWanted.getContent();
                }
                traveller = traveller.getNext();
            }
        }
        return null;
    }

    private NodeListDouble<E> nodeIndexForward(int index) {
        if (isEmpty()) {
            return new NodeListDouble<>(null);
        }
        NodeListDouble<E> node = last.getNext();
        int i = 0;
        while (i < index) {
            node = node.getNext();
            i++;
        }
        return node;
    }

    private NodeListDouble<E> nodeIndexBackward(int index) {
        if (isEmpty()) {
            return new NodeListDouble<>(null);
        }
        NodeListDouble<E> node = last;
        int i = this.realSize - 1;
        while (i > index) {
            node = node.getPrevious();
            i--;
        }
        return node;
    }

    @Override
    public E get(int index) {
        NodeListDouble<E> node;
        if (isEmpty() || index < 0 || index >= realSize) {
            return null;
        } else {
            int cont = 0;
            node = header;
            while (cont != index) {
                cont++;
                node = node.getNext();
            }
            return node.getContent();
        }
    }

    @Override
    public boolean set(int index, E element) {
        NodeListDouble<E> node;
        if (index == 0) {
            last.getNext().setContent(element);
            return true;
        } else if (index - 1 == realSize) {
            last.setContent(element);
            return true;
        } else if (element == null || index >= realSize || index < 0) {
            return false;
        } else if (index >= (realSize - 1) / 2) {
            node = nodeIndexForward(index);
        } else {
            node = nodeIndexBackward(index);
        }
        node.setContent(element);
        return true;
    }

    public NodeListDouble<E> getHeader() {
        return header;
    }

    public void setHeader(NodeListDouble<E> header) {
        this.header = header;
    }

    public NodeListDouble<E> getLast() {
        return last;
    }

    public void setLast(NodeListDouble<E> last) {
        this.last = last;
    }

    public int getSize() {
        return realSize;
    }

    public void setSize(int size) {
        this.realSize = size;
    }
}
