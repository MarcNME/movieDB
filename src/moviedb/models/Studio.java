package moviedb.models;

public class Studio {

    private final int id;
    private final String name;

    public Studio(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
