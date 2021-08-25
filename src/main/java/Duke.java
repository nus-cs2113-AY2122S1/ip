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

        //Initialize List
        Task[] toDo = new Task[10];
        int trackIndex = 0;
        //Initialize bool to check whether program should exit loop
        boolean isProgress = true;

        while (isProgress) {
            line = in.nextLine();

            String output;

            if (line.equals("bye")) {
                output = "    ____________________________________________________________\n"
                        + "     Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________";
                isProgress = false;
            } else if (line.equals("list")) {
                output = "    ____________________________________________________________\n";
                for (int i = 0; trackIndex > i; i++) {
                    String record = "     " + (i + 1) + ".["
                            + toDo[i].getStatusIcon() + "] "+ toDo[i].getDescription() + "\n";
                    output = output.concat(record);
                }
                output = output.concat("    ____________________________________________________________\n");
            } else if (line.contains("done")) {
                int dividerPosition = line.indexOf(" ");
                int numberInTaskArray = Integer.parseInt(line.substring(dividerPosition + 1)) - 1;
                toDo[numberInTaskArray].setIsDone();
                output = "____________________________________________________________\n"
                        + "     Nice! I've marked this task as done: \n"
                        + "       [X] " + toDo[numberInTaskArray].getDescription() + "\n"
                        + "    ____________________________________________________________";
            } else {
                output = "    ____________________________________________________________\n"
                        + "     added: " + line + "\n"
                        + "    ____________________________________________________________\n";
                toDo[trackIndex] = new Task(line);
                trackIndex++;
            }
            System.out.println(output);
        }
    }
}


