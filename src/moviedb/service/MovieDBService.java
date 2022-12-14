package moviedb.service;

import moviedb.models.Movie;
import moviedb.models.Person;
import moviedb.models.Studio;
import moviedb.sqlService.DbAdapter;

import java.util.List;

public class MovieDBService {
    private DbAdapter adapter;

    private List<Movie> movies;
    private List<Person> persons;
    private List<Studio> studios;

    public MovieDBService() {
        adapter = new DbAdapter();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMoviesByID(int id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findAny().orElse(null);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person getPersonByID(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }

    public List<Studio> getStudios() {
        return studios;
    }

    public Studio getStudioByID(int id) {
        return studios.stream()
                .filter(studio -> studio.getId() == id)
                .findAny().orElse(null);
    }

    public void refresh() {
        movies = adapter.getMovies();
        persons = adapter.getPeople();
        studios = adapter.getStudios();

        movies.forEach(movie -> {
            movie.setReviews(adapter.getReviewsForMovieID(movie.getId()));
            movie.setContributors(adapter.getContributorsByMovieID(movie.getId()));
        });
    }
}
