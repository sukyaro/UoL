import java.util.Scanner;

public class VGather {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How Many Students in Class?");
        float numOfStudents = input.nextInt();
        float sumGrade = 0;
        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("Enter a grade:");
            sumGrade += input.nextInt();
        }
        double rounded = Math.round((sumGrade / numOfStudents) * 100.0) / 100.0; // Rounds to 2 decimal places
        System.out.println(rounded);
    }
}