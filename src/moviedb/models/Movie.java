package moviedb.models;

import java.util.*;
import java.util.stream.Collectors;

public class Movie {

    private List<Contrbutor> contributors; //Contributors to the movie <personId, role>
    private List<Review> reviews;
    private int id;
    private String title;
    private String description;
    private String imagePath;
    private int studioID;

    public Movie() {
        this.id = -1;
        this.title = "notChanged";
        this.studioID = -1;
        this.contributors = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.description = "notChanged";
        this.imagePath = "notChanged";
    }

    public Movie(int id, String title, int studioID, List<Contrbutor> contributors) {
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
        this.contributors = new ArrayList<>();
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

    public List<Contrbutor> getContibutors() {
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

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void setContributors(List<Contrbutor> contributors) {
        this.contributors = contributors;
    }
    public void addContributor(int personID, String role) {
        this.contributors.add(new Contrbutor(personID, role));
    }

    public String getContributorRole(int personID) {
        return this.contributors.stream()
                .filter(contrbutor -> contrbutor.personID == personID)
                .map(contrbutor -> contrbutor.role)
                .findAny().orElse(null);
    }

    public List<Integer> getContributorsIDsByRole(String role) {
        List<Integer> contributorIds = new ArrayList<>();
        for (Contrbutor contrbutor : contributors) {
            if (contrbutor.getRole().equals(role)) {
                contributorIds.add(contrbutor.getPersonID());
            }
        }
        
        return contributorIds;
    }
}
