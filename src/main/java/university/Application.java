package university;

public class Application {

    private static ApplicationManager applicationManager = new ApplicationManager();

    public static void main(String[] args) {
        while (true) {
            Menu.mainMenu();
            int choice = getChoice();
            if (manageAction(choice)) {
                break;
            }
        }
    }

    private static int getChoice() {
        while (true) {
            try {
                return Helper.getIntFromUser("Enter your selection");
            } catch (Exception e) {
                System.out.println("Invalid input! Try again.");
            }
        }
    }

    private static boolean manageAction(int choice) {
        switch (choice) {
            case 1:
                System.out.println("You have chosen: Manage Students");
                manageStudents();
                break;
            case 2:
                System.out.println("You have chosen: Manage Professors");
                manageProfessors();
                break;
            case 3:
                System.out.println("You have chosen: Manage Courses");
                manageCourses();
                break;
            case 4:
                System.out.println("You have chosen: Manage Enrollments");
                manageEnrollments();
                break;
            case 5:
                System.out.println("You have chosen: Manage Departments");
                manageDepartments();
            case 0:
                System.out.println("Exiting system...");
                return true;
            default:
                System.out.println("Invalid option! Please try again.");
                break;
        }
        return false;
    }

    private static void manageStudents() {
        boolean running = true;
        while (running) {
            Menu.studentMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    System.out.println("Register Student selected");
                    applicationManager.registerStudent();
                    break;
                case 2:
                    System.out.println("Update Student selected");
                    applicationManager.updateStudent();
                    break;
                case 3:
                    System.out.println("Delete Student selected");
                    applicationManager.deleteStudent();
                    break;
                case 4:
                    System.out.println("List Students selected");
                    applicationManager.listStudents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    }

    private static void manageProfessors() {
        boolean running = true;
        while (running) {
            Menu.professorMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    System.out.println("Add Professor selected");
                    applicationManager.addProfessor();
                    break;
                case 2:
                    System.out.println("List Professors selected");
                   applicationManager.listProfessors();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    }

    private static void manageCourses() {
        boolean running = true;
        while (running) {
            Menu.courseMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    System.out.println("Create Course selected");
                    applicationManager.createCourse();
                    break;

                case 2:
                    System.out.println("List Courses selected");
                    applicationManager.listCourses();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    }

    private static void manageEnrollments() {
        boolean running = true;
        while (running) {
            Menu.enrollmentMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    System.out.println("Enroll Student in Course selected");
                    applicationManager.enrollStudent();
                    break;
                case 2:
                    System.out.println("List Students in Course selected");
                    applicationManager.listStudentsInCourseSelected();
                    break;
                case 3:
                    System.out.println("List Courses for a Student selected");
                    applicationManager.listCoursesForStudentSelected();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    }
    private static void manageDepartments() {
        boolean running = true;
        while (running) {
            Menu.departmentMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    System.out.println("Create Department selected");
//                    applicationManager.createDepartment();
                    break;
                case 2:
                    System.out.println("List Departments selected");
//                    applicationManager.listDepartments();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
                    break;
            }
        }
    }
}