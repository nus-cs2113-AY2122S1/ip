import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    private final String greetings = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    private final String goodbyeMessage =
            "Bye. Hope to see you again soon!";

    public Ui () {
    }

    /**
     * We print the welcome message when the user starts duke for the first time
     * */
    public void welcomeMessage() {
        System.out.println(this.greetings);
    }

    /**
     * We print the good bye message when the user says bye
     * */
    public void goodbyeMessage() {
        System.out.println(this.goodbyeMessage);
    }

    /**
     * We print an empty line
     * */
    public void printNextLine() {
        System.out.println();
    }

    /**
     * We print the message when the user inputs list
     * */
    public void getListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * We print the message when the user inputs find
     * */
    public void getFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    /**
     * We print the delete message when a task is deleted
     * */
    public void deleteTaskMessage(Task taskToDelete) {
        System.out.println("Noted. I've removed this task:\n" +
                "  " + taskToDelete.toString());
    }

    /**
     * We print the message when a task is marked as done
     * */
    public void markTaskDoneMessage(Task markTask) {
        markTask.printMarkTaskAsDone();
    }

    /**
     * We print the error message when an invalid task number is given
     * */
    public void printInvalidTaskNumberProvided() {
        System.out.println("Task number provided does not exist!");
    }

    /**
     * @return The input given by the user
     * */
    public String readUserInput() {
        this.scanner = new Scanner(System.in);
        String userInput = this.scanner.nextLine();
        return userInput;
    }

    /**
     * We print the current number of tasks in the list of tasks
     * */
    public void printNumberOfTasks(TaskList taskList) {
        int numberOfTasks = taskList.getNumberOfTask();
        String taskNoun;
        if (numberOfTasks == 1 || numberOfTasks == 0) {
            taskNoun = "task";
        } else {
            taskNoun = "tasks";
        }
        System.out.println("Now you have " + numberOfTasks + " " +
                taskNoun +" in the list.");
    }
}
