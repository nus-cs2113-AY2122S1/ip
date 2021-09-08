import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IncorrectCommandInput {
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
        String commandInput = identifyUserInput(userInput)[0];
        String checkedCommandInput = "";
        try {
            checkedCommandInput = commandInputError(commandInput);
        }catch (IncorrectCommandInput e){
            System.out.println("Invalid Command!");
        }
        while(!userInput.contains("bye")){
            switch(checkedCommandInput) {
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
                System.out.println("Invalid! Please try again.");
            }
            try {
                userInput = sc.nextLine();
                commandInput = identifyUserInput(userInput)[0];
                checkedCommandInput = commandInputError(commandInput);
            }catch (IncorrectCommandInput e){
                System.out.println("Invalid Command! Please try again");
            }
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

    public static String commandInputError(String userInput) throws IncorrectCommandInput {
        switch(userInput) {
        case "todo":
            return userInput;
            break;
        case "event":
            return userInput;
            break;
        case "list":
            return userInput;
            break;
        case "bye":
            return userInput;
            break;
        default:
            throw new IncorrectCommandInput();
            break;
        }
    }
}


