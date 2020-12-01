package DataStructure;

import DataStructure.NodeList;
import java.util.Iterator;

/**
 *
 * @author oscarlucas7
 */
public class LinkedList<E> implements List<E> {

    private NodeList<E> header;
    private NodeList<E> last;
    private int nodeNumber;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.nodeNumber = 0;
    }

    @Override
    public boolean addFirst(E e) {
        NodeList<E> new1 = new NodeList<>(e);
        if (e == null) {
            return false;
        } else if (this.isEmpty()) {
            setHeader(new1);
            setLast(new1);
            nodeNumber++;
            return true;
        } else {
            new1.setNext(header);
            this.setHeader(new1);
            nodeNumber++;
            return true;

        }

    }

    @Override
    public boolean addLast(E e) {
        NodeList new2 = new NodeList(e);

        if (e == null) {
            return false;
        } else if (this.isEmpty()) {
            setHeader(new2);
            setLast(new2);
            last.setNext(null);
        } else {
            last.setNext(new2);
            this.setLast(new2);
            last.setNext(null);
        }
        nodeNumber++;
        return true;
    }

    @Override
    public E removeFirst() {
        E nodeRemoved = header.getContent();

        if (this.isEmpty()) {
            return null;

        } else if (size() == 1) {
            header = null;
            last = null;
            nodeNumber--;
            return nodeRemoved;
        } else {
            setHeader(header.getNext());
            nodeNumber--;
        }
        return nodeRemoved;
    }

    @Override
    public E removeLast() {
        if (this.isEmpty()) {
            return null;
        } else if (size() == 1) {
            E nodeRemoved = last.getContent();
            header = null;
            last = null;
            nodeNumber--;
            return nodeRemoved;
        } else {
            E nodeRemoved = last.getContent();
            NodeList<E> nodeI = header;
            for (int i = 0; i < size(); i++) {
                NodeList<E> nodeN = nodeI.getNext();
                if (nodeN == last) {
                    setLast(nodeI);
                    nodeI.setNext(null);
                }
                nodeI = nodeN;
            }

            nodeNumber--;
            return nodeRemoved;
        }
    }

    @Override
    public int size() {
        return nodeNumber;
    }

    @Override
    public boolean isEmpty() {
        return header == null;
    }

    @Override
    public void clear() {
        setHeader(null);
        setLast(null);
        nodeNumber = 0;
    }

    @Override
    public void add(int index, E element) {
        if (index == 0) {
            addFirst(element);
        } else {
            NodeList nodeIterator = header;
            for (int i = 0; i < index - 1; i++) {
                nodeIterator = nodeIterator.getNext();
            }
            NodeList bNode = nodeIterator;
            NodeList afterNode = bNode.getNext();
            NodeList nodeNew = new NodeList<>(element);
            bNode.setNext(nodeNew);
            nodeNew.setNext(afterNode);
            nodeNumber++;

        }
    }

    @Override
    public E remove(int index) {
        if (index > this.size() - 1) {
            return null;

        } else {
            if (index == 0) {

                return removeFirst();

            } else if (index == this.size() - 1) {
                return removeLast();
            } else {
                NodeList<E> nodeConnect = null;
                int p = 0;
                for (NodeList<E> i = header; i != null; i = i.getNext()) {
                    if (p + 1 == index) {
                        nodeConnect = i.getNext().getNext();
                        i.setNext(nodeConnect);

                    }
                    p++;
                }
                nodeNumber--;
                if (nodeConnect != null) {
                    return nodeConnect.getContent();
                } else {
                    return null;
                }
            }

        }

    }

    @Override
    public E get(int index) {
        NodeList<E> nodeI = header;
        for (int i = 0; i < index - 1; i++) {
            nodeI = nodeI.getNext();
        }
        return nodeI.getContent();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        NodeList nodeItera = header;
        for (int i = 0; i < nodeNumber; i++) {
            if (i + 1 == nodeNumber) {
                builder.append(nodeItera.getContent());
            } else {
                builder.append(nodeItera.getContent() + ",");
                nodeItera = nodeItera.getNext();
            }
        }
        builder.substring(0, builder.length() - 1);
        builder.append("]");
        return builder.toString();

    }

    @Override
    public boolean set(int index, E element) {
        NodeList<E> nodeI = header;
        for (int i = 0; i < index - 1; i++) {
            nodeI = nodeI.getNext();
        }
        nodeI.setContent(element);
        return true;
    }

    public NodeList<E> getHeader() {
        return header;
    }

    public void setHeader(NodeList<E> header) {
        this.header = header;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    public int getNodeNumber() {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber) {
        this.nodeNumber = nodeNumber;
    }
}
