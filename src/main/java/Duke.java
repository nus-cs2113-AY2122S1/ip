import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "    ——————————————————————————————————————————————————————————————";
        System.out.println(divider);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(divider);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(!line.equals("bye")) {
            System.out.println(divider);
            System.out.print("    ");
            System.out.println(line);
            System.out.println(divider);
            line = in.nextLine();
        }
        System.out.println(divider);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
