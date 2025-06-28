public class ResearchCouncil implements Billable, Emailable {
    private String name;
    private String email;
    
    public void payBill(int billed) {
        System.out.println(billed);
    }

    public void sendEmail() {
        System.out.println("sendto: " + email + "Hi " + name + ",\n");
    }

    public static void main(String[] args) {
        // Creating instances of different classes

        // Testing Student
        Student student = new Student();
        student.setName("Alice");
        student.setEmail("alice@example.com");
        student.setGrade(90);

        System.out.println("Student Name: " + student.getName());
        System.out.println("Student Email: " + student.getEmail());
        System.out.println("Student Grade: " + student.getGrade());

        // Testing Lecturer
        Lecturer lecturer = new Lecturer();
        lecturer.setName("Dr. Bob");
        lecturer.setEmail("bob@example.com");
        lecturer.setTimeTable("Monday, Wednesday, Friday");

        System.out.println("Lecturer Name: " + lecturer.getName());
        System.out.println("Lecturer Email: " + lecturer.getEmail());
        System.out.println("Lecturer Timetable: " + lecturer.getTimeTable());

        // Testing Professor
        Professor professor = new Professor();
        professor.setName("Prof. Charlie");
        professor.setEmail("charlie@example.com");
        professor.setTimeTable("Tuesday, Thursday");
        professor.setBudget(5000);

        System.out.println("Professor Name: " + professor.getName());
        System.out.println("Professor Email: " + professor.getEmail());
        System.out.println("Professor Timetable: " + professor.getTimeTable());
        System.out.println("Professor Budget: " + professor.getBudget());

        // Testing Research Council
        ResearchCouncil researchCouncil = new ResearchCouncil();
        System.out.println("Research Council instance created.");

        // Testing interfaces
        Degreeable degreeable = student;
        degreeable.awardDegree();

        Payable payable = professor;
        payable.payAmount(2000);

        Emailable emailer = lecturer;
        emailer.sendEmail();

        Billable billable = student;
        billable.payBill(100);

        System.out.println("All tests completed successfully.");
    }
}