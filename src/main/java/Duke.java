import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printList(Task[] taskList) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.length; i++) {
            System.out.println(" " + (i + 1) + ". [" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
        }
        System.out.println("____________________________________________________________");
    }

    public static void printDukeGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greeting the User
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void addTask(int index, String inWord, Task[] taskList) {
        taskList[index] = new Task(inWord);
        System.out.println("____________________________________________________________");
        System.out.println(" added: " + inWord);
        System.out.println("____________________________________________________________");
        System.out.println();
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void returnException() {
        System.out.println("____________________________________________________________");
        System.out.println(" Sorry, your input is invalid! Please enter a valid input :)");
        System.out.println("____________________________________________________________");
    }

    public static void printTaskDone(String[] commands, int index, Task[] taskList) {
        int taskDoneIndex = Integer.parseInt(commands[1]);
        if (taskDoneIndex <= 0 || taskDoneIndex > index) {
            System.out.println("____________________________________________________________");
            System.out.println("Item out of Index! Please input a valid task number :)");
            System.out.println("____________________________________________________________");
        } else {
            taskList[taskDoneIndex - 1].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   [" + taskList[taskDoneIndex - 1].getStatusIcon() + "] " + taskList[taskDoneIndex - 1].getDescription());
            System.out.println("____________________________________________________________");
        }
    }

    public static void printDukeExit() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        printDukeGreet();

        String inWord;
        Scanner scan = new Scanner(System.in);
        System.out.println();
        inWord = scan.nextLine();

        //List of Special User Commands
        String exitString = "bye";
        String taskCommand = "list";
        String doneCommand = "done";

        Task[] taskList = new Task[100];
        int index = 0;

        while (!inWord.toLowerCase().equals(exitString)) {
            if (inWord.toLowerCase().equals(taskCommand)) {
                printList(Arrays.copyOf(taskList, index));
                //Adding a Task
            } else if (inWord.toLowerCase().startsWith(doneCommand)) {
                String[] commands = inWord.split(" ");

                //Check input validity (if user command starts with done and followed by an integer) and exceptions
                if (commands.length != 2 || !isNumeric(commands[1])) {
                    returnException();
                } else {
                    printTaskDone(commands, index, taskList);
                }
                //Mark Task as Done
            } else {
                addTask(index, inWord, taskList);
                index += 1;
                //Add additional task
            }
            inWord = scan.nextLine();
        }

        //Exits when user types "bye"
        printDukeExit();
    }
}