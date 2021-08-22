import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String text;
        ArrayList<String> text_store = new ArrayList<String>();
        Scanner in = new Scanner(System.in);

        System.out.println("\tHello! I'm Duke \uD83D\uDE04");
        System.out.println("\tWhat can I do for you?");
        text = in.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                for (int i=0; i<text_store.size(); i++) {
                    System.out.println("\t" + (i+1) + ". " + text_store.get(i));
                }
            } else {
                System.out.println("\tAdded: " + text);
                text_store.add(text);
            }
            text = in.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon! \uD83D\uDC4B");
    }
}
