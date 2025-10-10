package university;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Helper {

    private static final Scanner sc = new Scanner(System.in);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    // Regex for names: letters, spaces, hyphens, apostrophes
    private static final String NAME_REGEX = "^[A-Za-z\\s\\-']+$";
    // Simple email regex
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    private static final int MAX_ATTEMPTS = 5;

    // ------------------- String & Name -------------------
    public static String getStringFromUser(String message) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print(message + ": ");
            String input = sc.nextLine().trim();
            if (isValidName(input)) return input;

            System.out.println("Invalid input. Please enter letters only.");
            attempts++;
        }
        System.out.println("Too many invalid attempts. Exiting.");
        System.exit(1); // Exit program
        return null; // unreachable, just for compiler
    }

    // ------------------- Email -------------------
    public static String getEmailFromUser(String message) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print(message + ": ");
            String input = sc.nextLine().trim();
            if (isValidEmail(input)) return input;

            System.out.println("Invalid email format. Please enter a valid email.");
            attempts++;
        }
        System.out.println("Too many invalid attempts. Exiting.");
        System.exit(1);
        return null;
    }

    // ------------------- Numbers -------------------
    public static Long getLongFromUser(String message) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print(message + ": ");
            if (sc.hasNextLong()) {
                long value = sc.nextLong();
                sc.nextLine();
                return value;
            } else {
                System.out.println("Please enter a valid number.");
                sc.next(); // discard invalid input
                attempts++;
            }
        }
        System.out.println("Too many invalid attempts. Exiting.");
        System.exit(1);
        return null;
    }

    public static int getIntFromUser(String message) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.print(message + ": ");
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } else {
                System.out.println("Please enter a valid number.");
                sc.next();
                attempts++;
            }
        }
        System.out.println("Too many invalid attempts. Exiting.");
        System.exit(1);
        return -1;
    }

    // ------------------- Date -------------------
    public static LocalDate getLocalDateFromUser(String message) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            System.out.println("Expected date format: dd.MM.yyyy");
            System.out.print(message + ": ");
            String input = sc.nextLine();
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid date format. Try again.");
                attempts++;
            }
        }
        System.out.println("Too many invalid attempts. Using current date instead.");
        return new Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    // ------------------- Validation Methods -------------------
    private static boolean isValidName(String input) {
        return input.matches(NAME_REGEX);
    }

    private static boolean isValidEmail(String input) {
        return input.matches(EMAIL_REGEX);
    }
}
