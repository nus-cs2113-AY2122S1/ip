import java.util.Scanner;

public class Duke {
    static String horizontal = " __________________________________________________\n";
    static String introduction = horizontal + "   Hello! I'm Duke\n" + "   What can I do for you?\n" + horizontal;
    static String farewell = horizontal + "   Bye. Hope to see you again soon!\n" + horizontal;

    public static void echoCommand(String line) {
        System.out.println(horizontal + "    " + line + "\n" + horizontal);
    }

    public static void main(String[] args) {
        System.out.println(introduction);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            echoCommand(line);
            line = in.nextLine();
        }
        System.out.println(farewell);
    }
}
