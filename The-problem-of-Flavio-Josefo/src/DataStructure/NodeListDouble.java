package DataStructure;

/**
 *
 * @author oscarlucas7
 */
public class NodeListDouble<E> {

    E content;
    NodeListDouble<E> next;
    NodeListDouble<E> previous;

    public NodeListDouble(E content) {
        this.content = content;

    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public NodeListDouble<E> getNext() {
        return next;
    }

    public void setNext(NodeListDouble<E> next) {
        this.next = next;
    }

    public NodeListDouble<E> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeListDouble<E> previous) {
        this.previous = previous;
    }

}
