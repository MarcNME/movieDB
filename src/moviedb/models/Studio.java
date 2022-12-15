package moviedb.models;

import java.util.ArrayList;
import java.util.List;

public class Studio {

    private final int id;
    private final String name;
    List studios = new ArrayList<>();
    public Studio(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
    public int getId() {return id;}

}
