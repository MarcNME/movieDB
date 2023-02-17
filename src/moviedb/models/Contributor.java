package moviedb.models;

public class Contributor {

    private final int personID;
    private final String role;

    public Contributor(int personID, String role) {
        this.personID = personID;
        this.role = role;
    }

    public int getPersonID() {
        return personID;
    }

    public String getRole() {
        return role;
    }
}
