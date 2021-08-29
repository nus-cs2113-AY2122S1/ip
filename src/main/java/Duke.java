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
                // Iterates through and prints stored tasks in the format: 1.[ ] read book
                //                                                         2.[ ] buy bread
                for (int count = 0; count < itemIndex; count++) {
                    System.out.println(count + 1 + "." + tasks[count].getStatusIcon() + " " + tasks[count].getDescription());
                }
                System.out.println(line);
            } else if (command.contains("done")) {
                // When user enters string "done 2", string is split to extract the index 2 only
                int taskDoneIndex = Integer.parseInt(command.split(" ")[1]) - 1;
                // Checks if given index holds a task and throws error message if not true
                if (taskDoneIndex > itemIndex - 1 || taskDoneIndex < 0) {
                    System.out.println("Apologies sir but, it seems that task hasn't been created yet :(\n" + line);
                } else {
                    // Makes use of class variables and methods from class Task
                    Task taskChosen = tasks[taskDoneIndex];
                    // Checks if task has already been marked as done
                    if (taskChosen.isDone()) {
                        System.out.println("Sir I believe this task has already been completed");
                    } else {
                        taskChosen.changeStatusDone(true);
                        System.out.println(line + "\n" + "As you wish sir, this task will be marked as done!");
                    }
                    // Otherwise, marks task as done with X. E.g. 1.[X] read book if user inputs "done 1"
                    System.out.println("    " + taskChosen.getStatusIcon() + " " + taskChosen.getDescription() + "\n" + line);
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
                // Adds any other command as item to list
                tasks[itemIndex] = new Task(command);
                System.out.println("Will do sir, I've added: " + command);
                itemIndex++;
            }
        }
    }
}