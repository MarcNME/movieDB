package moviedb.service;

import moviedb.models.Movie;
import moviedb.models.Person;
import moviedb.models.Review;
import moviedb.models.Studio;
import moviedb.sqlService.DbAdapter;

import java.util.List;

public class MovieDBService {
    private final DbAdapter adapter;

    private List<Movie> movies;
    private List<Person> persons;
    private List<Studio> studios;

    public MovieDBService() {
        adapter = new DbAdapter();
        adapter.testConnection();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMoviesByID(int id) {
        return movies.stream()
                .filter(movie -> movie.getId() == id)
                .findAny().orElse(null);
    }

    public void addMovie(Movie movie) {
        adapter.addMovie(movie);
    }

    public void addReview(Review review, int movieID) {
        adapter.addReview(review, movieID);
    }

    public void editMovie(int movieId, String title, String description) {
        adapter.editMovie(movieId, title, description);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person getPersonByID(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }

    public void addPerson(Person person) {
        adapter.addPerson(person);
    }

    public List<Studio> getStudios() {
        return studios;
    }

    public Studio getStudioByID(int id) {
        return studios.stream()
                .filter(studio -> studio.getId() == id)
                .findAny().orElse(null);
    }

    public void deleteMovie(int id) {
        adapter.deleteMovie(id);
    }

    public void addStudio(Studio studio) {
        adapter.addStudio(studio);
    }

    public void refresh() {
        movies = adapter.getMovies();
        persons = adapter.getPeople();
        studios = adapter.getStudios();

        movies.forEach((Movie movie) -> {
            movie.setReviews(adapter.getReviewsForMovieID(movie.getId()));
            movie.setContributors(adapter.getContributorsByMovieID(movie.getId()));
        });
    }

    public String addLineBreaks(String s, int maxLineLength) {
        String[] splits = s.split(" ");
        StringBuilder builder = new StringBuilder();
        String tmp = "";
        for (String b : splits) {
            if (tmp.length() + b.length() <= maxLineLength) {
                tmp += b + ' ';
            } else {
                builder.append(tmp);
                builder.append('\n');
                tmp = b;
            }
        }
        builder.append(tmp);

        return builder.toString();
    }
}
