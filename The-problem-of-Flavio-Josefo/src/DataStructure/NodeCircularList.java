package DataStructure;

/**
 *
 * @author oscarlucas7
 */
public class NodeCircularList<E> {
    private NodeCircularList<E> next;
    private NodeCircularList<E> previous;
    private E content;

    public NodeCircularList(E content){
        this.content = content;
        this.next = this;
        this.previous = this;
    }
    
    public NodeCircularList<E> getNext() {
        return next;
    }

    public void setNext(NodeCircularList<E> next) {
        this.next = next;
    }

    public NodeCircularList<E> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeCircularList<E> previous) {
        this.previous = previous;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }
    
    
    
}
