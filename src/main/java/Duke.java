import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        WelcomeMessage();
        String line;
        Scanner in = new Scanner(System.in);

        Task[] toDo = new Task[100];
        int trackIndex = 0;
        boolean isProgress = true;

        while (isProgress) {
            line = in.nextLine();

            if (line.equals("bye")) {
                isProgress = false;
                goodbyeMessage();
            } else if (line.equals("list")) {
                handleListRequest(toDo, trackIndex);
            } else if (line.contains("done")) {
                handleDoneRequest(line, toDo);
                trackIndex++;
            } else if (line.contains("todo")) {
                handleToDoRequest(line, toDo, trackIndex);
                trackIndex++;
            } else if (line.contains("deadline")) {
                handleDeadlineRequest(line, toDo, trackIndex);
                trackIndex++;
            } else if (line.contains("event")) {
                handleEventRequest(line, toDo, trackIndex);
                trackIndex++;
            } else {
                handleTaskRequest(line, toDo, trackIndex);
                trackIndex++;
            }
        }
    }

    private static void handleTaskRequest(String line, Task[] toDo, int trackIndex) {
        String output;
        output = "    ____________________________________________________________\n"
                + "     added: " + line + "\n"
                + "    ____________________________________________________________\n";
        toDo[trackIndex] = new Task(line);
        System.out.println(output);
    }

    private static void handleEventRequest(String line, Task[] toDo, int trackIndex) {
        String output;
        int dividerPosition = line.indexOf("/");
        String description = line.substring(6, dividerPosition - 1);
        String date = line.substring(dividerPosition + 4);
        toDo[trackIndex] = new Event(description, date);
        output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "      " + toDo[trackIndex].toString() + "\n"
                + "     Now you have " + (trackIndex + 1) + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(output);
    }

    private static void handleDeadlineRequest(String line, Task[] toDo, int trackIndex) {
        String output;
        int dividerPosition = line.indexOf("/");
        String description = line.substring(9, dividerPosition - 1);
        String date = line.substring(dividerPosition + 4);
        toDo[trackIndex] = new Deadline(description, date);
        output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "      " + toDo[trackIndex].toString() + "\n"
                + "     Now you have " + (trackIndex + 1) + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(output);
    }

    private static void handleToDoRequest(String line, Task[] toDo, int trackIndex) {
        String output;
        String description = line.substring(5);
        toDo[trackIndex] = new Todo(description);
        output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "      " + toDo[trackIndex].toString() + "\n"
                + "     Now you have " + (trackIndex + 1)  + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(output);
    }

    private static void handleDoneRequest(String line, Task[] toDo) {
        String output;
        int dividerPosition = line.indexOf(" ");
        int numberInTaskArray = Integer.parseInt(line.substring(dividerPosition + 1)) - 1;
        toDo[numberInTaskArray].setIsDone();
        output = "____________________________________________________________\n"
                + "     Nice! I've marked this task as done: \n"
                + "       [X] " + toDo[numberInTaskArray].getDescription() + "\n"
                + "    ____________________________________________________________";
        System.out.println(output);
    }

    private static void handleListRequest(Task[] toDo, int trackIndex) {
        String output;
        output = "    ____________________________________________________________\n";
        for (int i = 0; trackIndex > i; i++) {
            String record = "     " + (i + 1) + "." + toDo[i].toString() + "\n";
            output = output.concat(record);
        }
        output = output.concat("    ____________________________________________________________");
        System.out.println(output);
    }

    private static void goodbyeMessage() {
        String output;
        output = "    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________";

        System.out.println(output);
    }

    private static void WelcomeMessage() {
        String intro = "     ____________________________________________________________\n"
                + "     Hello! I'm\n"
                + "     ____        _\n"
                + "    |  _ \\ _   _| | _____\n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________";
        System.out.println(intro);
    }
}


