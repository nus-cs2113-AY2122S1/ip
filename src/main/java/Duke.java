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

        Task[] toDo = new Task[100];
        int trackIndex = 0;
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
                    String record = "     " + (i + 1) + "." + toDo[i].toString() + "\n";
                    output = output.concat(record);
                }
                output = output.concat("    ____________________________________________________________\n");
            } else if (line.contains("done")) {
                int dividerPosition = line.indexOf(" ");
                //What if the number is more than 10?
                int numberInTaskArray = Integer.parseInt(line.substring(dividerPosition + 1)) - 1;
                toDo[numberInTaskArray].setIsDone();
                output = "____________________________________________________________\n"
                        + "     Nice! I've marked this task as done: \n"
                        + "       [X] " + toDo[numberInTaskArray].getDescription() + "\n"
                        + "    ____________________________________________________________";
            } else if (line.contains("todo")) {
                //Way too many lines, why not store how the output will look like as a method in the class
                String description = line.substring(5);
                toDo[trackIndex] = new Todo(description);
                output = "    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "      " + toDo[trackIndex].toString() + "\n"
                        + "     Now you have " + (trackIndex + 1)  + " tasks in the list.\n"
                        + "    ____________________________________________________________";
                trackIndex++;
            } else if (line.contains("deadline")) {
                int dividerPosition = line.indexOf("/");
                String description = line.substring(9, dividerPosition - 1);
                String date = line.substring(dividerPosition + 4);
                toDo[trackIndex] = new Deadline(description, date);
                output = "    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "      " + toDo[trackIndex].toString() + "\n"
                        + "     Now you have " + (trackIndex + 1) + " tasks in the list.\n"
                        + "    ____________________________________________________________";
                trackIndex++;
            } else if (line.contains("event")) {
                int dividerPosition = line.indexOf("/");
                String description = line.substring(6, dividerPosition - 1);
                String date = line.substring(dividerPosition + 4);
                toDo[trackIndex] = new Event(description, date);
                output = "    ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "      " + toDo[trackIndex].toString() + "\n"
                        + "     Now you have " + (trackIndex + 1) + " tasks in the list.\n"
                        + "    ____________________________________________________________";
                trackIndex++;
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


