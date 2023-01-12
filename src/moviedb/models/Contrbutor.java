package moviedb.models;

public class Contrbutor {

    int personID;
    String role;

    public Contrbutor(int personID, String role) {
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
