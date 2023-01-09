package moviedb.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movie {

    private final Map<Integer, String> contributors; //Contributors to the movie <personId, role>
    private final List<Review> reviews;
    private int id;
    private String title;
    private String description;
    private String imagePath;
    private int studioID;

    public Movie() {
        this.id = -1;
        this.title = "notChanged";
        this.studioID = -1;
        this.contributors = new HashMap<>();
        this.reviews = new ArrayList<>();
        this.description = "notChanged";
        this.imagePath = "notChanged";
    }

    public Movie(int id, String title, int studioID, Map<Integer, String> contributors) {
        this.id = id;
        this.title = title;
        this.studioID = studioID;
        this.contributors = contributors;
        this.reviews = new ArrayList<>();
        this.description = "";
        this.imagePath = "";
    }

    public Movie(int id, String title, int studioID) {
        this.id = id;
        this.title = title;
        this.studioID = studioID;
        this.contributors = new HashMap<>();
        this.reviews = new ArrayList<>();
        this.description = "";
        this.imagePath = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getStudioID() {
        return studioID;
    }

    public void setStudioID(int studioID) {
        this.studioID = studioID;
    }

    public Map<Integer, String> getContibutors() {
        return contributors;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public Review getReviewByID(int id) {
        return reviews.stream()
                .filter(review -> review.getId() == id)
                .findAny().orElse(null);
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void addContributor(int personID, String role) {
        this.contributors.put(id, role);
    }

    public String getContributorRole(int personID) {
        return this.contributors.get(id);
    }
}
