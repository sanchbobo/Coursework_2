/**
 * A class representing a person with a name, surname, and email address.
 */
public class Person {

    // Instance variables
    private String name;
    private String surname;
    private String email;

    /**
     * Constructor for the Person class.
     * @param name The person's first name.
     * @param surname The person's last name.
     * @param email The person's email address.
     */
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    /**
     * Getter method for the person's first name.
     * @return The person's first name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for the person's last name.
     * @return The person's last name.
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Getter method for the person's email address.
     * @return The person's email address.
     */
    public String getEmail() {
        return this.email;
    }
}
