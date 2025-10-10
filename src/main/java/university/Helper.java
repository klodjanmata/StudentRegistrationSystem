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

    public static String getStringFromUser(String message) {
        System.out.print(message + ": ");
        String input = sc.nextLine().trim();
        while (!isValidName(input)) {
            System.out.print("Invalid input. Please enter letters only. " + message + ": ");
            input = sc.nextLine().trim();
        }
        return input;
    }

    public static Long getLongFromUser(String message) {
        System.out.print(message + ": ");
        while (!sc.hasNextLong()) {
            System.out.print("Please enter a valid number. " + message + ": ");
            sc.next(); // discard invalid input
        }
        long value = sc.nextLong();
        sc.nextLine();
        return value;
    }

    public static int getIntFromUser(String message) {
        System.out.print(message + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number. " + message + ": ");
            sc.next(); // discard invalid input
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static LocalDate getLocalDateFromUser(String message) {
        System.out.println("Expected date format: dd.MM.yyyy");
        System.out.print(message + ": ");
        String input = sc.nextLine();
        try {
            return LocalDate.parse(input, DATE_FORMATTER);
        } catch (Exception e) {
            System.out.println("Invalid date format, using current date instead.");
            return new Date().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
    }

    // ------------------- Validation Methods -------------------
    private static boolean isValidName(String input) {
        return input.matches(NAME_REGEX);
    }
}
