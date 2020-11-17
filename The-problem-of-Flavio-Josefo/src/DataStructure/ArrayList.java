package DataStructure;

/**
 *
 * @author oscarlucas7
 */
public class ArrayList<E> implements List<E> {
    private E[] array;
    private int capacity = 10;
    private int effectiveSize;
    
    public ArrayList(){
        this.array = (E[]) new Object[capacity];
        effectiveSize = 0;
    }
    
    public boolean isFull(){
        return effectiveSize == array.length;
    }
    
    public void increaseCapacity(){
        E[] arrayHelper = (E[]) new Object[effectiveSize * 2];
        for (int i = 0; i < array.length; i++) {
            arrayHelper[i] = array[i];
        }
        array = arrayHelper;
    }
    
    @Override
    public E addFirst(E element) {
        if(element == null)
            return null;
        if(isFull())
            increaseCapacity();
        if(isEmpty()){
            array[0] = element;
            effectiveSize++;
            return array[0];
        }
        else{
            for (int i = effectiveSize-1; i > 0; i--) {
                array[i+1] = array[i];
            }
            array[0] = element;
            effectiveSize++;
            return array[0];
        }
    }

    @Override
    public E addLast(E element) {
        if(element == null)
            return null;
        if(isFull())
            increaseCapacity();
        if(isEmpty()){
            array[0] = element;
            effectiveSize++;
            return array[0];
        }
        else{
            array[effectiveSize] = element;
            effectiveSize++;
            return array[effectiveSize];
        }
    }

    @Override
    public E add(int index, E element) {
        if(index == 0){
            return addFirst(element);
        }
        if(element == null || !(index>0 && index < effectiveSize))
            return null;
        if(isFull())
            increaseCapacity();
        if(isEmpty()){
            array[0] = element;
            effectiveSize++;
            return array[0];
        }
        else{
            for (int i = effectiveSize-1; i != index; i--) {
                array[i+1] = array[i];
            }
            array[index] = element;
            effectiveSize++;
            return array[index];
        }
    }

    @Override
    public E removeFirst() {
        E elementRemoved = array[0];
        if(effectiveSize == 1){
            array[0] = null;
            effectiveSize = 0;
            return elementRemoved;
        }
        if(!isEmpty()){
            for (int i = 0; i < effectiveSize; i++) {
                array[i] = array[i+1];
            }
            effectiveSize--;
            return elementRemoved;
        }else
            return null;
    }

    @Override
    public E removeLast() {
        E elementRemoved = array[effectiveSize-1];
        if(effectiveSize == 1){
            array[0] = null;
            effectiveSize = 0;
            return elementRemoved;
        }
        if(!isEmpty()){
            array[effectiveSize-1] = null;
            effectiveSize--;
            return elementRemoved;
        }else
            return null;
            
            
        
    }

    @Override
    public E remove(int index) {
        if(index == 0)
            return removeFirst();
        if(!(index>0 && index < effectiveSize))
            return null;
        if(!isEmpty()){
            E elementRemoved = array[index];
            for (int i = effectiveSize-1; i >= index; i--) {
                array[i+1] = array[i];
            }
            effectiveSize--;
            return elementRemoved;
        }else
            return null;
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
         this.array = (E[]) new Object[capacity];
        effectiveSize = 0;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize==0;
    }

    @Override
    public int size() {
        return effectiveSize;
    }
 }
