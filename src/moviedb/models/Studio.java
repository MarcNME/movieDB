package moviedb.models;

public class Studio {

    private final int id;
    private final String name;

    public Studio(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Studio(String name) {
        this.id = -1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
