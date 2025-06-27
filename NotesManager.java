import java.io.*;
import java.util.Scanner;

public class NotesManager {
    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Text-Based Notes Manager ---");
            System.out.println("1. Add a Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    addNote(scanner);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void addNote(Scanner scanner) {
        System.out.print("Enter your note: ");
        String note = scanner.nextLine();

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // 'true' enables appending
            writer.write(note + "\n");
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note.");
            e.printStackTrace();
        }
    }

    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean hasNotes = false;

            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
                hasNotes = true;
            }

            if (!hasNotes) {
                System.out.println("No notes found.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes file found. Add a note first.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading notes.");
            e.printStackTrace();
        }
    }
}
