package moviedb.service;

import java.util.List;
import moviedb.models.*;
import moviedb.sqlService.DbAdapter;

public class MovieDBService {
    private DbAdapter adapter;


    private List<Movie> movies;
    private List<Person> persons;
    private List<Studio> studios;

    public Movie getMoviesByID(int id)
    {
     return movies.stream()
             .filter(movie -> movie.getId() == id)
             .findAny().orElse(null);
    }

    public  Person getPersonByID(int id)
    {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }

    public Studio getStudioByID(int id)
    {
        return studios.stream()
                .filter(studio -> studio.getId() == id)
                .findAny().orElse(null);
    }
}
