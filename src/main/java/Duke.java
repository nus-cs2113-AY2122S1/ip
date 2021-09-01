import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int taskListIndex;
        Task currentTask = new Task();
        LinkedList<Task> savedTasks = new LinkedList<Task>();
        TaskList taskList = new TaskList(savedTasks);
        String welcomeMessage = "______________________________\n"
                + "Hello! I'm Friday\n"
                + "What are you doing today?\n"
                + "______________________________\n";
        String goodbyeMessage = "______________________________\n"
                + "Bye! Hope to see you again soon!\n"
                + "______________________________\n";
        System.out.print(welcomeMessage);
        String userInput = sc.nextLine();
        while(!userInput.contains("bye")){
            switch(identifyUserInput(userInput)[0]) {
            case "todo":
                ToDo toDoTask = new ToDo(userInput.substring(userInput.indexOf(' ',0))
                        ,false);
                taskList.addTasks(toDoTask);
                toDoTask.initialiseToDo();
                System.out.println("Now you have " + taskList.countTaskInList()
                        + " tasks in the list");
                System.out.println("______________________________\n");
                break;

            case "deadline":
                Deadline deadLineTask = new Deadline(
                        userInput.substring(userInput.indexOf(' ',0), userInput.indexOf('/'))
                        ,false,identifyDeadlineCommand(userInput)[1]);
                taskList.addTasks(deadLineTask);
                deadLineTask.initialiseDeadline();
                System.out.println("Now you have " + taskList.countTaskInList()
                        + " tasks in the list");
                System.out.println("______________________________\n");
                break;

            case "event":
                Events eventTask = new Events(
                        userInput.substring(userInput.indexOf(' ',0), userInput.indexOf('/')),
                        false,identifyDeadlineCommand(userInput)[1]);
                taskList.addTasks(eventTask);
                eventTask.initialiseEvent();
                System.out.println("Now you have " + taskList.countTaskInList()
                        + " tasks in the list");
                System.out.println("______________________________\n");
                break;

            case "list":
                System.out.println("______________________________\n");
                taskList.listTasks();
                System.out.println("______________________________\n");
                break;

            case "done":
                taskListIndex = Integer.parseInt(identifyUserInput(userInput)[1]);
                currentTask = taskList.findTask(taskListIndex);
                currentTask.markTaskAsDone();
                break;

            default:
                System.out.println("Please key in a valid input!");
                break;
            }
            userInput = sc.nextLine();
        }
        System.out.print(goodbyeMessage);
    }

    /**
     * Takes the user input and identify the index which,
     * the user wants to mark as completed.
     *
     * @param userInput
     * @return taskIndex.
     */
    public static String[] identifyUserInput(String userInput){
        String[] parts = userInput.split(" ");
        return parts;
    }

    /**
     * Takes the user input and identify the dateline which,
     * the user wants the task to be.
     *
     * @param userInput
     * @return String array of string split at "/".
     */
    public static String[] identifyDeadlineCommand(String userInput){
        String[] parts = userInput.split("/");
        return parts;
    }
}
