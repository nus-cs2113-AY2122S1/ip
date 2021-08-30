import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "_________________________________________________\n";

        System.out.println(logo + "Hello! I'm Duke\n" + "Whatchu want\n" + logo);

        Scanner in = new Scanner(System.in);
        String echo = "hi";

        do {
            echo = in.nextLine();
            if (!isSame(echo)) {
                System.out.println(logo + echo + "\n" + logo);
            }
        } while (!isSame(echo));

        System.out.println(logo + "Bye. Hope to see you again soon!\n" + logo);

    }

    public static boolean isSame(String word) {
        if (word.equals("Bye") || word.equals("bye")) {
            return true;
        }
        else return false;
    }
}
