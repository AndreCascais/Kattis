import java.util.Scanner;

public class wizardofodds {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        double number = s.nextDouble();

        int questions = s.nextInt();

        if (Math.log(number)/Math.log(2) > questions) {
            System.out.println("You will become a flying monkey!");
        }
        else {
            System.out.println("Your wish is granted!");
        }
    }
}
