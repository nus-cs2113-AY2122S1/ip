import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void echo(String line) {
        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(" " + line);
        System.out.println(separator);
    }

    public static void showList(String[] texts) {
        for (int i = 0; i < texts.length; i++) {
            System.out.println(" " + Integer.toString(i + 1) + ". " + texts[i]);
        }
    }

    public static void main(String[] args) {
        String separator = "____________________________________________________________";
        String line;
        Scanner in = new Scanner(System.in);
        int textCount = 0;
        String[] texts = new String[100];

        System.out.println(separator);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(separator);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(separator);
            if (line.equals("list")) {
                showList(Arrays.copyOf(texts, textCount));
            } else {
                texts[textCount] = line;
                System.out.println(" added: " + line);
                textCount++;
            }
            System.out.println(separator);
            line = in.nextLine();
        }

        System.out.println(separator);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
