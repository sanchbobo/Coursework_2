/**
 * A class representing a ticket with a row, seat, price, and the person who bought it.
 */
public class Ticket {

    // Instance variables
    private int row;
    private int seat;
    private int price;
    Person newPerson;

    /**
     * Constructor for the Ticket class.
     * @param row The row of the ticket.
     * @param seat The seat of the ticket.
     * @param price The price of the ticket.
     * @param newPerson The person who bought the ticket.
     */
    public Ticket(int row, int seat, int price, Person newPerson) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.newPerson = newPerson;
    }

    /**
     * Method to print the person's name, surname, email, row, seat, and price of the ticket.
     */
    public void print() {
        System.out.println(newPerson.getName() + " " + newPerson.getSurname() + " " + newPerson.getEmail());
        System.out.println(row + " " + seat + " " + price);
    }

    /**
     * Getter method for the row of the ticket.
     * @return The row of the ticket.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Getter method for the seat of the ticket.
     * @return The seat of the ticket.
     */
    public int getSeat() {
        return this.seat;
    }

    /**
     * Getter method for the price of the ticket.
     * @return The price of the ticket.
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Getter method for the person who bought the ticket.
     * @return The person who bought the ticket.
     */
    public Person getPerson() {
        return this.newPerson;
    }
}
