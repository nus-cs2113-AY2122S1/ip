package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {

    public static final int MAX_RECORDS = 100;
    public static final String INDENT = "    │ ";
    private static int taskCount = 0;
    private static int taskCompleted = 0;
    private static final Task words[] = new Task[MAX_RECORDS];

    public static void main(String[] args) {
        greetUser();
        engageUser();
        byeUser();
    }

    /**
     * Prints the list of tasks collated by Tired.
     */
    public static void printList() {
        Ui.printTopLine();
        System.out.println(INDENT + "Here are your tasks, \"oRgAnIc iTeLlIgEnCe\":");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(INDENT + (i + 1) + "." + words[i]);
        }
        Ui.printBottomLine();
    }

    /**
     *  Prints error message to user. Prompts user to input correct command.
     */
    public static void printError() {
        Ui.printTopLine();
        System.out.println(INDENT + "You didn't input the type of task. Again. Or you're stupid. Whatever.");
        Ui.printBottomLine();
    }

    /**
     * Prints error message for when user leaves out important information in input.
     */
    public static void printMissingTextError() {
        Ui.printTopLine();
        System.out.println(INDENT + "And?? Retype and complete your sentence like a grown adult. Please.");
        Ui.printBottomLine();
    }

    /**
     * Adds inputs from user to list[] to keep track of user's tasks, deadlines, and events.
     *
     * @param taskName Name of task from user.
     * @param taskType Type of task from user.
     * @param taskDetails Time/date of event/deadline.
     */
    public static void addTask(String taskName, String taskType, String taskDetails) {
        switch (taskType) {
        case "todo":
            words[taskCount] = new Todo(taskName);
            break;
        case "deadline":
            words[taskCount] = new Deadline(taskName, taskDetails);
            break;
        case "event":
            words[taskCount] = new Event(taskName, taskDetails);
            break;
        default:
            return;
        }

        taskCount++;
        String plural = (taskCount - taskCompleted) == 1 ? "" : "s";

        Ui.printTopLine();
        System.out.println(INDENT + " Fine. Added to your list:");
        System.out.println(INDENT + "   " + words[taskCount - 1]);
        System.out.println(INDENT + " You have " + (taskCount - taskCompleted)
                + " pending task" + plural + ". tHaT's aWeSoMe!!!1!!1!!");
        Ui.printBottomLine();
    }

    /**
     * Marks tasks in list[] as done, if they exist or has not been completed.
     *
     * @param taskNumber Number n associated with the task, where n is the nth task in list.
     */
    public static void markTaskAsDone(int taskNumber) {
        boolean isExists = taskNumber >= 0 && taskNumber < taskCount;

        Ui.printTopLine();
        if (!isExists) {
            System.out.println("    │ Wha- Hey! Task does not exist!");
        } else if (words[taskNumber].getDoneStatus()) {
            System.out.println("    │ Dude... you've done the task already.");
        } else if (isExists){
            taskCompleted++;
            words[taskNumber].setAsDone();
            System.out.println("    │ About time. I've mark that task as done:");
            System.out.println("    │   [" + words[taskNumber].getStatusIcon() + "]" + words[taskNumber].getDescription());
        }
        Ui.printBottomLine();
    }

    /**
     * Checks whether there exist an input after user input the command word.
     *
     * @param input Expected input from user after the command word taskType is typed.
     * @return true if input is empty, false if input is not empty.
     */
    public static boolean isEmpty(String input) {
        return input.equals("");
    }


    /**
     * Prints greeting message to user when code is ran.
     */
    public static void greetUser() {
        Ui.printLogo();
        Ui.printTopLine();
        System.out.println(INDENT + "*Sigh* Hi... I'm Tired                                             │\n"
                + INDENT + "What do you want from me?                                          │");
        Ui.printBottomLine();
    }

    /**
     * Prints a snarky remark to user.
     */
    public static void mockUser() {
        Ui.printTopLine();
        System.out.println(INDENT + "You know what a todo list bot is?\n"
                + INDENT + "I'm a todo list bot. So stop chatting with me.");
        Ui.printBottomLine();
    }

    /**
     * Prints farewell message to user and exits code.
     */
    public static void byeUser() {
        Ui.printTopLine();
        System.out.println(INDENT + "\"Only in the agony of parting do we look into the depths of love.\" │\n"
                + INDENT + " —— George Eliot                                                   │\n"
                + INDENT + "                                                                   │\n"
                + INDENT + "Ha! As if I care! Goodbye!!                                        │");
        Ui.printBottomLine();
    }

    /**
     * Engages user base on what the user has typed and executes a corresponding command.
     */
    public static void engageUser() {
        Scanner text = new Scanner(System.in);
        String taskType;
        String taskName;
        String taskDetails = "";

        String userInput;
        String[] parts;
        boolean isExit = false;

        do {
            taskType = text.next().toLowerCase();

            switch (taskType) {
            case "bye":
                isExit = true;
                break;
            case "hello":
            case "hi":
            case "yo":
                mockUser();
                break;
            case "list":
                printList();
                break;
            case "done":
                userInput = text.next();
                if (isEmpty(userInput)) {
                    printMissingTextError();
                } else {
                    markTaskAsDone(Integer.parseInt(userInput) - 1);
                }
                break;
            case "todo":
                taskName = text.nextLine();
                if (isEmpty(taskName)) {
                    printMissingTextError();
                } else {
                    addTask(taskName, taskType, taskDetails);
                }
                break;
            case "deadline":
                userInput = text.nextLine();
                if (isEmpty(userInput)) {
                    printMissingTextError();
                } else {
                    parts = userInput.split(" /by ");
                    taskName = parts[0];
                    taskDetails = parts[1];

                    addTask(taskName, taskType, taskDetails);
                }
                break;
            case "event":
                userInput = text.nextLine();
                if (isEmpty(userInput)) {
                    printMissingTextError();
                } else {
                    parts = userInput.split(" /at ");
                    taskName = parts[0];
                    taskDetails = parts[1];

                    addTask(taskName, taskType, taskDetails);
                }
                break;
            default:
                printError();
                break;
            }
        } while (!isExit);
    }

}
