package DataStructure;

import DataStructure.NodeList;
import java.util.Iterator;

/**
 *
 * @author oscarlucas7
 */
public class LinkedList<E> implements List<E> {

    private NodeList<E> first;
    private NodeList<E> last;
    private int effectiveSize;

    public LinkedList() {
        this.first = null;
        this.last = null;
        effectiveSize = 0;
    }

    @Override
    public E addFirst(E element) {
        if (!(element == null)) {
            NodeList<E> newNode = new NodeList<>(element);
            if (isEmpty()) {
                first = newNode;
                last= newNode;
                effectiveSize++;
                return newNode.getContent();
            } else {
                newNode.setNext(first);
                first = newNode;
                effectiveSize++;
                return newNode.getContent();

            }
        }
        return null;
    }

    @Override
    public E addLast(E element) {
        if(!(element == null)){
            NodeList<E> newNode = new NodeList<>(element);
            if(isEmpty()){
                addFirst(element);
            }else{
                last.setNext(newNode);
                last = newNode;
                effectiveSize++;
                return newNode.getContent();
            }
            
        }
        return null;
    }

    @Override
    public E add(int index, E element) {
        if((index>=0 && index < effectiveSize) || element != null){
            NodeList<E> newNode = new NodeList<>(element);
            if(index == 0)
                return addFirst(element);
            int cursor=0;
            for (NodeList<E> i = this.first; i!=null ; i = i.getNext()) {
                if(cursor == index-1){
                    newNode.setNext(i.getNext());
                    i.setNext(newNode);
                    effectiveSize++;
                    return newNode.getContent();
                }else{
                    cursor++;
                }
            }
        }
        return null;
    }

    @Override
    public E removeFirst() {
        if(!isEmpty()){
            NodeList<E> removedNode = this.first;
           if(effectiveSize == 1){
                this.first = this.last = null;
                effectiveSize--;
                return removedNode.getContent();
            }else{
                this.first = this.first.getNext();
                effectiveSize--;
                removedNode.setNext(null);
                return removedNode.getContent();
            }
           
        }else
            return null;
    }

    @Override
    public E removeLast() {
        if(!isEmpty()){
            NodeList<E> removedNode = this.last;
            if(effectiveSize==1)
                return removeFirst();
            else{
                for (NodeList<E> i = first; i != null; i = i.getNext()) {
                    if(i.getNext() == last){
                        i.setNext(null);
                        last = i;
                        removedNode.setNext(null);
                    }
                }
                effectiveSize--;
                return removedNode.getContent();
            }
        }
        return null;
    }

    @Override
    public E remove(int index) {
        if(index>=0 && index < effectiveSize){
            if(index == 0)
                return removeFirst();
            else if(index == effectiveSize-1)
                return removeLast();
            else{
                int cursor=0;
                for (NodeList<E> i = first; i != null; i = i.getNext()) {
                    if(cursor == index-1){
                        NodeList<E> removedNode = i.getNext();
                        i.setNext(removedNode.getNext());
                        removedNode.setNext(null);
                        effectiveSize--;
                        return removedNode.getContent();
                    }
                    else{
                        cursor++;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public E get(int index) {
        if(index >= 0 && index < effectiveSize){
            if(index==0)
                return first.getContent();
            else if(index == effectiveSize-1)
                return last.getContent();
            else{
                int cursor=0;
                for (NodeList<E> i = first; i != null; i = i.getNext()) {
                    if(cursor==index){
                        return i.getContent();
                    }else
                        cursor++;
                }
            }
        }return null;
    }

    @Override
    public E set(int index, E element) {
         if((index >= 0 && index <effectiveSize) || element != null){
            if(index==0){
                first.setContent(element);
                return first.getContent();
            }else if(index == effectiveSize-1){
                last.setContent(element);
                return last.getContent();
            }else{
                int cursor=0;
                for (NodeList<E> i = first.getNext(); i != last; i = i.getNext()) {
                    if(cursor == index){
                        i.setContent(element);
                        return i.getContent();
                    }else{
                        cursor++;
                    }
                }
            }
         }
         return null;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        effectiveSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator iterator = new Iterator() {
            int cursor = 0;
            E element;
            NodeList<E> node = first;
            @Override
            public boolean hasNext() {
                return cursor != effectiveSize;
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

    @Override
    public String toString() {
        if(!isEmpty()){
            String arraylist = "[ ";
            for (NodeList i = first; i != null; i = i.getNext()) {
                if ((i != last)) {
                    arraylist += i.getContent() + ", ";
                } else {
                    arraylist += last.getContent() + " ]";
                }
            }
            return arraylist;
        }
        return null;
    }
    
}
