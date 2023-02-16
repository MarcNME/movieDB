package moviedb.models;

public class Review {
    int id;
    final int grade;
    final String userName;

    public Review(int id, int grade, String userName) {
        this.id = id;
        this.grade = grade;
        this.userName = userName;
    }

    public Review(int grade, String userName) {
        this.grade = grade;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public String getUserName() {
        return userName;
    }
}
