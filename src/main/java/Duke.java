import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner s1 = new Scanner(System.in);
        String input = s1.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = s1.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
