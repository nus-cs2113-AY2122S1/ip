import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    final static String line = "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(line);
        printWelcomeMesage();
        getMenu();
    }

    public static void printExitMessage() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void printWelcomeMesage() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public static void printLogoMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
    }

    public static void printMessage(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    public static void printTasksList(Task[] tasksList) {
        if (tasksList == null) {
            System.out.println("There is currently 0 tasks in your list.");
            return;
        }
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasksList) {
            System.out.printf("%d. ", i);
            task.printStatus();
            i++;
        }
    }

    public static void setDone(Task[] tasksList, String userInputs) {
        if (tasksList == null) {
            System.out.println("Error: No tasks in list.");
            return;
        }
        try {
            int index = Integer.parseInt(userInputs.split(" ")[1]);
            index = index - 1;
            if (index >= 0 && index < tasksList.length) {
                tasksList[index].setDone(true);
                tasksList[index].printStatus();
            } else {
                System.out.println("Error: task not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: Incorrect format.");
            return;
        }


    }

    public static Task[] addTaskToList(Task[] tasksList, String userInputs) {
        if (tasksList == null) {
            Task task = new Task(userInputs);
            tasksList = new Task[1];
            tasksList[0] = task;
            return tasksList;
        }
        Task[] newItemsList = new Task[tasksList.length + 1];
        for (int i = 0; i < tasksList.length; i++) {
            newItemsList[i] = tasksList[i];
        }
        newItemsList[tasksList.length] = new Task(userInputs);
        return newItemsList;
    }

    public static void getMenu() {
        Scanner in = new Scanner(System.in);
        String userInputs = in.nextLine();
        Task[] tasksList = null;
        menuLoop:
        while (true) {
            switch (userInputs.split(" ")[0]) {
            case "":
                break;
            case "done":
                System.out.println(line);
                setDone(tasksList, userInputs);
                System.out.println(line);
                break;
            case "list":
                System.out.println(line);
                printTasksList(tasksList);
                System.out.println(line);
                break;
            case "bye":
                printExitMessage();
                break menuLoop;
            default:
                tasksList = addTaskToList(tasksList, userInputs);
                printMessage("added: " + userInputs);
                break;
            }
            userInputs = in.nextLine();
        }
    }
}
