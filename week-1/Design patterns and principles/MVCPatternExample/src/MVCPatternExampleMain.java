public class MVCPatternExampleMain {
    public static void main(String[] args) {
        Student model = new Student("Alice", "101", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(model, view);

        controller.updateView();

        System.out.println("\nUpdating student details...");
        controller.setStudentGrade("A+");
        controller.updateView();
    }
}