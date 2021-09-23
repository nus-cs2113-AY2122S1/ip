import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printLogo();
        greetUser();

        String userLineInput = "initialize";
        Task[] taskItems = new Task[100]; //create array to store text entered by user
        int taskCounter = 0; //track number of tasks

        Scanner in = new Scanner(System.in); //Create scanner object to take in input

        handleUserInput(userLineInput, taskItems, taskCounter, in);
    }

    private static void handleUserInput(String userLineInput, Task[] taskItems, int taskCounter, Scanner in) {
        while (!userLineInput.equals("bye")) {
            userLineInput = in.nextLine(); //user input
            String[] userWords = userLineInput.split(" ");

            printDottedLine();
            switch (userWords[0]) {
            case "list":
                userInputIsList(taskItems, taskCounter);
                continue;

            case "todo":
                userInputIsTodo(userLineInput, taskItems, taskCounter);
                taskCounter++;
                printTaskCount(taskCounter);
                printDottedLine();
                continue;

            case "event":
                userInputIsEvent(userLineInput, taskItems, taskCounter);
                taskCounter++;
                printTaskCount(taskCounter);
                printDottedLine();
                continue;

            case "deadline":
                userInputIsDeadline(userLineInput, taskItems, taskCounter);
                taskCounter++;
                printTaskCount(taskCounter);
                printDottedLine();
                continue;


            case "done":
                userInputIsDone(taskItems, taskCounter, userWords[1]);
                continue;

            case "bye":
                userInputIsBye();
                break;

            default:
            }
        }
    }

    private static void userInputIsTodo(String userLineInput, Task[] taskItems, int taskCounter) {
        int todoInputLength = userLineInput.length();
        String todoTaskInput = userLineInput.substring(4, todoInputLength);
        taskItems[taskCounter] = new ToDo(todoTaskInput);
        System.out.println("Got it. I've added this task:");
        System.out.println(taskItems[taskCounter].description);
    }

    private static void userInputIsDeadline(String userLineInput, Task[] taskItems, int taskCounter) {
        int byPosition = userLineInput.indexOf("/by");
        int deadlineInputLength = userLineInput.length();
        String deadlineTaskInput = userLineInput.substring(8, byPosition - 1);
        String deadlineDateInput = userLineInput.substring((byPosition + 4), deadlineInputLength);
        taskItems[taskCounter] = new Deadline(deadlineTaskInput + " (by: " + deadlineDateInput + ")");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskItems[taskCounter].description);
    }

    private static void printTaskCount(int taskCounter) {
        System.out.println("You now have " + taskCounter + " task(s) in the list.");
    }

    private static void userInputIsEvent(String userLineInput, Task[] taskItems, int taskCounter) {
        int atPosition = userLineInput.indexOf("/at");
        int eventInputLength = userLineInput.length();
        String eventTaskInput = userLineInput.substring(5, atPosition - 1);
        String eventDateInput = userLineInput.substring((atPosition + 4), eventInputLength);
        taskItems[taskCounter] = new Event(eventTaskInput + " (at: " + eventDateInput + ")");
        System.out.println("Got it. I've added this task:");
        System.out.println(taskItems[taskCounter].description);
    }


    private static void userInputIsList(Task[] taskItems, int taskCounter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCounter; i++) {
            System.out.println((i + 1) + "." + taskItems[i].description);
        }
        printDottedLine();
    }

    private static void printDottedLine() {
        System.out.println("____________________________________________________________");
    }

    private static void greetUser() {
        printDottedLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void userInputIsDone(Task[] taskItems, int taskCounter, String userWord) {
        int doneInteger = Integer.parseInt(userWord);
        System.out.println("Nice! I've marked this task as done:");
        for (int i = 0; i < taskCounter; i++) {
            if (i == (doneInteger - 1)) {
                taskItems[i].setDone();
                System.out.println(doneInteger + "." + taskItems[i].description);
                break;
            }
        }
        printDottedLine();
    }

    private static void userInputIsBye() {
        printDottedLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDottedLine();
    }
}
