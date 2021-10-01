package duke.tasklist;

import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;
import duke.exception.NumberOutOfBoundsException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList{
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String INDENT = "    ";
    public static final int DESCRIPTION_INDEX = 0;
    public static final int DUE_TIME_INDEX = 1;
    public static final int LOCATION_INDEX = 1;




    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    public static String number;
    public static String inputCommand;

    public static ArrayList<Task> taskArrayList;

    public TaskList(ArrayList<Task> storageArrayList){
        taskArrayList = storageArrayList;
    }

    /**
     * Performs actions based on user commands
     * @param userInput input from the user
     * @throws NumberOutOfBoundsException number input by user not in task list
     * @throws StringIndexOutOfBoundsException required field is left empty by the user
     * @throws AtEmptyException "at" field is left empty
     * @throws ByEmptyException "by" field is left empty
     */
    public static void processUserInput(String userInput) throws NumberOutOfBoundsException,
            StringIndexOutOfBoundsException, AtEmptyException, ByEmptyException {

        String[] splitInput = userInput.split(" ");
        inputCommand = splitInput[DESCRIPTION_INDEX];
        switch (inputCommand) {
        case COMMAND_LIST:
            performListTask();
            break;
        case COMMAND_DONE:
            int inputNum = handleIntConversion(userInput, COMMAND_DONE);
            performMarkTaskDone(inputNum);
            Storage.writeToFile();
            break;
        case COMMAND_TODO:
            String todoTask = Parser.splitTodo(userInput);
            performAddTodo(todoTask);
            Storage.writeToFile();
            break;
        case COMMAND_DEADLINE:
            String[] deadlineInfo = Parser.splitDeadline(userInput);
            performAddDeadline(deadlineInfo[DESCRIPTION_INDEX], deadlineInfo[LOCATION_INDEX]);
            Storage.writeToFile();
            break;
        case COMMAND_EVENT:
            String[] eventInfo = Parser.splitEvent(userInput);
            performAddEvent(eventInfo[DESCRIPTION_INDEX], eventInfo[DUE_TIME_INDEX]);
            Storage.writeToFile();
            break;
        case COMMAND_DELETE:
            inputNum = handleIntConversion(userInput, COMMAND_DELETE);
            performDelete(inputNum);
            Storage.writeToFile();
            break;
        case COMMAND_FIND:
            String keyword = Parser.splitKeyword(userInput);
            findKeyword(keyword);
            break;
        case COMMAND_BYE:
            Ui.exitProgram();
            break;
        default:
            Ui.printInvalidInput();
        }
    }

    private static void performListTask() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");

        for (Task t : taskArrayList) {
            System.out.println(INDENT + t.taskNum + "." + t);
        }
    }

    private static int handleIntConversion(String userInput, String command) {
        number = userInput.replace(command, "");
        number = number.trim();
        return Integer.parseInt(number);
    }

    private static void performMarkTaskDone(int inputNum) throws NumberOutOfBoundsException {
        int doneTaskIndex = inputNum - 1;

        boolean isValidNum = (inputNum > DESCRIPTION_INDEX) && (inputNum <= Task.taskCount);
        if (isValidNum) {
            taskArrayList.get(doneTaskIndex).markAsDone();
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(INDENT + taskArrayList.get(doneTaskIndex).taskNum + "." + taskArrayList.get(doneTaskIndex));
        } else {
            throw new NumberOutOfBoundsException();
        }
    }

    private static void performDelete(int inputNum) throws NumberOutOfBoundsException {
        int deleteTaskIndex = inputNum - 1;

        boolean isValidNum = (inputNum > DESCRIPTION_INDEX) && (inputNum <= Task.taskCount);
        if (isValidNum) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Noted. I've removed this task:");
            System.out.println(INDENT + taskArrayList.get(deleteTaskIndex).taskNum + "." + taskArrayList.get(deleteTaskIndex));
            taskArrayList.remove(deleteTaskIndex);
            decrementTaskCount();
            updateTaskNum(deleteTaskIndex);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list");
        } else {
            throw new NumberOutOfBoundsException();
        }
    }

    private static void decrementTaskCount() {
        Task.taskCount = Task.taskCount - 1;
    }

    private static void updateTaskNum(int deleteTaskNum) {
        for (int i = deleteTaskNum; i < taskArrayList.size(); i++) {
            taskArrayList.get(i).taskNum = taskArrayList.get(i).taskNum - 1;
        }
    }

    private static void findKeyword(String keyword){
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the matching tasks in the list:");
        boolean isThereMatchingTask = false;
        for (Task t: taskArrayList){
            if (t.toString().contains(keyword)){
                System.out.println(INDENT + t.taskNum + "." + t);
                isThereMatchingTask = true;
            }
        }
        if (!isThereMatchingTask){
            System.out.println("Search Complete. There are no matching tasks in the list.");
        }
    }

    private static void performAddTodo(String todoTask) {
        System.out.println(HORIZONTAL_LINE);
        taskArrayList.add(new Todo(todoTask));
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + taskArrayList.get(Task.taskCount - 1));
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    private static void performAddDeadline(String description, String by) {
        System.out.println(HORIZONTAL_LINE);
        taskArrayList.add(new Deadline(description, by));
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + taskArrayList.get(Task.taskCount - 1));
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }

    private static void performAddEvent(String description, String at) {
        System.out.println(HORIZONTAL_LINE);
        taskArrayList.add(new Event(description, at));
        System.out.println("Got it. I've added this task:");
        System.out.println(INDENT + taskArrayList.get(Task.taskCount - 1));
        System.out.println("Now you have " + Task.taskCount + " tasks in the list");
    }
}
