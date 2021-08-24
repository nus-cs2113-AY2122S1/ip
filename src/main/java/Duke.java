import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String intro = "    ____________________________________________________________\n"
                + "     Hello! I'm\n"
                + "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________";
        System.out.println(intro);

        //Initialise bool to check whether program should exit loop
        boolean shouldStay = true;

        while (shouldStay) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();

            String output;

            if (line.equals("bye")) {
                output = "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________";
                shouldStay = false;
            } else {
                output = "    ____________________________________________________________\n" +
                        "     " + line + "\n" +
                        "    ____________________________________________________________\n";
            }
            System.out.println(output);
        }
    }
}
