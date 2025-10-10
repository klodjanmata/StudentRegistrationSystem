package university;

public class Menu {

    private static final String LINE = "╔══════════════════════════════════════════════════╗";
    private static final String BOTTOM_LINE = "╚══════════════════════════════════════════════════╝";

    public static void mainMenu() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("║        🎓  Welcome to Student Registration System        ║");
        System.out.println(BOTTOM_LINE);
        System.out.println("  1. 👨‍🎓 Manage Students");
        System.out.println("  2. 👨‍🏫 Manage Professors");
        System.out.println("  3. 📚 Manage Courses");
        System.out.println("  4. 📝 Manage Enrollments");
        System.out.println("  5. 🏢 Manage Departments");
        System.out.println("  0. ❌ Exit");
        System.out.println("───────────────────────────────────────────────────");
        System.out.print("👉 Enter your selection: ");
    }

    public static void studentMenu() {
        printSectionHeader("Student Management");
        System.out.println("  1. ➕ Register Student");
        System.out.println("  2. ✏️  Update Student");
        System.out.println("  3. 🗑️  Delete Student");
        System.out.println("  4. 📄 List Students");
        System.out.println("  0. 🔙 Back to Main Menu");
        printSectionFooter();
    }

    public static void professorMenu() {
        printSectionHeader("Professor Management");
        System.out.println("  1. ➕ Add Professor");
        System.out.println("  2. 📄 List Professors");
        System.out.println("  0. 🔙 Back to Main Menu");
        printSectionFooter();
    }

    public static void courseMenu() {
        printSectionHeader("Course Management");
        System.out.println("  1. ➕ Create Course");
        System.out.println("  2. 📄 List Courses");
        System.out.println("  0. 🔙 Back to Main Menu");
        printSectionFooter();
    }

    public static void enrollmentMenu() {
        printSectionHeader("Enrollment Management");
        System.out.println("  1. 📝 Enroll Student in Course");
        System.out.println("  2. 👨‍🎓 List Students in Course");
        System.out.println("  3. 📚 List Courses for a Student");
        System.out.println("  0. 🔙 Back to Main Menu");
        printSectionFooter();
    }

    public static void departmentMenu() {
        printSectionHeader("Department Management");
        System.out.println("  1. ➕ Create Department");
        System.out.println("  2. 📄 List Departments");
        System.out.println("  0. 🔙 Back to Main Menu");
        printSectionFooter();
    }

    // Helper methods for consistent section formatting
    private static void printSectionHeader(String title) {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.printf ("║  📌 %-45s ║%n", title);
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private static void printSectionFooter() {
        System.out.println("───────────────────────────────────────────────────");
        System.out.print("👉 Enter your selection: ");
    }
}
