package moviedb.sqlService;

import moviedb.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    public int getMovieID(Movie m) {
        String sql = String.format("SELECT id FROM movies "
                + "WHERE title = '%s' AND description = '%s' AND studio_id = %d;",
                m.getTitle(), m.getDescription(), m.getStudioID());
        System.out.println(sql);
        int id = -1;
        try {
            ResultSet result = connector.executeQuery(sql);
            if (result.next()) {
                id = result.getInt("id");
            }
            result.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        connector.close();
        return id;
    }

    public void addMovie(Movie movie) {
        String dml = String.format("INSERT INTO `movies` "
                + "(`title`, `description`, `studio_id`) "
                + "VALUES ('%s', '%s', %d);",
                movie.getTitle(), movie.getDescription(), movie.getStudioID());

        try {
            connector.executeNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteMovie(int id) {
        String dml = "DELETE FROM movies WHERE `movies`.`id` = " + id;
        try {
            connector.executeNonQuery(dml);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editMovie(int id, String title, String description) {
        String dml = "UPDATE `movies` SET `title` = '" + title + "', `description` = '" + description + "' WHERE `movies`.`id` = 1 ";
        try {
            connector.executeNonQuery(dml);
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dml = String.format("INSERT INTO `persons` "
                + "(name, birthday) "
                + "VALUES ('%s', '%s')", person.getName(), dateFormat.format(person.getBirthdate()));

        try {
            connector.executeNonQuery(dml);
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
        String dml = String.format("INSERT INTO `reviews` "
                + "(grade, userName, movie_id) "
                + "VALUES (%d, '%s', %d);",
                review.getGrade(), review.getUserName(), movieID);

        try {
            connector.executeNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteReviewsForMovie(int movieId) {
        String dml = "DELETE FROM reviews WHERE `reviews`.`movie_id` = " + movieId;
        try {
            connector.executeNonQuery(dml);
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
        String dml = String.format("INSERT INTO `studios`"
                + "(name) VALUES ('%s')", studio.getName());

        try {
            connector.executeNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Contributor> getContributorsByMovieID(int movieID) {
        String sql = "SELECT * FROM `contributors` WHERE movie_id = " + movieID + ";";
        List<Contributor> contributions = new ArrayList<>();

        try {
            ResultSet result = connector.executeQuery(sql);

            while (result.next()) {
                contributions.add(new Contributor(
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
        String dml = String.format("INSERT INTO `contributors` "
                + "(person_id, movie_id, role)"
                + " VALUES (%d, %d, '%s')", personID, movieID, role);

        try {
            connector.executeNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteContributionsForMovie(int movieId) {
        String dml = "DELETE FROM contributors WHERE `contributors`.`movie_id` = " + movieId;
        
        try {
            connector.executeNonQuery(dml);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
