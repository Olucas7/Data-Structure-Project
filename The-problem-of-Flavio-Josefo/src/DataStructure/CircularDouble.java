package DataStructure;

import java.util.ListIterator;
import java.util.NoSuchElementException;

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

        if (e == null) {
            addNewF.setNext(addNewF);
            addNewF.setPrevious(addNewF);
            header = addNewF;
            last = header;

        } else {
            addNewF.setPrevious(addNewF);
            last.setNext(addNewF);
            header.setPrevious(addNewF);
            addNewF.setNext(header);
            header = addNewF;
        }
        realSize++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        NodeListDouble<E> nodo_aux = new NodeListDouble(e);

        if (e == null) {
            return false;
        } else if (isEmpty()) {
            nodo_aux.setNext(nodo_aux);
            nodo_aux.setPrevious(nodo_aux);
            header = nodo_aux;
            last = header;

        } else {
            nodo_aux.setPrevious(last);
            last.setNext(nodo_aux);
            header.setPrevious(nodo_aux);
            nodo_aux.setNext(header);
            last = nodo_aux;
        }
        realSize++;
        return true;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            E eliminateF = getFirst();
            last.setNext(header.getNext());
            header.getNext().setPrevious(last);
            header.setNext(null);
            header.setPrevious(null);
            setHeaderNode(header.getNext());
            realSize--;
            return eliminateF;
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            E eliminateLast = getLastNode().getContent();
            header.setPrevious(last.getPrevious());
            last.getPrevious().setNext(header);
            last.setNext(null);
            last.setPrevious(null);
            setLastNode(last.getPrevious());
            realSize--;
            return eliminateLast;
        }
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

    public NodeListDouble<E> getNode(int index) {
        NodeListDouble<E> nodoEncontrado = null;
        if (!isEmpty()) {
            if (index == 0) {
                nodoEncontrado = header;
            } else {
                if (index > 0 && index < realSize) {
                    NodeListDouble<E> aux = header;
                    for (int i = 0; i < realSize - 1; i++) {
                        if (i == index - 1) {
                            NodeListDouble<E> mod = aux.getNext();
                            nodoEncontrado = mod;
                        } else {
                            aux = aux.getNext();
                        }
                    }
                }
            }
        }
        return nodoEncontrado;

    }

    @Override
    public int size() {
        return realSize;
    }

    @Override
    public void clear() {
        setHeaderNode(null);
        setLastNode(null);
        realSize = 0;
    }

    @Override
    public E get(int index) {
        E nodoEncontrado = null;
        if (!isEmpty()) {
            if (index == 0) {
                nodoEncontrado = header.getContent();
            } else {
                if (index > 0 && index < realSize) {
                    NodeListDouble<E> aux = header;
                    for (int i = 0; i < realSize - 1; i++) {
                        if (i == index - 1) {
                            NodeListDouble<E> mod = aux.getNext();
                            nodoEncontrado = mod.getContent();
                        } else {
                            aux = aux.getNext();
                        }
                    }
                }
            }
        }
        return nodoEncontrado;
    }

    public boolean isEmpty() {
        return header == null;
    }

    public NodeListDouble getHeaderNode() {
        return header;
    }

    public void setHeaderNode(NodeListDouble<E> header) {
        this.header = header;

    }

    public NodeListDouble<E> getLastNode() {
        return last;
    }

    public void setLastNode(NodeListDouble<E> last) {
        this.last = last;
    }

    @Override
    public E getLast() {
        return last.getContent();
    }

    @Override
    public E getFirst() {
        return header.getContent();

    }

    @Override
    public boolean remove(E element) {
        NodeListDouble<E> actual = header;
        NodeListDouble<E> anterior = last;
        boolean eliminado = false;
        do {
            if (actual.getContent() == element) {
                if (actual == header) {
                    header = header.getNext();
                    last.setNext(header);
                    header.setPrevious(last);
                } else if (actual == last) {
                    last = anterior;
                    header.setPrevious(last);
                    last.setNext(header);
                } else {
                    anterior.setNext(actual.getNext());
                    actual.getNext().setPrevious(anterior);
                }
                eliminado = true;
            }
            anterior = actual;
            actual = actual.getNext();
        } while (actual != header && eliminado == false);
        return eliminado;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>();
    }

    public class MyIterator<E> implements Iterator<E> {

        NodeListDouble<E> n = getHeaderNode();

        @Override
        public boolean hasNext() {
            return n != null;
        }

        @Override
        public E next() {
            E elemento = null;
            elemento = n.getContent();
            n = n.getNext();
            return elemento;
        }

    }

    public ListIterator<E> IterarNode(int i) {
        ListIterator<E> iter = new ListIterator<E>() {
            NodeListDouble<E> nodoViajero = getNode(i);
            int puntero = realSize;

            @Override
            public boolean hasNext() {
                boolean verificar = false;
                if (puntero != 0 && nodoViajero != null) {
                    verificar = true;
                }
                return verificar;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    E elemnt = nodoViajero.getContent();
                    nodoViajero = nodoViajero.getNext();
                    return elemnt;
                }
            }

            @Override
            public boolean hasPrevious() {
                boolean verificar = false;

                if (puntero != 0 && nodoViajero != null) {
                    verificar = true;
                }  
                    
                return verificar;

            }

            @Override
            public E previous() {
                nodoViajero = nodoViajero.getPrevious();
                E elemento = nodoViajero.getContent();
                return elemento;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        return iter;

    }

    public ListIterator<E> listIterator() {
        ListIterator<E> iter = new ListIterator() {
            NodeListDouble<E> nodoViajero = header;
            int puntero = realSize;

            @Override
            public boolean hasNext() {
                boolean verify = false;

                if (puntero != 0 && nodoViajero != null) {
                    verify = true;
                } else {
                    puntero = realSize;
                }

                return verify;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    E elemento = nodoViajero.getContent();
                    nodoViajero = nodoViajero.getNext();
                    puntero--;
                    return elemento;
                }
            }

            @Override
            public boolean hasPrevious() {
                boolean verify = false;

                if (puntero != 0 && nodoViajero != null) {
                    verify = true;
                } else {
                    puntero = realSize;
                }

                return verify;
            }

            @Override
            public E previous() {
                nodoViajero = nodoViajero.getPrevious();
                E elemento = nodoViajero.getContent();
                puntero--;
                return elemento;
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void set(Object e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void add(Object e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        return iter;
    }
}
