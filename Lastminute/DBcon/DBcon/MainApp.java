
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LMUserDAO LMUserDAO = new LMUserDAO();

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter username for registration: ");
                    String registerUsername = scanner.next();
                    System.out.print("Enter password for registration: ");
                    String registerPassword = scanner.next();

                    try {
                        LMUserDAO.register(registerUsername, registerPassword);
                        System.out.println("Registration successful!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter username for login: ");
                    String loginUsername = scanner.next();
                    System.out.print("Enter password for login: ");
                    String loginPassword = scanner.next();

                    try {
                        if (LMUserDAO.authenticate(loginUsername, loginPassword)) {
                            System.out.println("Login successful!");
                        } else {
                            System.out.println("Login failed. Wrong username or password.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}