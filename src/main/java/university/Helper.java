package university;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Helper {

    private static final Scanner sc = new Scanner(System.in);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getStringFromUser(String message) {
        System.out.print(message + ": ");
        return sc.nextLine();
    }

    public static int getIntFromUser(String message) {
        System.out.print(message + ": ");
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number. " + message + ": ");
            sc.next();
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
}
