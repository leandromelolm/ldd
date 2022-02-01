package dom;

public class Person {
    
    private int died;
    private String name;
    
    public Person() {}

    public Person(String name, int died) {
        this.died = died;
        this.name = name;
    }
    
    public int getDied() {
        return died;
    }

    public void setDied(int died) {
        this.died = died;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
