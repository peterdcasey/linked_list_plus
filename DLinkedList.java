import java.util.Iterator;

/**
 * DLinkedList
 *
 * @author Peter
 * @version 0.01
 */

public class DLinkedList<T> implements IList<T>, Iterable<T>
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
        list.clear();
        list.addFirst(new Person("Frankie"));
        list.addLast(new Person("Terri"));
        
        System.out.println("<<< The List >>>");
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
    
    public T getFirst() {
        return (start != null) ? start.getData() : null;
    }
    
    public T getLast() {
        return (start != null) ? start.getPrevious().getData() : null;
    }
    
    public T get(int index) {
        return null;
    }
    
    public boolean contains(T data) {
        return false;
    }
    
    public void clear() {
        System.out.println("===== clear =====");
        if (start == null) return;
        
        Node<T> e = start.getNext();
        while (e != start) {
            Node<T> next = e.getNext();
            nullNode(e);
            e = next;
        }
        nullNode(start);
        start = null;
        size = 0;
    }
    
    private void nullNode(Node<T> node) {
        node.setNext(null);
        node.setPrevious(null);
        //System.out.println(node.data);
        node.data = null;
    }
    
    public int size() {
        return size;
    }
    
    public boolean add(int index, T data) {
        return false;
    }
    
    public boolean addFirst(T data) {
        Node<T> node = new Node(data);
        
        if (start != null) {
            node.setNext(start);
            node.setPrevious(start.getPrevious());
            start.setPrevious(node);
        }
        start = node;
        size++;
        return true;
    }
    
    public boolean addLast(T data) {
        Node<T> node = new Node(data);
        
        if (start == null) {
            start = node;
        }
        else {
            node.setNext(start);
            node.setPrevious(start.getPrevious());
            start.getPrevious().setNext(node);
            start.setPrevious(node);
        }
        size++;
        return true;
    }

    public Iterator<T> iterator() {
        return new DLLIterator<T>(this);
    }
    
    private Node<T> firstNode() {
        return start;
    }
    
    private Node<T> lastNode() {
        return (start != null) ? start.getPrevious() : null;
    }
    
    //================================================
    private class DLLIterator<T> implements Iterator<T> {
        private Node<T> current;
        private int count = 0;
        
        public DLLIterator(DLinkedList<T> list) {
            current = list.firstNode();
        }
        
        public boolean hasNext() {
            return count < size;
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
    
    private static class Node<T> {
        private Node<T> next;
        private Node<T> previous;
        private T data;
        
        public Node (T data) {
            this.data = data;
            next = this;
            previous = this;
        }
        
        public Node<T> getNext() { return next; }
        public Node<T> getPrevious() { return previous; }
        public T getData() { return data; }
        public void setNext(Node<T> next) { this.next = next; }
        public void setPrevious(Node<T> previous) { this.previous = previous; }
    }
}
