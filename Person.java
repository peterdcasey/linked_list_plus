
/**
 * A person.
 *
 * @author Peter
 * @version 0.01
 */
public class Person implements Comparable<Person>
{
    private static int nextId = 0;
    private String name;
    private int id;

    public Person(String name)
    {
        this.name = name;
        id = nextId++;
    }

    public String getName() { return name; }

    public int getId() { return id; }

    public int compareTo(Person other) {
        return this.getName().compareTo(other.getName());
    }

    public boolean equals(Object other) {
        boolean result = false;

        if (this == other) {
             result = true;
        } 
        else if (other instanceof Person) {
            result = name.equals(((Person)other).name);
        }
        
        return result;
    }

    public String toString() {
        return getId() + ":" + getName();
    }
}
