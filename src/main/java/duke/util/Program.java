package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.HalUi;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;

import static duke.util.UserData.readFromFile;
import static duke.util.UserData.writeToFile;

public class Program {
    private boolean canTerminateHal = false;    //when true, the program exits
    private static ArrayList<Task> listTasks = new ArrayList<>(999);
    private static int numItems;

    private static int DEADLINE_INDEX = 9;
    private static int EVENT_INDEX = 5;
    private static int TODO_INDEX = 4;
    private static int TASK_STRING_OFFSET = 3;

//    public static final String LINE_BREAK_SINGLE = "____________________________________________________________";
//    public static final String TASK_ADDED_DONE_TEXT = "Got it! I've added this task: ";
//    public static final String DONE_TASK_ERROR_MESSAGE = "No such task exist! Are you sure you keyed in the correct number?";
//    public static final String DONE_TASK_SUCCESS_MESSAGE = "Nice! I've marked this task as done:";
//    public static final String PRINT_EMPTY_TASKS_MESSAGE = "No items found... Add some items now!";
//    public static final String PRINT_ERROR_MESSAGE = "Your input does not follow my format!\n" +
//            "Read properly and type it again!";
//    public static final String ENTER_COMMAND_TEXT = "Enter command: ";
//    public static final String PRINT_EMPTY_DESCRIPTION_MESSAGE =
//            "Hmm... did you forget to write your task?";
//    public static final String PRINT_EMPTY_DATE_MESSAGE =
//            "Hmm... I think you forgot to write your timings!";
//    public static final String PRINT_DELETE_MESSAGE =
//            "Noted... I've removed the following task:";
//
//    public static final String INVALID_NUMBER_ERROR = "Your input wasn't an integer! Write a valid number";
//    public static final String INVALID_RANGE_ERROR = "The index you specified is outside the size of the list";

    public Program() throws IOException {
        this.numItems = 0;
        loadSavedTasks();
    }

    StorageDataParser parser = new StorageDataParser();
    static HalUi ui = new HalUi();
    public static int getNumItems() {
        return numItems;
    }

    //function takes in an input string from the user, parses it and runs the corresponding function
    public void parseAndExecuteTask(String string) throws HalException, IOException {
        if (Objects.equals(string, "list")) {
            listAllTasks();
        } else if (Objects.equals(string, "bye")) {
            this.executeBye();
        } else if (string.startsWith("done")) {
            this.executeDoneTask(string);
        } else if (string.startsWith("deadline")) {
            addDeadlineTask(string);
        } else if (string.startsWith("event")) {
            addEventTask(string);
        } else if (string.startsWith("todo")){
            addToDoTask(string);
        } else if (string.startsWith("delete")) {
            deleteTask(string);
        } else {
            throw new HalException("I'm sorry, but I don't know what that means :((");
        }
        UserData.writeToFile(parser.saveListAsString(listTasks));
    }

    //function to load tasks from memory
    public static void loadSavedTasks() throws IOException {
        listTasks = readFromFile();
        int count = 0;
        for (Object obj : listTasks) {
            if ( obj != null ) {
                count++;
            }
        }
        numItems = count;
    }

    //function to add a new deadline task
    public static void addDeadlineTask(String deadlineTask) throws HalException {
        ui.printSingleLineBreak();

        //check if string contains '/by' tag
        if (!deadlineTask.contains("/by")) {
            ui.printErrorMessage();
            throw new HalException("Wrong Deadline task format");
        }

        String description = deadlineTask.substring(DEADLINE_INDEX, deadlineTask.indexOf('/')).trim();
        String by = deadlineTask.substring(deadlineTask.indexOf("/by") + TASK_STRING_OFFSET).trim();

        if (description.equals("")) {
            ui.printEmptyDescriptionMessage();
            throw new HalException("Empty description");
        } else if (by.equals("")) {
            ui.printEmptyDateMessage();
            throw new HalException("Empty date");
        }

        Deadline newDeadlineTask = new Deadline(description, by);
        listTasks.add(newDeadlineTask);
        numItems++;

        ui.printTaskMessage(newDeadlineTask);
        printTotalTasks();
        ui.printEnterCommandMessage();
    }

    //function to add a new event task
    public static void addEventTask(String eventTask) throws HalException {
        ui.printSingleLineBreak();

        //check if string contains '/at' tag
        if (!eventTask.contains("/at")) {
            ui.printErrorMessage();
            throw new HalException("Wrong Event task format");
        }

        String description = eventTask.substring(EVENT_INDEX, eventTask.indexOf('/')).trim();
        String at = eventTask.substring(eventTask.indexOf("/at") + TASK_STRING_OFFSET).trim();

        if (description.equals("")) {
            ui.printEmptyDescriptionMessage();
            throw new HalException("Empty description");
        } else if (at.equals("")) {
            ui.printEmptyDateMessage();
            throw new HalException("Empty date");
        }

        Event newEventTask = new Event(description, at);
        listTasks.add(newEventTask);
        numItems++;

        ui.printTaskMessage(newEventTask);
        printTotalTasks();
        ui.printEnterCommandMessage();
    }

    //function to add a new todo task
    public static void addToDoTask(String toDoTask) throws HalException {
        ui.printSingleLineBreak();
        String description;

        description = toDoTask.substring(TODO_INDEX).trim();

        if (description.equals("")) {
            ui.printEmptyDescriptionMessage();
            throw new HalException("Empty description");
        }

        ToDo newTask = new ToDo(description);
        listTasks.add(newTask);
        numItems++;

        ui.printTaskMessage(newTask);
        printTotalTasks();
        ui.printEnterCommandMessage();
    }

    //function to list all tasks currently saved by the user
    public static void listAllTasks() {
        ui.printSingleLineBreak();
        System.out.println("Displaying all items saved:");
        if (numItems == 0) {
            ui.printEmptyTaskMessage();
        }
        for (int i = 0; i < listTasks.size(); i++) {
            System.out.println(i + 1 + ": " + listTasks.get(i).toString());
        }
        ui.printEnterCommandMessage();
    }

    //function to mark individual tasks as done
    public void executeDoneTask(String task) {
        try {
            int taskNum = Integer.parseInt(task.substring(task.indexOf(' ') + 1));
            if (taskNum > getNumItems() || taskNum <= 0) {
                ui.printDoneTaskErrorMessage();
            } else {
                listTasks.get(taskNum-1).markAsDone();
                ui.printDoneTaskSuccessMessage();
                System.out.println(listTasks.get(taskNum-1).toString());
            }

            ui.printEnterCommandMessage();

        } catch (NumberFormatException error) {
            ui.printInvalidNumberMessage();
            ui.printEnterCommandMessage();
        }
    }

    public void deleteTask(String index) {
        ui.printSingleLineBreak();
        try {
            int taskIndex = Integer.parseInt(index.substring(index.indexOf(" ") + 1)) - 1; //get the index of the task
            Task tempTask = listTasks.get(taskIndex);

            ui.printDeleteMessage(tempTask);

            listTasks.remove(taskIndex);
            numItems--;
            printTotalTasks();
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidRangeMessage();
        } catch (NumberFormatException e) {
            ui.printInvalidNumberMessage();
        } finally {
            ui.printEnterCommandMessage();
        }
    }

    //function to exit program
    public void executeBye() {
        this.setCanTerminateHal(true);
    }

    public static void printTotalTasks() {
        System.out.println("You now have " + numItems + " task(s) in your list!");
    }

    public Boolean getCanTerminateHal() {
        return canTerminateHal;
    }

    public void setCanTerminateHal(boolean canTerminateHal) {
        this.canTerminateHal = canTerminateHal;
    }
}
