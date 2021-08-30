import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "        _|       _|_|        _|_|_|      _|      _|    _|_|_|        _|_|_|\n"
                    + "        _|     _|    _|      _|    _|    _|      _|      _|        _|\n"
                    + "        _|     _|_|_|_|      _|_|_|      _|      _|      _|          _|_|\n"
                    + " _|     _|     _|    _|      _|    _|      _|  _|        _|              _|\n"
                    + "  _| _|    _|  _|    _|  _|  _|    _|  _|    _|    _|  _|_|_|  _|  _|_|_|\n";
        System.out.print("Initialising...............\n");
        String line = "--------------------------------------------------------------------------------";
        System.out.println(line + "\n" + logo);
        // Prints enclosed text in italics
        String byline = "\033[3m----------------------------------Just a rather very intelligent system---------\033[0m\n";

        System.out.println(byline + "Good Evening Sir! I'm J.A.R.V.I.S");
        System.out.println("How may I be of assistance to you today?");
        System.out.println(line + "\n");

        int itemIndex = 0;
        boolean saidBye = false;

        // Init array with space for 100 strings
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);

        while (!saidBye) {

            String command = in.nextLine();

            if (command.equals("bye")) {
                saidBye = true;
                System.out.println("Affirmative sir, I'll shut down all operations");
            } else if (command.equals("list")) {
                System.out.println(line + "\n" + "Here are the current tasks in your list:");
                // Iterates through and prints stored tasks in the format: 1.[ ][ ] read book
                //                                                         2.[ ][ ] buy bread
                for (int count = 0; count < itemIndex; count++) {
                    System.out.println(count + 1 + "." + tasks[count].printTask());
                }
                System.out.println();
                System.out.println(line);
            } else if (command.contains("done")) {
                // When user enters string "done 2", string is split to extract the index 2 only
                int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                // Checks if given index holds a task and throws error message if no such task exists
                if (taskDoneIndex > itemIndex - 1 || taskDoneIndex < 0) {
                    System.out.println("Apologies sir but, it seems that task hasn't been created yet :(\n" + line);
                } else {
                    // Selects task to be modified with command "done"
                    Task taskChosen = tasks[taskDoneIndex];
                    // Checks if task has already been marked as done
                    if (taskChosen.isDone()) {
                        System.out.println("Sir I believe this task has already been completed");
                    } else {
                        taskChosen.changeStatusDone(true);
                        System.out.println(line + "\n" + "As you wish sir, this task will be marked as done!");
                    }
                    // Otherwise, marks task as done with X. E.g. 1.[ ][X] read book if user inputs "done 1"
                    System.out.println("    " + taskChosen.getTaskType() + taskChosen.getStatusIcon() + " "
                            + taskChosen.getDescription() + "\n" + line);
                }
            } else if (command.equals("echo")) {
                // Simply echos given command until user types "stop"
                System.out.println("What would you like me to repeat sir?");
                Scanner echo = new Scanner(System.in);
                boolean isExit = false;
                while (!isExit) {
                    String echoLine = echo.nextLine();
                    if (echoLine.equals("stop")) {
                        isExit = true;
                        System.out.println("Okay sir, stopping echo mode");
                    }
                    else {
                        System.out.println(echoLine);
                    }
                }
            } else {
                // Create tasks for class Todo
                if (command.contains("todo")) {
                    // Reads substring containing the task description only
                    tasks[itemIndex] = new Todo(command.substring(5));
                }
                // Create tasks for class Deadline
                else if (command.contains("deadline")){
                    // Reads two substrings: 1. The task description after keyword "deadline"
                    //                       2. The actual deadline after "/by " till end of string
                    tasks[itemIndex] = new Deadline((command.substring(9, command.indexOf("/"))),
                            command.substring(command.indexOf("/") + 4));
                }

                // Create tasks for class Event
                else if (command.contains("event")){
                    // Same substring read as Deadline
                    tasks[itemIndex] = new Event((command.substring(6, command.indexOf("/"))),
                            command.substring(command.indexOf("/") + 4));
                }
                // If no keywords are used, stores new tasks into Task array
                else {
                    tasks[itemIndex] = new Task(command);
                }
                itemIndex++;
                System.out.println("Will do sir, I've added: " + System.lineSeparator() + "  " + tasks[itemIndex - 1].printTask());
                if (itemIndex == 1) {
                    System.out.printf("Now you have %d task in your list.\n", itemIndex);
                }
                else {
                    System.out.printf("Now you have %d tasks in your list.\n", itemIndex);
                }
            }
        }
    }
}