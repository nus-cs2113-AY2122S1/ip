import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String intro = "Top of the morning my good sir, what can I do for you on this fine day?";
        String farewell = "I bid you farewell my good man. Good Bye.";
        Scanner sc = new Scanner(System.in);
        System.out.println(intro);
        String input;
        do {
            input = sc.nextLine();
            input = input.stripTrailing();
            input = input.stripLeading();
            if (input.equals("end")) {
                System.out.println(farewell);
            } else {
                System.out.println((input));
            }
        } while (!(input.equals("end")));
    }
}
