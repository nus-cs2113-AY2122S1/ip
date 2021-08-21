import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________\n");
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________\n");

        //Echo
        line = in.nextLine();
        while (line.compareTo("bye") != 0) {
            System.out.println("____________________________________\n");
            System.out.println(line);
            System.out.println("____________________________________\n");
            line = in.nextLine();
        }

        System.out.println("____________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________\n");
    }
}
