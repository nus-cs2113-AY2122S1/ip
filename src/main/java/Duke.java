import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.UnknownCommandException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static String instructions = "Hello! Welcome to Duke. I am your personal task tracker.\n"
            + "As of now, I can help you track Todos, Deadlines and Events. "
            + "Mark your tasks with either \"todo\", \"deadline\" or \"event\" at the start. \n"
            + "For deadlines and events, after your task, please enter either \"by (your deadline)\" "
            + "or \"at (your event timing)\".\n"
            + "To see all your tasks, enter \"list\". \n"
            + "To mark a task as done, enter \"done (task number)\". \n"
            + "To delete a task, enter \"delete (task number)\". \n"
            + "To exit this program, enter \"bye\". \n"
            + "And that's all! Hope you find me helpful! :) \n";

    public static String helpMessage = "Enter \"help\" if you need help using me! \n";

    public static String greeting = "____________________________________________________________\n"
            + "Hello! I'm Duke\n"
            + logo
            + "What can I do for you?\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String goodbye = "____________________________________________________________\n"
            + "Bye. Hope to see you again soon!\n"
            + "____________________________________________________________\n";


    public static String emptyTaskError = "____________________________________________________________\n"
            + "Please do not leave the description of the task empty!\n"
            + helpMessage
            + "____________________________________________________________\n";

    public static String invalidTaskError = "____________________________________________________________\n"
            + "I don't know what that means! It's either an invalid command or you have formatted the task wrongly.\n"
            + helpMessage
            + "____________________________________________________________\n";

    //public static Task[] taskList = new Task[100];
    public static ArrayList<Task> taskList = new ArrayList<>();
    //public static int itemCount = 0;

    //main function to process input
    public static void processLine(String line) throws UnknownCommandException, EmptyTaskException, InvalidCommandException {
        boolean isTodoTask = line.startsWith("todo");
        boolean isDeadlineTask = line.startsWith("deadline");
        boolean isEventTask = line.startsWith("event");
        boolean isProperTask = isTodoTask || isDeadlineTask || isEventTask;

        if (line.equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are your remaining tasks:");

            for (int i = 1; i <= taskList.size(); i++) {
                System.out.println(
                        Integer.toString(i)
                                + ". "
                                + taskList.get(i - 1)
                );
            }
            System.out.println("____________________________________________________________\n");

        } else if (line.startsWith("done")) {
            try {
                int i = Integer.parseInt(line.substring(5)) - 1;
                taskList.get(i).markAsDone();

                System.out.println("____________________________________________________________\n"
                        + "Well done! I've marked this task as done: \n"
                        + taskList.get(i)
                        + System.lineSeparator()
                        + "____________________________________________________________\n"
                );
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please mark a valid task number as done!\n");

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please mark a task number as done!\n");
            }

        } else if (isProperTask) {
            //checks for task commands
            if (isTodoTask) {
                String actualTask = line.substring(4).trim();
                if (actualTask.isBlank()) {
                    throw new EmptyTaskException();
                }
                taskList.add(new Todo(actualTask));
            } else if (isDeadlineTask) {
                int indexOfSlash = line.indexOf("/");
                String actualTask = line.substring(8, indexOfSlash - 1).trim();
                String deadlineBy = line.substring(indexOfSlash + 4);
                if (actualTask.isBlank()) {
                    throw new EmptyTaskException();
                }
                if (!actualTask.contains("/by")) {
                    throw new InvalidCommandException();
                }
                taskList.add(new Deadline(actualTask, deadlineBy));

            } else {
                int indexOfSlash = line.indexOf("/");
                String actualTask = line.substring(5, indexOfSlash - 1).trim();
                String eventAt = line.substring(indexOfSlash + 4);
                if (actualTask.isBlank()) {
                    throw new EmptyTaskException();
                }
                if (!actualTask.contains("/at")) {
                    throw new InvalidCommandException();
                }
                taskList.add(new Event(actualTask, eventAt));
            }

            System.out.println("____________________________________________________________\n"
                    + "Got it! I've added this task: "
                    + System.lineSeparator()
                    + "  "
                    + taskList.get(taskList.size() - 1)
                    + System.lineSeparator()
                    + "Now you have "
                    + Integer.toString(taskList.size())
                    + " tasks left in the list."
                    + System.lineSeparator()
                    + "____________________________________________________________\n"
            );


        } else if (line.startsWith("help")) {
            System.out.println(instructions);

        } else if (line.startsWith("delete")) {
            try {
                int i = Integer.parseInt(line.substring(7)) - 1;
                String deletedTask = taskList.get(i).toString();
                taskList.remove(i);
                System.out.println("____________________________________________________________\n"
                        + "Noted. I've removed this task: \n"
                        + deletedTask
                        + System.lineSeparator()
                        + "Now you have " + taskList.size() + " tasks in the list.\n"
                        + "____________________________________________________________\n"
                );

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Choose a task to delete!\n");

            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please choose a valid task number to delete!\n");
            }

        } else {
            throw new UnknownCommandException();
        }
    }

    public static void main(String[] args) {
        System.out.println(greeting);

        String line;
        Scanner in = new Scanner(System.in);

        //user input loop
        while (true) {
            line = in.nextLine();

            //checks for exit command
            if (line.equals("bye")) {
                System.out.println(goodbye);
                return;
            }

            //process the other commands
            try {
                processLine(line);

            } catch (UnknownCommandException | StringIndexOutOfBoundsException | InvalidCommandException e) {
                System.out.println(invalidTaskError);

            } catch (EmptyTaskException e) {
                System.out.println(emptyTaskError);

            }

        }
    }
}


