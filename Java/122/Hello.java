
import java.util.Scanner;


public class Hello {
    public static void main(String[] args) {
        System.out.println("Please enter your name:");
        Scanner name = new Scanner(System.in);
        System.out.println("Hello" + " " + name.nextLine() + "!"); 
    }
}