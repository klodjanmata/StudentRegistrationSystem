package university;

public class Menu {

    public static void mainMenu() {
        System.out.println("\n");
        System.out.println("______Welcome to Student Registration System_______");
        System.out.println("Choose your action:");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Professors");
        System.out.println("3. Manage Courses");
        System.out.println("4. Manage Enrollments");
        System.out.println("0. Exit");
    }

    public static void studentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Register Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. List Students");
        System.out.println("0. Back to Main Menu");
    }

    public static void professorMenu() {
        System.out.println("\n--- Professor Management ---");
        System.out.println("1. Add Professor");
        System.out.println("2. List Professors");
        System.out.println("0. Back to Main Menu");
    }

    public static void courseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Create Course");
        System.out.println("2. List Courses");
        System.out.println("0. Back to Main Menu");
    }

    public static void enrollmentMenu() {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student in Course");
        System.out.println("2. List Students in Course");
        System.out.println("3. List Courses for a Student");
        System.out.println("0. Back to Main Menu");
    }
}
