package moviedb.sqlService;

import moviedb.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DbAdapter {
    private final DbConnector connector;

    public DbAdapter() {
        this.connector = new MySqlConnector("jdbc:mysql://localhost:3306/moviedb", "root", "");
    }

    public void testConnection() {
        connector.testConnection();
    }

    public List<Movie> getMovies() {
        String sql = "SELECT * FROM movies;";
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
        String sql = "SELECT * FROM movies WHERE id = " + id + ";";
        Movie movie = null;
        try {
            ResultSet result = connector.executeQuery(sql);
            if (result.next()) {
                movie = new Movie();
                movie.setId(id);
                movie.setTitle(result.getString("title"));
                movie.setDescription(result.getString("description"));
                movie.setStudioID(result.getInt("studio_id"));
                movie.setImagePath(result.getString("imagePath"));
                
                movie.setContributors(getContributorsByMovieID(id));
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return movie;
    }

    public void addMovie(Movie movie) {
        String dml = String.format("INSERT INTO `movies` " +
                        "(`title`, `description`, `studio_id`) " +
                        "VALUES ('%s', '%s', '%d');",
                movie.getTitle(), movie.getDescription(), movie.getStudioID());

        try {
            connector.excecuteNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteMovie(int id) {
        String dml = "DELETE FROM movies WHERE `movies`.`id` = " + id;
        try {
            connector.excecuteNonQuery(dml);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Person> getPeople() {
        String sql = "SELECT * FROM persons";
        List<Person> people = new ArrayList<>();
        try {
            ResultSet result = connector.executeQuery(sql);
            while (result.next()) {
                Person tmp = new Person(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getDate("birthday"));

                people.add(tmp);
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return people;
    }

    public void addPerson(Person person) {
        String dml = String.format("INSERT INTO `persons` " +
                "(name, birthday) " +
                "VALUES (%s, %s)", person.getName(), person.getBirthdate());

        try {
            connector.excecuteNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Review> getReviewsForMovieID(int movieId) {
        String sql = "SELECT * FROM reviews WHERE movie_id = " + movieId + ";";
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

    public void addReview(Review review, int movieID) {
        String dml = String.format("INSERT INTO `reviews` " +
                        "(grade, userName, movie_id) " +
                        "VALUES (%d, '%s', %d);",
                review.getGrade(), review.getUserName(), movieID);

        try {
            System.out.println(dml);
            connector.excecuteNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Studio> getStudios() {
        String sql = "SELECT * FROM studios;";
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
        String sql = "SELECT * FROM studios WHERE id = " + id + ";";
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

    public void addStudio(Studio studio) {
        String dml = String.format("INSERT INTO `studios`" +
                "(name) VALUES (%s)", studio.getName());

        try {
            connector.excecuteNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Contrbutor> getContributorsByMovieID(int movieID) {
        String sql = "SELECT * FROM `contributors` WHERE movie_id = " + movieID + ";";
        List<Contrbutor> contributions = new ArrayList<>();

        try {
            ResultSet result = connector.executeQuery(sql);

            while (result.next()) {
                contributions.add(new Contrbutor(
                        result.getInt("person_id"),
                        result.getString("role")));
            }
            result.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        connector.close();
        return contributions;
    }

    public void addContribution(int movieID, int personID, String role) {
        String dml = String.format("INSERT INTO `contributors` " +
                "(person_id, movie_id, role)" +
                " VALUES (%d, %d, %s)", movieID, personID, role);

        try {
            connector.excecuteNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
