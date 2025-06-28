public class Student extends Person implements Degreeable, Billable {
    private int grade;

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void awardDegree() {
        int grade = getGrade();
        
        if (grade > 40) {
            System.out.println("You Passed Your Degree, hooray!");
        }
    }

    public void payBill(int bill) {
        System.out.println(bill);
    }
}
  
