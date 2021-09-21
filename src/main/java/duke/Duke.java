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
    //Need taskNumber if implementing ArrayList

    public static void main(String[] args) {
        Ui.greetUser();
        engageUser();
        Ui.byeUser();
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

    //Move to taskManager //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Adds inputs from user to list[] to keep track of user's tasks, deadlines, and events.
     *
     * @param taskName Name of task from user.
     * @param taskType Type of task from user.
     * @param taskDetails Time/date of event/deadline.
     */
    public static void addTask(String taskName, String taskType, String taskDetails) {

        try {
            switch (taskType) {
            case "todo":
                addTodo(taskName);
                break;
            case "deadline":
                addDeadline(taskName, taskDetails);
                break;
            case "event":
                addEvent(taskName, taskDetails);
                break;
            default:
                return;
            }
            addTaskConfirmation();
        } catch (DukeException e) {
            Ui.printMissingTextError();
        }
    }
    public static void addTodo(String taskName) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("todo name missing.");
        }
        words[taskCount] = new Todo(taskName);
    }
    public static void addDeadline(String taskName, String taskDetails) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("deadline name missing.");
        }
        words[taskCount] = new Deadline(taskName, taskDetails);
    }
    public static void addEvent(String taskName, String taskDetails) throws DukeException {
        if (isEmpty(taskName)) {
            throw new DukeException("event name missing.");
        }
        words[taskCount] = new Event(taskName, taskDetails);
    }

    /**
     * Prints confirmation to user of added task and updates taskCount number
     */
    public static void addTaskConfirmation() {
        taskCount++;
        int taskPending = taskCount - taskCompleted;
        String plural = (taskPending) == 1 ? "" : "s";

        Ui.printTopLine();
        System.out.println(INDENT + " Fine. Added to your list:");
        System.out.println(INDENT + "   " + words[taskCount - 1]);
        System.out.println(INDENT + " You have " + taskPending + " pending task" + plural + ". tHaT's aWeSoMe!!!!!1!!");
        Ui.printBottomLine();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Marks tasks in words[] as done, only if they exist or has not been completed.
     *
     * @param userInput String from user to be converted into a number that is associated with a task.
     */
    public static void markTaskAsDone(String userInput) {
        int taskNumber;
        boolean isExists = false;
        try {
            //abstraction here with method with "throws DukeException"
            taskNumber = Integer.parseInt(userInput) - 1;
            isExists = taskNumber >= 0 && taskNumber < taskCount;

            Ui.printTopLine();
            if (!isExists) {
                System.out.println(INDENT + "Wha- Hey! Task does not exist!");
            } else if (words[taskNumber].getDoneStatus()) {
                System.out.println(INDENT + "Dude... you've done the task already.");
            } else if (isExists) {
                taskCompleted++;
                words[taskNumber].setAsDone();
                System.out.println(INDENT + "About time. I've mark that task as done:");
                System.out.println(INDENT + "[" + words[taskNumber].getStatusIcon() + "]" + words[taskNumber].getDescription());
            }
            Ui.printBottomLine();
        } catch (NumberFormatException e){
            Ui.printNumberFormatError();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printMissingTextError();
        }
    }

//    public static boolean confirmUndoneTaskExistence(int taskNumber) throws DukeException {
//        boolean isExists = taskNumber >= 0 && taskNumber < taskCount;
//
//        if () {
//            throw new NumberFormatException("expected a number");
//        }
//        else if (!isExists) {
//            throw new ArrayIndexOutOfBoundsException("number out of bounds");
//        } else if (words[taskNumber].getDoneStatus()) {
//            System.out.println(INDENT + "Dude... you've done the task already.");
//        } else {
//            return true;
//        }
//    }

    public static int parsedInteger(String userInput) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput) - 1;
            return taskNumber;
        } catch (NumberFormatException e){
            Ui.printNumberFormatError();
        }
        return 0;
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
     * Engages user base on what the user has typed and executes a corresponding command.
     */
    public static void engageUser() {
        Scanner text = new Scanner(System.in);
        String taskType;
        String taskName;
        String taskDetails = "";

        String userInput;
        String[] parts = new String[0];
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
                Ui.mockUser();
                break;
            case "list":
                printList();
                break;
            case "done":
                userInput = text.nextLine();
                if (userInput.equals("")) {
                    Ui.printMissingTextError();
                } else {
                    parts = userInput.split(" ");
                    userInput = parts[1];
                    markTaskAsDone(userInput);
                }
                //markTaskAsDone(Integer.parseInt(userInput) - 1);
                break;
            case "todo":
                taskName = text.nextLine();
                addTask(taskName, taskType, taskDetails);
                break;
            case "deadline":
            case "event":
                userInput = text.nextLine();

                if (taskType.equals("deadline")) {
                    parts = userInput.split(" /by ");
                } else if (taskType.equals("event")) {
                    parts = userInput.split(" /at ");
                }

                taskName = parts[0];
                taskDetails = parts[1];

                addTask(taskName, taskType, taskDetails);
                break;
            default:
                userInput = text.nextLine();
                Ui.printMissingTaskTypeError();
                break;
            }
        } while (!isExit);
    }

}
