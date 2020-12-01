package DataStructure;

/**
 *
 * @author oscarlucas7
 */
public class NodeListDouble<E> {
   
    private E content;
    private NodeListDouble<E> next;
    private NodeListDouble<E> previous;

    public NodeListDouble(E content) {
        this.content = content;

    }

    public NodeListDouble(E content, NodeListDouble<E> next, NodeListDouble<E> previous) {
        this.content = content;
        this.next = next;
        this.previous = previous;
    }

    public void removeNode() {
        getPrevious().setNext(next);
        getNext().setPrevious(previous);
        setPrevious(null);
        setNext(null);
    }

    public void insertNode(NodeListDouble<E> node) {
        this.getPrevious().setNext(node);
        node.setNext(this);
        node.setPrevious(this.getPrevious());
        this.setPrevious(node);
    }

    /**
     * @return the content
     */
    public E getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(E content) {
        this.content = content;
    }

    /**
     * @return the next
     */
    public NodeListDouble<E> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
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
