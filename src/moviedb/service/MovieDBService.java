package moviedb.service;

import java.util.List;
import moviedb.models.*;
import moviedb.sqlService.DbAdapter;

public class MovieDBService {
    private DbAdapter adapter;
    
    private List<Movie> movies;
    private List<Person> persons;
    private List<Studio> studios;

}
