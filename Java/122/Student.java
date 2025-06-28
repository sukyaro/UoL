public class Student {
    public boolean hasSubmitted = false;

    private String name;
    private String email;
    private int yearOfBirth;
    private int enrolmentYear;
    private int studentId;
    private int grade = 0;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getEnrolmentYear() {
        return enrolmentYear;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getGrade() {
        return grade;
    }

    public void submitCoursework() {
        hasSubmitted = true;
    }

    public Student(String name, String email, int yearOfBirth, int enrolmentYear, int studentId) {
        this.name = name;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
        this.enrolmentYear = enrolmentYear;
        this.studentId = studentId;
    }

    public Student (String name, String email, int yearOfBirth) {
        this.name = name;
        this.email = email;
        this.yearOfBirth = yearOfBirth;
    }

    public Student (String name, String email, String yearOfBirth) {
        this.name = name;
        this.email = email;
        String[] date = yearOfBirth.split("/");
        this.yearOfBirth = Integer.parseInt(date[2]);
    }

    public Student () {

    }

    public void updateGrade(int newGrade) {

    if (newGrade > 100 || newGrade < 0) {
        System.out.println("Enter a grade from 0-100.");
        return;
    } else {
        this.grade = newGrade;
    }

    // otherwise set grade and hasSubmitted.
    }

    public static void main(String[] args) {
        // Creating a Student object using the constructor
        Student alice = new Student("Alice", "aliceXtreme@aol.com", 1984, 2021, 1234567);

        // Printing details to verify
        System.out.println("Name: " + alice.name);
        System.out.println("Email: " + alice.email);
        System.out.println("Year of Birth: " + alice.yearOfBirth);
        System.out.println("Enrolment Year: " + alice.enrolmentYear);
        System.out.println("Student ID: " + alice.studentId);
    }
}

