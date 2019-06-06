import java.util.Iterator;

/**
 * DLinkedList: doubly linked circular list
 *
 * @author Peter
 * @version 0.03
 */

public class DLinkedList<T> implements Iterable<T>
{
    public static void main(String[] args) {
        System.out.println();
        DLinkedList<Person> list = new DLinkedList<>();
        list.addFirst(new Person("Paul"));
        list.addFirst(new Person("Paula"));
        list.addFirst(new Person("Pricilla"));
        list.addFirst(new Person("Prudence"));
        list.addLast(new Person("Teresa"));
        list.addLast(new Person("Thomas"));
        list.addFirst(new Person("Frankie"));
        list.addLast(new Person("Terri"));
        
        for (Person p : list) {
            System.out.println(p);
        }
    }
    // =======================================================
    
    private Node<T> start;
    private int size;
    
    public DLinkedList() {
        start = null;
        size = 0;
    }
    
    public Node<T> getFirst() {
        return start;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(T data) {
        
        if (start != null) {
            Node<T> node = new Node(data, start.getPrevious(), start);
            start.setPrevious(node);
            start = node;
        }
        else { 
            start = new Node(data);
        }
        size++;
    }
    
    public void addLast(T data) {
        
        if (start != null) {
            Node<T> node = new Node(data, start.getPrevious(), start);
            start.getPrevious().setNext(node);
            start.setPrevious(node);
        }
        else {
            start = new Node(data);
        }
        size++;
    }

    public Iterator<T> iterator() {
        return new DLLIterator<T>(this);
    }
    
    //================================================
    // Custom Iterator class
    //================================================
    private class DLLIterator<T> implements Iterator<T> {
        // current item iterator will return
        private DLinkedList<T>.Node<T> current;
        // linked list is circular so use a counter rather than null
        private int count = 0;
        
        public DLLIterator(DLinkedList<T> list) {
            current = list.getFirst();
        }
        
        public boolean hasNext() {
            return count < size();
        }
        
        public T next() {
            T data = current.getData();
            current = current.getNext();
            count++;
            return data;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    //=====================================================
    // Simple Node class for a doubly linked list
    //=====================================================
    private class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private T data;
        
        public Node (T data) {
            this.data = data;
            next = this;
            previous = this;
        }
        
        public Node(T data, Node previous, Node next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
        
        // Node operations
        public Node<T> getNext() { return next; }
        public Node<T> getPrevious() { return previous; }
        public T getData() { return data; }
        public void setNext(Node<T> next) { this.next = next; }
        public void setPrevious(Node<T> previous) { this.previous = previous; }
    };
    
}
