import java.util.*;

/**
 * Write a description of class May21 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class May21 extends Object
{
    // instance variables - replace the example below with your own
    private int x;
    private static int y = 0;
    private static ArrayList<Integer> list3;
    private int id;

    public static void main(String[] args) {
        test2();
    }
    
    private static void printList(Object list) {
        //list.stream().forEach(System.out::println);
        System.out.println(list);
    }
    
    public String toString() {
        return "ID: " + id;
    }
    
    /**
     * Constructor for objects of class May21
     */
    public May21(int id) {
        this.id = id;
    }
    
    public May21(May21 other) {
        this.id = other.id;
    }
    
    // mutator
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        else if (! (other instanceof May21)) {
            return false;
        }
        else {  // at point we KNOW it is a May21 object
            return this.id == ((May21)other).id;
        }
    }
    
    private static void test2() {
        var list1 = new ArrayList<May21>();
        list1.add(new May21(4));
        list1.add(new May21(6));
        list1.add(new May21(7));
        var list2 = list1;
        //var list3 = new ArrayList<May21>(list1);
        //list3.get(0).setId(99);
        var list3 = copy(list1);
        list1.get(0).setId(99);
        
        printList(list1);
        printList(list2);
        printList(list3);
    }
    
    public static ArrayList<May21> copy(ArrayList<May21> list) {
        var newList = new ArrayList<May21>();
        
        for (int i = 0; i < list.size(); i++) {
            newList.add(new May21(list.get(i)));
        }
        
        return newList;
        
    }
    
    private static void printXAlso() {
        //System.out.println(x);
    }
    
    public void printX() {
        System.out.println(x);
    }
    
    private static void printY() {
        System.out.println(y);
    }
    
    private static void test1() {
        
        var list1 = new ArrayList<Integer>();
        Integer x = 55;
        int y = 55;
        list1.add(x); list1.add(6);
        ArrayList<Integer> list2 = list1;
        list3 = new ArrayList<>(list1);
        
        list1.add(99);
        list1.set(0, new Integer(66));
        
        printList(list1);
        printList(list2);
        printList(list3);
        
        /*printY();
        May21.printY();
        
        May21 obj = new May21();
        obj.printX();
        obj.y = 99;
        */        
    }
}
