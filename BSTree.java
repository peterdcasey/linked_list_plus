import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

/**
 * Binary Search Tree
 * 
 */
public class BSTree<T extends Comparable<T>>  {
    
    // Test Driver
    public static void main(String[] args) {
        System.out.println();
        integerExample();
        personExample();
        try {
            fileExample("nums3.txt");
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // BST with Person data
    public static void personExample() {
        BSTree<Person> t = new BSTree<>();
        t.insert(new Person("Betty"))
        .insert(new Person("Zach"))
        .insert(new Person("Tina"))
        .insert(new Person("Lindsay"))
        .insert(new Person("Andy"));
        System.out.println(t.asList(Order.INORDER));
        System.out.println(t.contains(new Person("Tina")));
        System.out.println(t.contains(new Person("Larry")));
        t.insert(new Person("Mary"))
        .insert(new Person("Sam"))
        .insert(new Person("Tina"));    
        System.out.println(t.asList(Order.INORDER));
    }
    
    // BST with Integer data
    public static void integerExample() {
        BSTree<Integer> t = new BSTree<>();
        int[] nums = {4, 0, 9, 8, 1, -2, 7, 39};
        /*                  4
         *          0               9
         *     -2       1        8     39
         *                     7
         */
        Arrays.stream(nums).forEach(e -> t.insert(e));
        
        System.out.println(t.contains(4));
        System.out.println(t.contains(99));
            
        System.out.println(t.asList(Order.PREORDER));
        System.out.println(t.asList(Order.INORDER));
        System.out.println(t.asList(Order.POSTORDER));
    }
    
    /**
     * Read numbers from a file into a BSTree and
     *     print the three traversals
     * @param fileName The name of the file of numbers
     */
    public static void fileExample(String fileName)
            /*throws IOException*/ {
        Scanner s = null;
        BSTree<Integer> t = new BSTree<>();

        try {
            s = new Scanner(
                    new BufferedReader(
                        new FileReader(fileName)));
            s.useDelimiter(",\\s*");  // comma delimited numbers

            while (s.hasNextInt()) {
                int i = s.nextInt();
                System.out.println(i);
                t.insert(i);
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
            return;
        } finally {
            if (s != null) {
                s.close();
            }
        }
        
        System.out.println(t.asList(Order.PREORDER));
        System.out.println(t.asList(Order.INORDER));
        System.out.println(t.asList(Order.POSTORDER));        
    }

    private BSTNode<T> root;    // root of the BST
    private List<T> list;       // List used to 'easy' print
    
    public static enum Order { PREORDER, INORDER, POSTORDER };

    // root <-- null, list <-- new ArrayList()
    public BSTree() {
        root = null;
        list = new ArrayList<T>();
    }

    /**
     * insert()
     * @param data A data element
     * @return this
     * @throws NullPointerException when data is null
     */
    public BSTree<T> insert(T data) {
        java.util.Objects.requireNonNull(data, "insert: data must be non-null");
        root = insert(data, root);
        return this;
    }

    /**
     * insert recursively
     * @param data (already tested for null)
     * @param root The root of the BST
     * @return new BSTNode to hang off correct parent node
     */
    private BSTNode<T> insert(T data, BSTNode<T> root) {   
        if (root == null) {
            return new BSTNode(data);
        }

        int cmp = root.compareTo(data);

        if (cmp > 0) {
            root.setLeftChild(insert(data, root.getLeftChild()));
        }
        else if (cmp < 0) {
            root.setRightChild(insert(data, root.getRightChild()));
        }

        return root;
    }
    
    /**
     * contains --> does data exist in the BST
     * @return true if found, false otherwise
     * @throws NullPointerException
     */
    public boolean contains(T data) {
        java.util.Objects.requireNonNull(data, "insert: data must be non-null");
        return containsNode(root, data);
    }

    /**
     * Recursive contains
     * @param current Current node in tree, starts at root
     * @param data Item to look for
     * @return true if found, false otherwise
     */
    private boolean containsNode(BSTNode<T> current, T data) {
        var found = false;

        if (current != null) {
            if (data.equals(current.getData())) {
                found = true;
            } 
            else {
                int cmp = data.compareTo(current.getData());
                found =  cmp < 0
                        ? containsNode(current.getLeftChild(), data)
                        : containsNode(current.getRightChild(), data);
            }
        }
        return found;
    }

    /**
     * asList get the items from the BSTree into a list
     *    for easy printing, clear it first
     * @return a list with the BSTree elements in order
     */
    public List<T> asList(Order order) {
        list.clear();
        switch (order) {
            case PREORDER:
                getTree2(root); break;
            case INORDER:
                getTree(root);  break;
            case POSTORDER:
                getTree3(root); break;
            default:
                System.out.println("Error in asList()");
        }
        return list;
    }

    /**
     * Do a recursive inorder traversal of the BSTree, adding
     *   each item to the temporary list.
     * @param The root of each tree to traverse
     */
    public void getTree(BSTNode<T> t) {
        if (t != null) {
            getTree(t.getLeftChild());
            list.add(t.getData());
            getTree(t.getRightChild());
        }
    }

    /**
     * Do a recursive inorder traversal of the BSTree, adding
     *   each item to the temporary list.
     * @param The root of each tree to traverse
     */
    public void getTree2(BSTNode<T> t) {
        if (t != null) {
            list.add(t.getData());
            getTree2(t.getLeftChild());
            getTree2(t.getRightChild());
        }
    }

    /**
     * Do a recursive inorder traversal of the BSTree, adding
     *   each item to the temporary list.
     * @param The root of each tree to traverse
     */
    public void getTree3(BSTNode<T> t) {
        if (t != null) {
            getTree3(t.getLeftChild());
            getTree3(t.getRightChild());
            list.add(t.getData());
        }
    }

    // ===========================================================

    /**
     * Binary Search Tree node
     * left, right child link and a data item
     */
    private static class BSTNode<T extends Comparable<T>> {
        private BSTNode<T> leftChild;
        private T data;
        private BSTNode<T> rightChild;

        public BSTNode(T data) {
            leftChild = null;
            this.data = data;
            rightChild = null;
        }

        public int compareTo(T other) {
            return this.getData().compareTo(other);
        }

        public T getData() {
            return this.data;
        }

        public BSTNode<T> getLeftChild() {
            return leftChild;
        }

        public BSTNode<T> getRightChild() {
            return rightChild;
        }

        public void setLeftChild(BSTNode<T> node) {
            leftChild = node;
        }

        public void setRightChild(BSTNode<T> node) {
            rightChild = node;
        }
    }
}
