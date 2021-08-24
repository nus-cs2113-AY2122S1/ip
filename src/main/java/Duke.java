import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String intro = "     ____________________________________________________________\n"
                + "     Hello! I'm\n"
                + "     ____         _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________";
        System.out.println(intro);
        String line;
        Scanner in = new Scanner(System.in);

        //Initialise List (elements are initialised as null)
        String[] toDo = new String[10];
        int trackIndex = 0;
        //Initialise bool to check whether program should exit loop
        boolean shouldStay = true;

        while (shouldStay) {
            line = in.nextLine();

            String output;

            if (line.equals("bye")) {
                output = "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________";
                shouldStay = false;
            } else if (line.equals("list")) {
                output = "    ____________________________________________________________\n";
                for (int i = 0; trackIndex > i; i++) {
                    String record = "     " + (i+1)+ ". " + toDo[i] + "\n";
                    output = output.concat(record);
                }
                output = output.concat("    ____________________________________________________________\n");
            } else {
                output = "    ____________________________________________________________\n" +
                        "     added: " + line + "\n" +
                        "    ____________________________________________________________\n";
                toDo[trackIndex] = line;
                trackIndex++;
            }
            System.out.println(output);
        }
    }
}


