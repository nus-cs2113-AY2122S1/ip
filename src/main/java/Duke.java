import java.util.Scanner;

public class Duke {
    public static final String TEXT_SPACER = "    ";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLineSpacer();
        System.out.println(TEXT_SPACER + "Hey, what's up!\n" + TEXT_SPACER + "What can I help you with today?");
        printLineSpacer();

        Task[] userLists = new Task[100];
        Scanner scanner = new Scanner(System.in);
        String userLineInput = "";
        int numOfTasks = 0;

        while(!userLineInput.equals("bye")) {
            userLineInput = scanner.nextLine();
            String[] splitInput = userLineInput.split(" ");
            String userCommand = splitInput[0];
            printLineSpacer();
            if (!userCommand.equals(("bye"))) {
                switch (userCommand) {
                case "list":
                    printList(userLists, numOfTasks);
                    break;
                case "done":
                    completeTask(userLists, splitInput);
                    break;
                case "event":
                case "deadline":
                case "todo":
                    storeTasks(userLists, userLineInput, numOfTasks, userCommand);
                    numOfTasks++;
                    break;
                default:
                    System.out.println(TEXT_SPACER + "Invalid Input!");
                    printLineSpacer();
                    break;
                }
            }
        }

        System.out.println(TEXT_SPACER + "Aight. See you soon mate.");
        printLineSpacer();
    }

    private static void printList(Task[] userLists, int numOfTasks) {
        if (numOfTasks == 0) {
            System.out.println(TEXT_SPACER + "List is empty!");
        } else {
            System.out.println(TEXT_SPACER + "Here's your list of tasks:");
            for (int i = 0; i < numOfTasks; i++) {

                System.out.println("    " + (i + 1)
                        + "." + userLists[i].toString());
            }
        }
        printLineSpacer();
    }

    private static void completeTask(Task[] userLists, String[] splitInput) {
        int chosenEntry;
        chosenEntry = Integer.parseInt(splitInput[1]) - 1;
        userLists[chosenEntry].markAsDone();
        System.out.println(TEXT_SPACER + "Good job on completing a task!");
        System.out.println(TEXT_SPACER + userLists[chosenEntry].toString());
        printLineSpacer();
    }

    private static void storeTasks(Task[] userLists, String userLineInput, int numOfTasks, String userCommand) {
        if (userCommand.equals("event")){
            storeEvent(userLists, userLineInput, numOfTasks);
        } else if(userCommand.equals("deadline")){
            storeDeadline(userLists, userLineInput, numOfTasks);
        } else{
            storeToDo(userLists, userLineInput, numOfTasks);
        }
        System.out.println(TEXT_SPACER + "Now you have " + (numOfTasks + 1) + " tasks in your list");
        printLineSpacer();
    }

    private static void storeToDo(Task[] userLists, String userLineInput, int numOfTasks) {
        userLists[numOfTasks] = new ToDo(userLineInput.substring(5));
        System.out.println(TEXT_SPACER + "Aight, I've added the following task to your list:");
        System.out.println(TEXT_SPACER + userLists[numOfTasks].toString());
    }

    private static void storeDeadline(Task[] userLists, String userLineInput, int numOfTasks) {
        int infoIndex = userLineInput.indexOf("/");
        String taskDescription = userLineInput.substring(9, infoIndex);
        String dueDate = userLineInput.substring(infoIndex + 3);
        userLists[numOfTasks] = new Deadline(taskDescription, dueDate);
        System.out.println(TEXT_SPACER + userLists[numOfTasks].toString());
    }

    private static void storeEvent(Task[] userLists, String userLineInput, int numOfTasks) {
        int infoIndex = userLineInput.indexOf("/");
        String taskDescription = userLineInput.substring(6, infoIndex);
        String eventTime = userLineInput.substring(infoIndex + 3);
        userLists[numOfTasks] = new Event(taskDescription, eventTime);
        System.out.println(TEXT_SPACER + userLists[numOfTasks].toString());
    }


    private static void printLineSpacer() {
        System.out.println(TEXT_SPACER + "**************************************************");
    }
}
