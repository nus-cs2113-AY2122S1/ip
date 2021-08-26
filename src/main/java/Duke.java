import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String Greetings = "     _______________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "     _______________________\n";

        System.out.println(Greetings);
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            String echo = "     _______________________\n"
                    + "     " + line + "\n"
                    + "     _______________________\n";
            System.out.println(echo);
            line = in.nextLine();
        }

        String Bye = "     _______________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "     _______________________\n";
        System.out.println(Bye);
    }


}
