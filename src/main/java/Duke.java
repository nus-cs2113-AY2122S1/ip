import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke" + "\n"
                + "What can I do for you?";

        String farewell = "Bye. Hope to see you again soon!";

        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);

        String phrase = sc.nextLine();

        while (!phrase.equals("bye")) {
            System.out.println(phrase);
            phrase = sc.nextLine();
        }

        System.out.println(farewell);
    }
}
