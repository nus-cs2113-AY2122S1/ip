import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String[] storage = new String[100];
        int i = 0;

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        //beginning of level-1
        line = in.nextLine();
        while (!(line.equals("bye"))) {
            if (!(line.equals("list"))) {
                storage[i] = line;
                i++;
                //System.out.println(i); //check which iteration i is at
                System.out.println("    added: " + line);
                line = in.nextLine();
            }

            if (line.equals("list")) {
                for (int j = 0; j < i; j++) {
                    System.out.println("    " + (j + 1) + ". " + storage[j]);
                }
                line = in.nextLine();
            }
        }
        System.out.println("    " + "Bye. Hope to see you again soon!");
    }
}
