//uses File handling concepts in java
import java.io.*;
import java.util.Scanner;

public class AddressBook {
    private static final String FILE_PATH = "contacts.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Address Book");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    searchContact();
                    break;
                case 4:
                    System.out.println("Exiting Address Book. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter contact phone number: ");
        String phoneNumber = scanner.nextLine();

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(name + "," + phoneNumber);
            System.out.println("Contact added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while adding contact: " + e.getMessage());
        }
    }

    private static void viewContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            System.out.println("Contacts:");
            while ((line = reader.readLine()) != null) {
                String[] contact = line.split(",");
                System.out.println("Name: " + contact[0]);
                System.out.println("Phone: " + contact[1]);
                System.out.println("------------------------");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while viewing contacts: " + e.getMessage());
        }
    }

    private static void searchContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter contact name to search: ");
        String searchName = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] contact = line.split(",");
                if (contact[0].equalsIgnoreCase(searchName)) {
                    System.out.println("Contact found:");
                    System.out.println("Name: " + contact[0]);
                    System.out.println("Phone: " + contact[1]);
                    System.out.println("------------------------");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Contact not found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while searching contacts: " + e.getMessage());
        }
    }
}
