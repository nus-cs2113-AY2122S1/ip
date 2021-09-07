import java.util.Scanner;

public class Duke {

    private static final Scanner in = new Scanner(System.in);

    /** List of all Tasks */
    private static Task[] allTasks;

    private static final String logo = getLogo();
    private static final String line = getLine();
    private static int inputCount = 0;

    public static void main(String[] args) {
        initTaskList();
        welcomeMessage(logo, line);
        while(true) {
            String userInput = getUserInput();
            taskQuery(userInput);
        }
    }

    private static String getUserInput() {
        String input = in.nextLine();
        System.out.println(line);
        return input;
    }

    private static void taskQuery(String userInput) {
        if(userInput.equals("bye")) {
            System.out.println("Bye, see you!\n" + line);
            System.exit(0);
        } else if(userInput.equals("list")) {
            for(int i = 0; i < inputCount; i++){
                System.out.println((i + 1) + "." + allTasks[i]);
            }
        } else if(userInput.startsWith("done ")) {
            String[] words = userInput.split(" ");
            int taskCompleted = Integer.parseInt(words[1]) - 1;
            if (allTasks[taskCompleted].isDone()) {
                System.out.println("You have already marked this task as done! Time to move on :)");
            } else {
                allTasks[taskCompleted].markAsDone();
                System.out.println("Awesome! You've completed the following task:");
                System.out.println(" [X] " + allTasks[taskCompleted].getDescription());
            }

        } else if(userInput.startsWith("todo ")) {
            String taskName = userInput.substring(5);
            ToDo newToDo = new ToDo(taskName);
            allTasks[inputCount] = newToDo;
            inputCount += 1;
            taskMessage(inputCount, newToDo);
        } else if(userInput.startsWith("deadline ") && userInput.contains("/by")) {
            int taskEndIndex = userInput.indexOf("/by") - 1; //to account for spacing before "/by"
            String taskName = userInput.substring(9, taskEndIndex);
            int deadlineStartIndex = taskEndIndex + 5;
            String deadline = userInput.substring(deadlineStartIndex);
            Deadline newDeadline = new Deadline(taskName, deadline);
            allTasks[inputCount] = newDeadline;
            inputCount += 1;
            taskMessage(inputCount, newDeadline);
        } else if(userInput.startsWith("event ") && userInput.contains("/at")) {
            int taskEndIndex = userInput.indexOf("/at") - 1; //to account for spacing before "/by"
            String taskName = userInput.substring(6, taskEndIndex);
            int eventTimeStartIndex = taskEndIndex + 5;
            String event = userInput.substring(eventTimeStartIndex);
            Event newEvent = new Event(taskName, event);
            allTasks[inputCount] = newEvent;
            inputCount += 1;
            taskMessage(inputCount, newEvent);
        } else {
            System.out.println("Invalid command :(");
        }
        System.out.println(line);
    }

    private static void initTaskList() {
        allTasks = new Task[100];
    }

    private static void taskMessage(int inputCount, Task task) {
        System.out.println("Successfully added. Here's the added task: ");
        System.out.println(task);
        System.out.println("There " + ((inputCount == 1)? "is " : "are ") + inputCount + " task" + ((inputCount == 1)? "" : "s") + " in the list now.");
    }

    private static void welcomeMessage(String logo, String line) {
        System.out.println(line + logo
                + " Hello! I'm Duke\n"
                + " What's up? :p\n" + line);
    }

    private static String getLine() {
        return "____________________________________________________________\n";
    }

    private static String getLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return logo;
    }
}
