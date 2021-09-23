import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    private final String greetings = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    private final String goodbyeMessage =
            "Bye. Hope to see you again soon!";

    public Ui () {
    }

    public void welcomeMessage() {
        System.out.println(this.greetings);
    }

    public void goodbyeMessage() {
        System.out.println(this.goodbyeMessage);
    }

    public void printNextLine() {
        System.out.println();
    }

    public void getListMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void getFindMessage() {
        System.out.println("Here are the matching tasks in your list:");
    }

    public void deleteTaskMessage(Task taskToDelete) {
        System.out.println("Noted. I've removed this task:\n" +
                "  " + taskToDelete.toString());
    }

    public void markTaskDoneMessage(Task markTask) {
        markTask.printMarkTaskAsDone();
    }

    public void printInvalidTaskNumberProvided() {
        System.out.println("Task number provided does not exist!");
    }

    public String readUserInput() {
        this.scanner = new Scanner(System.in);
        String userInput = this.scanner.nextLine();
        return userInput;
    }

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
