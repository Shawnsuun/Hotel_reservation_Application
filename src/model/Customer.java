package model;
import java.util.regex.Pattern;

public class Customer {
    private String firstname;
    String lastname;
    String email;
    String emailRegex = "^(.+)@(.+).(.+)$";
    Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        if (!pattern.matcher(email).matches()) {
            System.out.println("Invalid email format.");
            throw new IllegalArgumentException("Invalid email format.");
        }
    }
    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "FirstName:" + firstname + ";"
                + "LastName:" + lastname + ";"
                + "Email:" + email;
    }
}
