import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String[] list = new String[100];
        int listItem = 0;

        Scanner s1 = new Scanner(System.in);
        String input = s1.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < listItem; i += 1) {
                    System.out.println(Integer.toString(i + 1) + ". " + list[i]);
                }
            } else {
                list[listItem] = input;
                System.out.println("added: " + input);
                listItem += 1;
            }
            input = s1.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
