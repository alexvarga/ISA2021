package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends BaseEntity {


    private String email;
    private String password; // for now
    private String firstName;
    private String lastName;
    //....


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
