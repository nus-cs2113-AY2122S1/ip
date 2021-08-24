import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        //beginning of level-1
        line = in.nextLine();
        while (!(line.equals("bye"))) {
            System.out.println("    " + line);
            line = in.nextLine();
        }
        System.out.println("    " + "Bye. Hope to see you again soon!");

    }
}
