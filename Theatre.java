import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class that represents a theatre.
 * A theatre has a name, seating area with rows and seats,
 * and a list of tickets sold.
 */
public class Theatre {
    /**
     * Displays a menu of options and returns the user's selection.
     *
     * @return The number corresponding to the user's selected option
     */
    public static int displayMenu() {
        // Define the menu options
        String[] options = {"Quit", "Buy a ticket", "Print seating area", "Cancel ticket", 
                            "List available seats", "Save to file", "Load from file", 
                            "Print ticket information and total price", "Sort tickets by price"};
        
        // Print the menu
        System.out.println("-------------------------------------------------\n");
        System.out.println("Please select an option:");
        for (int i = 1; i < options.length; i++) {
            System.out.println(i + ") " + options[i]);
        }
        System.out.println("0) " + options[0] + "\n");
        System.out.println("-------------------------------------------------\n");
        
        // Prompt the user for input and return their selection
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
    
    /**
     * The main method of the Theater application. Displays a menu of options to the user and
     * performs actions based on their selection.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        
        // Initialize the seating area and ticket list
        int[][] seatingArea = new int[][] {
            new int[12],
            new int[16],
            new int[20]
        };
        List<Ticket> ticket_lst = new ArrayList<>();
        
        // Loop until the user chooses to quit
        while (true) {
            // Display the menu and get the user's selection
            int selection = displayMenu();
            
            // Handle the user's selection
            switch (selection) {
                case 1: // Buy a ticket
                    buyTicket(seatingArea, ticket_lst);
                    break;
                case 2: // Print seating area
                    printSeatingArea(seatingArea);
                    break;
                case 3: // Cancel ticket
                    cancelTicket(seatingArea, ticket_lst);
                    break;
                case 4: // List available seats
                    showAvailableSeats(seatingArea);
                    break;
                case 5: // Save to file
                    save(seatingArea);
                    break;
                case 6: // Load from file
                    load(seatingArea);
                    break;
                case 7: // Print ticket information and total price
                    showTicketsInfo(ticket_lst);
                    break;
                case 8: // Sort tickets by price
                    List<Ticket> sorted_tickets = new ArrayList<>(sortTickets(ticket_lst));
                    showTicketsInfo(sorted_tickets);
                    break;
                case 0: // Quit
                    System.exit(0);
                    break;
                default: // Invalid selection
                    System.out.println("Invalid selection. Please choose a number from the menu.");
            }
        }
    }

    /**
    
    Checks whether the given row and seat are valid in the seating area.
    @param row The row number of the seat to be checked.
    @param seat The seat number of the seat to be checked.
    @return true if the row and seat are valid, false otherwise.
    */
    public static boolean checkRowAndSeat(int row, int seat) {
        // Check if the row number is within the range of 0-2
        if (row < 0 || row > 2) {
            return false;
        }
        
        // Check if the seat number is within the range of the corresponding row
        if (row == 0 && seat > 11) {
            return false;
        }
        
        if (row == 1 && seat > 15) {
            return false;
        }
        
        if (row == 2 && seat > 19) {
            return false;
        }
        // If the row and seat are within the valid ranges, return true
        return true;
    }
    /**
     * Allows a user to purchase a ticket and adds the ticket to the provided ticket list.
     * Updates the seating area to indicate that the purchased seat is now occupied.
     * 
     * @param seatingArea a 2D integer array representing the current seating area
     * @param ticketList a List of Ticket objects representing all tickets purchased so far
     */
    public static void buyTicket(int[][] seatingArea, List<Ticket> ticketList) {
        Scanner input = new Scanner(System.in);
    
        // Get information for the new person
        System.out.println("Enter the person's information (name surname email): ");
        String name = input.next();
        String surname = input.next();
        String email = input.next();
        Person newPerson = new Person(name, surname, email);
    
        // Get information for the new ticket
        System.out.println("Input a row number and a seat number:");
        int row = 0;
        int seat = 0;
        while (true) {
            row = input.nextInt();
            seat = input.nextInt();
    
            // Check if the entered row and seat are valid
            if (checkRowAndSeat(row, seat)) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    
        // Get the ticket price and create a new ticket object
        System.out.print("Enter the ticket price: ");
        int price = input.nextInt();
        Ticket newTicket = new Ticket(row, seat, price, newPerson);
        ticketList.add(newTicket);
    
        // Mark the purchased seat as occupied in the seating area
        seatingArea[row][seat] = 1;
    }    
    
    /**
     * Prints the current seating area with an ASCII art of the stage and marks taken seats with 'X' and available seats with 'O'.
     * 
     * @param seatingArea a 2D integer array representing the current seating area
     */
    public static void printSeatingArea(int[][] seatingArea) {
        // Prints the ASCII art of the stage
        System.out.println("    ***********");
        System.out.print("\n");
        System.out.println("    *  STAGE  *");
        System.out.print("\n");
        System.out.println("    ***********");
        
        // Iterates through the 2D seating area array and prints out 'X' for taken seats and 'O' for available seats
        for (int i = 0; i < seatingArea.length; i++) {
            if (i == 0) System.out.print("    "); // For formatting purposes
            if (i == 1) System.out.print("  "); // For formatting purposes
            for (int j = 0; j < seatingArea[i].length; j++) {
                if (j == seatingArea[i].length / 2) {
                    System.out.print(" "); // For formatting purposes
                }
                if (seatingArea[i][j] == 1) {
                    System.out.print('X');
                } else {
                    System.out.print('O');
                }
            }
            System.out.print('\n'); // Moves to the next line
        }
    }

    /**
     * This method cancels a ticket by removing it from the list of tickets and setting the seat in the seating area to 0.
     * 
     * @param seatingArea A 2D integer array representing the seating area.
     * @param ticket_lst A list of Ticket objects representing the tickets that have been sold.
     */
    public static void cancelTicket(int[][] seatingArea, List<Ticket> ticket_lst) {
        Scanner input = new Scanner(System.in);
        System.out.print("Input a row number and a seat number separated by a space: ");
        int row = input.nextInt();
        int seat = input.nextInt();
    
        // Check if the row and seat are valid inputs
        if (!checkRowAndSeat(row, seat)) {
            System.out.println("Invalid input");
            return;
        }
        // Set the seat in the seating area to 0
        seatingArea[row][seat] = 0;
    
        // Find the ticket to remove based on the row and seat
        Ticket ticketToRemove = null;
        for (Ticket ticket : ticket_lst) {
            if (ticket.getRow() == row && ticket.getSeat() == seat) {
                ticketToRemove = ticket;
                break;
            }
        }
    
        // If the ticket is not found, print a message and return
        if (ticketToRemove == null) {
            System.out.println("Ticket not found");
        //    return;
        }
    
        // Remove the ticket from the list
        ticket_lst.remove(ticketToRemove);
    //    seatingArea[row][seat] = 0;
    }
    
    /**
     * Displays the available seats in the seating area.
     * 
     * @param seatingArea a 2D array representing the seating area
     *        where 0 means the seat is available and 1 means the seat is taken.
     *        The outer array represents the rows and the inner array represents the seats in each row.
     */
    public static void showAvailableSeats(int[][] seatingArea) {
        String[] rows = {"0", "1", "2"};
        for (int i = 0; i < rows.length; i++) {
            System.out.printf("Seats available in row %s: ", rows[i]);
            for (int j = 0; j < seatingArea[i].length; j++) {
                if (seatingArea[i][j] == 0) {
                    System.out.printf("%d ", j);
                }
            }
            System.out.println();
        }
    }
    /**
     * Saves the seating area to a file named "output.txt".
     * @param seatingArea a 2D array representing the seating area.
     */
    public static void save(int[][] seatingArea) {
        try {
            // Create a new File object for "output.txt".
            File outputFile = new File("output.txt");
            // Create a new FileWriter object to write to outputFile.
            FileWriter writer = new FileWriter(outputFile);
            
            // Iterate through each row of seatingArea.
            for (int[] row : seatingArea) {
                // Iterate through each seat in the current row.
                for (int seat : row) {
                    // Write the current seat to the file.
                    writer.write(seat + " ");
                }
                // Move to the next line of the file.
                writer.write("\n");
            }
            
            // Close the FileWriter object.
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while writing to file");
            e.printStackTrace();
        }
    }
    
        
    /**
     * Loads seating information from a file and loads it into the provided seatingArea two-dimensional array.
     * 
     * @param seatingArea the two-dimensional array representing the seating area
     */
    public static void load(int[][] seatingArea) {
        // Open the file for reading
        File file = new File("output.txt");
        try (Scanner scanner = new Scanner(file)) {
            // Define the row sizes for each section of the seating area
            int[] rowSizes = {12, 16, 20};
            // Iterate through each section of the seating area
            for (int i = 0; i < 3; i++) {
                // Iterate through each row in the section
                for (int j = 0; j < rowSizes[i]; j++) {
                    // Read the value from the file and store it in the corresponding element of the seatingArea array
                    seatingArea[i][j] = scanner.nextInt();
                }
            }
        } catch (IOException e) {
            // If an error occurs while reading from the file, print an error message and stack trace
            System.out.println("Error while reading from file");
            e.printStackTrace();
        }
    }

    /**
     * This method displays information about tickets in a list, including the seat and row number,
     * the ticket price, and the personal information of the ticket holder.
     * If the provided list is empty, the method prints a message indicating that the list is empty.
     *
     * @param ticket_lst the list of tickets to display information about
     */
    public static void showTicketsInfo(List<Ticket> ticket_lst) {
        if (ticket_lst.isEmpty()) {
            System.out.println("The ticket list is empty");
            return;
        }
    
        int totalPrice = 0;
        for (Ticket ticket : ticket_lst) {
            System.out.printf("%s %s %d\n", ticket.getRow(), ticket.getSeat(), ticket.getPrice());
            System.out.printf("%s %s %s\n", ticket.getPerson().getName(), ticket.getPerson().getSurname(), ticket.getPerson().getEmail());
            totalPrice += ticket.getPrice();
        }
        System.out.println("Total price: " + totalPrice);
}
    
    /**
    Sorts a list of tickets based on their prices in ascending order.
    If the list has less than two tickets, it returns the original list.
    @param ticket_lst - the list of tickets to be sorted
    @return a sorted list of tickets based on their prices in ascending order
    */
    public static List<Ticket> sortTickets(List<Ticket> ticket_lst) {
        // Check if the list has less than two tickets
        if (ticket_lst.size() < 2) {
            System.out.println("There is nothing to sort");
            return ticket_lst;
        }
        // Create a new list and copy the elements of the original list into it
        List<Ticket> sorted_tickets = new ArrayList<>(ticket_lst);
        // Sort the tickets in ascending order based on their prices
        for (int i = 0; i < sorted_tickets.size() - 1; i++) {
            for (int j = i + 1; j < sorted_tickets.size(); j++) {
                Ticket ticket1 = sorted_tickets.get(i);
                Ticket ticket2 = sorted_tickets.get(j);
                if (ticket1.getPrice() > ticket2.getPrice()) {
                    sorted_tickets.set(i, ticket2);
                    sorted_tickets.set(j, ticket1);
                }
            }
        }
        return sorted_tickets;
    } 
}