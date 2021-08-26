import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void greeting() {
        String greeting = " Hello! I'm pepepopo\n" +
                        " What can I do for you?";
        System.out.println(greeting);
        divider();
    }

    public static void divider() {
        String divider = "\n____________________________________________________________";
        System.out.println(divider);
    }

    public static void bye() {
        String bye = " Bye. Hope to see you again soon!";
        System.out.println(bye);
        divider();

    }

    public static void added(String text) {
        String added = "added: ";
        System.out.println("added: " + text);
    }

    public static String input() {
        String line;
        Scanner in = new Scanner(System.in);

        line = in.nextLine();
        return line;
    }

    public static void echo(String text) {
            System.out.println(text);
            divider();
    }

    public static void main(String[] args) {
        greeting();

        String [] list = new String[100];
        int listCount = 0;

        boolean isBye;
        boolean isList;

        do {
            String input = input();
            isBye = input.equals("bye");
            isList = input.equals("list");

            if (isList){
                String [] listFinal = Arrays.copyOf(list, listCount);
                int curr = 1;
                for (String item: listFinal) {
                    System.out.println(curr + ". " + item);
                    curr += 1;
                }
                divider();
            }
            else {
                list[listCount] = input;
                listCount += 1;
                added(input);
                divider();
            }

        } while (!isBye);

        bye();

    }
}
