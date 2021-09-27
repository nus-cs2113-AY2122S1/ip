package duke.util;

import duke.parser.Parser;
import duke.storage.StorageDataParser;
import duke.storage.UserData;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.HalUi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static duke.storage.UserData.readFromFile;

/**
 * Program acts as a central core of Hal's task functions.
 */
public class Program {
    private boolean canTerminateHal = false;    //when true, the program exits
    private static ArrayList<Task> listTasks = new ArrayList<>(999);
    private static int numItems;

    ToDo dummyTodo = new ToDo(null);
    Event dummyEvent = new Event(null, "");
    Deadline dummyDeadline = new Deadline(null, "");

    /**
     * Constructor to create a Program instance.
     * Tasks saved in local storage is loaded into memory once the program is launched.
     */
    public Program() {
        this.numItems = 0;
        loadSavedTasks();
    }

    StorageDataParser storageParser = new StorageDataParser();
    static HalUi ui = new HalUi();
    static Parser messageParser = new Parser();
    public static int getNumItems() {
        return numItems;
    }

    /**
     * Takes in a string input. The function then parses it and runs the corresponding function.
     * If the input string doesn't match any existing function, a HalException is thrown.
     * Tasks are saved to local storage each time the function is run.
     *
     * @param string String input containing a function call.
     * @throws HalException thrown when no corresponding function exists.
     * @throws IOException thrown when tasks failed to save to local storage.
     */
    public void parseAndExecuteTask(String string) throws HalException, IOException {
        if (Objects.equals(string, "list")) {
            listAllTasks();
        } else if (Objects.equals(string, "bye")) {
            this.executeBye();
        } else if (string.startsWith("done")) {
            this.executeDoneTask(string);
        } else if (string.startsWith("deadline")) {
            addTasks(string,dummyDeadline);
        } else if (string.startsWith("event")) {
            addTasks(string,dummyEvent);
        } else if (string.startsWith("todo")){
            addTasks(string,dummyTodo);
        } else if (string.startsWith("delete")) {
            deleteTask(string);
        } else if (string.startsWith("find")) {
            findTask(string);
        } else {
            throw new HalException("I'm sorry, but I don't know what that means :((");
        }
        UserData.writeToFile(storageParser.saveListAsString(listTasks));
    }

    /**
     * Load tasks from local storage.
     * Updates the number of tasks in storage for list function.
     */
    public static void loadSavedTasks() {
        listTasks = readFromFile();
        int count = 0;
        for (Object obj : listTasks) {
            if ( obj != null ) {
                count++;
            }
        }
        numItems = count;
    }

    /**
     * Add tasks based on the task description and the type of task specified.
     * Description and time is parsed from the message using parseTextInput function.
     * If a task is not specified according to the format, a HalException is thrown.
     *
     * @param taskMessage Task description entered by the user.
     * @param type The type of task object -> to do, event or deadline
     * @throws HalException thrown when taskMessage cannot be parsed properly.
     */
    public static void addTasks(String taskMessage, Task type) throws HalException {
        ui.printSingleLineBreak();
        String description;
        String timing;

        List<String> returnVal = messageParser.parseTextInput(type, taskMessage);
        description = returnVal.get(0);
        timing = returnVal.get(1);

        if (type instanceof ToDo) {
            ToDo newTask = new ToDo(description);
            listTasks.add(newTask);
            ui.printTaskMessage(newTask);
        } else if (type instanceof Deadline) {
            Deadline newDeadlineTask = new Deadline(description, timing);
            listTasks.add(newDeadlineTask);
            ui.printTaskMessage(newDeadlineTask);
        } else if (type instanceof Event) {
            Event newEventTask = new Event(description, timing);
            listTasks.add(newEventTask);
            ui.printTaskMessage(newEventTask);
        }
        numItems++;
        ui.printNumItemsMessage(numItems);
        ui.printEnterCommandMessage();
    }

    /**
     * Prints all the tasks currently saved by the user.
     * If no tasks are present, an empty task message will be printed.
     */
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

    /**
     * Marks task as done based on the specified task index.
     * Takes in a string in the format "done x", where x is the order of the task in the list.
     *
     * @param task A string containing the order of the task in the list.
     */
    public void executeDoneTask(String task) {
        int taskNum = messageParser.parseInt(task);
        if (taskNum > getNumItems() || taskNum <= 0) {
            ui.printDoneTaskErrorMessage();
        } else {
            listTasks.get(taskNum-1).markAsDone();
            ui.printDoneTaskSuccessMessage();
            System.out.println(listTasks.get(taskNum-1).toString());
        }

        ui.printEnterCommandMessage();
    }

    public void findTask(String str) throws HalException {
        String searchStr = messageParser.parseTextInput(str);

        ArrayList<Task> result = (ArrayList<Task>) listTasks.stream()
                .filter(task -> task.getDescription().contains(searchStr))
                .collect(Collectors.toList());
        ui.printFindTasksMessage(result);
        ui.printEnterCommandMessage();
    }

  /**
     * Deletes a task from the list based on the specified task index.
     * Takes in a string in the format "delete x", where x is the order of the task in the list.
     * If the specified index does not exist, or an invalid format is entered, exceptions are thrown.
     * @param index A string containing the order of the task in the list.
     */
    public void deleteTask(String index) {
        ui.printSingleLineBreak();
        try {
            int taskIndex = messageParser.parseInt(index) - 1; //minus 1 due to 0 index base
            Task tempTask = listTasks.get(taskIndex);

            ui.printDeleteMessage(tempTask);

            listTasks.remove(taskIndex);
            numItems--;
            ui.printNumItemsMessage(numItems);
        } catch (NumberFormatException e) {
            ui.printInvalidNumberMessage();
        } catch (IndexOutOfBoundsException e) {
            ui.printInvalidRangeMessage();
        } finally {
            ui.printEnterCommandMessage();
        }
    }

    /**
     * Exits the program when called.
     */
    public void executeBye() {
        this.setCanTerminateHal(true);
    }

    public Boolean getCanTerminateHal() {
        return canTerminateHal;
    }

    public void setCanTerminateHal(boolean canTerminateHal) {
        this.canTerminateHal = canTerminateHal;
    }
}
