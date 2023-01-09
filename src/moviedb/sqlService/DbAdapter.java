package moviedb.sqlService;

import moviedb.models.Movie;
import moviedb.models.Person;
import moviedb.models.Review;
import moviedb.models.Studio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAdapter {
    private final DbConnector connector;

    public DbAdapter() {
        this.connector = new MySqlConnector("jdbc:mysql://localhost:3306/movieDB", "root", "");
    }

    public List<Movie> getMovies() {
        String sql = "SELECT * FROM moviedb.movie;";
        List<Movie> movies = new ArrayList<>();

        try {
            ResultSet result = connector.executeQuery(sql);
            while (result.next()) {
                Movie tmp = new Movie();
                tmp.setId(result.getInt("id"));
                tmp.setTitle(result.getString("title"));
                tmp.setDescription(result.getString("description"));
                tmp.setStudioID(result.getInt("studio_id"));
                tmp.setImagePath(result.getString("imagePath"));

                movies.add(tmp);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return movies;
    }

    public Movie getMovieByID(int id) {
        String sql = "SELECT * FROM moviedb.movie WHERE id = " + id + ";";
        Movie movie = null;
        try {
            ResultSet result = connector.executeQuery(sql);
            if (result.next()) {
                movie = new Movie();
                movie.setId(result.getInt("id"));
                movie.setTitle(result.getString("title"));
                movie.setDescription(result.getString("description"));
                movie.setStudioID(result.getInt("studio_id"));
                movie.setImagePath(result.getString("imagePath"));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return movie;
    }

    public void addMovie(Movie movie) {
        String dml = String.format("INSERT INTO `movie` " +
                        "(`title`, `description`, `studio_id`)" +
                        " VALUES ('%s', '%s', '%d');",
                movie.getTitle(), movie.getDescription(), movie.getStudioID());

        try {
            connector.excecuteNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Person> getPeople() {
        String sql = "SELECT * FROM moviedb.person";
        List<Person> people = new ArrayList<>();
        try {
            ResultSet result = connector.executeQuery(sql);
            while (result.next()) {
                Person tmp = new Person(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birthdate"));

                people.add(tmp);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return people;
    }

    public List<Review> getReviewsForMovie(int movieId) {
        String sql = "SELECT * FROM moviedb.review WHERE movie_id = " + movieId + ";";
        List<Review> reviews = new ArrayList<>();

        try {
            ResultSet result = connector.executeQuery(sql);

            while (result.next()) {
                Review tmp = new Review(
                        result.getInt("id"),
                        result.getInt("grade"),
                        result.getString("userName"));

                reviews.add(tmp);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return reviews;
    }

    public List<Studio> getStudios() {
        String sql = "SELECT * FROM moviedb.studios;";
        List<Studio> studios = new ArrayList<>();

        try {
            ResultSet result = connector.executeQuery(sql);
            while (result.next()) {
                Studio tmp = new Studio(
                        result.getInt("id"),
                        result.getString("name"));
                studios.add(tmp);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return studios;
    }

    public Studio getStudioByID(int id) {
        String sql = "SELECT * FROM moviedb.studio WHERE id = " + id + ";";
        Studio studio = null;
        try {
            ResultSet result = connector.executeQuery(sql);

            if (result.next()) {
                studio = new Studio(
                        result.getInt("id"),
                        result.getString("name"));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return studio;
    }
}
