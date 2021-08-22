import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String text;
        Scanner in = new Scanner(System.in);

        System.out.println("\tHello! I'm Duke \uD83D\uDE04");
        System.out.println("\tWhat can I do for you?");
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println("\t" + text);
            text = in.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon! \uD83D\uDC4B");
    }
}
