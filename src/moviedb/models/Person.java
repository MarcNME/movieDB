package moviedb.models;

import java.util.Date;

public class Person {
    private final int id;
    private final String name;
    private final Date birthdate;

    public Person(int id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public Person(String name, Date birthdate) {
        this.id = -1;
        this.name = name;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return name;
    }
}
