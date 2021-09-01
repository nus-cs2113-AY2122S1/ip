import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String instructions = "Hello! Welcome to Duke. I am your personal task tracker.\n"
                + "As of now, I can help you track Todos, Deadlines and Events. "
                + "Mark your tasks with either \"todo\", \"deadline\" or \"event\" at the start. \n"
                + "For deadlines and events, after your task, please enter either \"by (your deadline)\" "
                + "or \"at (your event timing)\".\n"
                + "To see all your tasks, enter \"list\". \n"
                + "To mark a task as done, enter \"done (task number)\". \n"
                + "To exit this program, enter \"bye\". \n"
                + "And that's all! Hope you find me helpful! :) \n";

        String line;
        Scanner in = new Scanner(System.in);

        Task[] taskList = new Task[100];
        int itemCount = 0;


        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + logo
                + "What can I do for you?\n"
                + "Enter \"help\" if you need help using me! \n"
                + "____________________________________________________________\n" ;

        String goodbye = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                +  "____________________________________________________________\n";

        System.out.println(greeting);

        while(true) {
            line = in.nextLine();

            boolean isTodoTask = line.startsWith("todo ");
            boolean isDeadlineTask = line.startsWith("deadline ");
            boolean isEventTask = line.startsWith("event ");
            boolean isProperTask = isTodoTask || isDeadlineTask || isEventTask;

            if (line.equals("bye")) {
                System.out.println(goodbye);
                return;
            }
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are your remaining tasks:");

                for (int i = 1; i <= itemCount; i++) {
                    System.out.println(
                            Integer.toString(i)
                            + ". "
                            + taskList[i-1]
                    );
                }
                System.out.println("____________________________________________________________\n");

            } else if (line.startsWith("done ")) {
                int i = Integer.parseInt(line.substring(5)) - 1;
                taskList[i].markAsDone();

                System.out.println("____________________________________________________________\n"
                        + "Well done! I've marked this task as done: \n"
                        + taskList[i]
                        + System.lineSeparator()
                        + "____________________________________________________________\n"
                );

            } else if (isProperTask) {

                if (isTodoTask) {
                    taskList[itemCount] = new Todo(line.substring(5));
                } else if (isDeadlineTask) {
                    int indexOfSlash = line.indexOf("/");
                    String actualTask = line.substring(9, indexOfSlash-1);
                    String deadlineBy = line.substring(indexOfSlash+4);
                    taskList[itemCount] = new Deadline(actualTask, deadlineBy);
                } else {
                    int indexOfSlash = line.indexOf("/");
                    String actualTask = line.substring(6, indexOfSlash-1);
                    String eventAt = line.substring(indexOfSlash+4);
                    taskList[itemCount] = new Event(actualTask, eventAt);
                }

                itemCount++;
                System.out.println("____________________________________________________________\n"
                        + "Got it! I've added this task: "
                        + System.lineSeparator()
                        + "  "
                        + taskList[itemCount - 1]
                        + System.lineSeparator()
                        + "Now you have "
                        + Integer.toString(itemCount)
                        + " tasks left in the list."
                        + System.lineSeparator()
                        + "____________________________________________________________\n"
                );
            } else if (line.startsWith("help")) {
                System.out.println(instructions);
            } else {
                System.out.println("Enter \"help\" to read instructions on how to use me!\n");
            }
        }
    }
}
