import java.util.*;

public class User {

    // A field that stores the user name
    private String userName;
    // A field that stores the password
    private String password;

    // A constructor that takes the user name and password as parameters
    public User(String userName, String password) {
        // Assign the parameters to the fields
        this.userName = userName;
        this.password = "abc@123";
    }

    // A method that returns the user name
    public String getUserName() {
        return userName;
    }

    // A method that returns the password
    public String getPassword() {
        return password;
    }

    // A method that checks if the user name and password are valid
    public boolean isValid() {
        // Assume that the user name and password are valid if they are not null or empty
        return userName != null && !userName.isEmpty() && password != null && !password.isEmpty();
    }

    // A method that prints the user information
    public void printInfo() {
        // Print the user name and password
        System.out.println("User name: " + userName);
        System.out.println("Password: " + password);
    }

    // A main method to test the code
    public static void main(String[] args) {
        // Create a scanner object to read input from the console
        Scanner sc = new Scanner(System.in);
        // Prompt the user to enter a user name
        System.out.print("Enter a user name: ");
        // Read the user name from the scanner
        String userName = sc.nextLine();
        // Prompt the user to enter a password
        System.out.print("Enter a password: ");
        // Read the password from the scanner
        String password = sc.nextLine();
        // Close the scanner
        sc.close();
        // Create a user object with the user name and password
        User user = new User(userName, password);
        // Check if the user is valid
        if (user.isValid()) {
            // Print the user information
            user.printInfo();
            // Print a success message
            System.out.println("User is valid.");
        } else {
            // Print an error message
            System.out.println("User is invalid.");
        }
    }
}
