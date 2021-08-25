import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);

        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String word;

        do{
            word = in.next();
            if (word.toLowerCase().equals("bye")){
                System.out.println(exit);
                break;
            }
            System.out.println("____________________________________________________________\n"
                    + word + "\n"
                    + "____________________________________________________________\n");
        } while (!word.toLowerCase().equals("bye"));

    }
}
