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

import static duke.storage.UserData.readFromFile;

public class Program {
    private boolean canTerminateHal = false;    //when true, the program exits
    private static ArrayList<Task> listTasks = new ArrayList<>(999);
    private static int numItems;

    ToDo dummyTodo = new ToDo(null);
    Event dummyEvent = new Event(null, "");
    Deadline dummyDeadline = new Deadline(null, "");

    public Program() throws IOException {
        this.numItems = 0;
        loadSavedTasks();
    }

    StorageDataParser storageParser = new StorageDataParser();
    static HalUi ui = new HalUi();
    static Parser messageParser = new Parser();
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
            addTasks(string,dummyDeadline);
        } else if (string.startsWith("event")) {
            addTasks(string,dummyEvent);
        } else if (string.startsWith("todo")){
            addTasks(string,dummyTodo);
        } else if (string.startsWith("delete")) {
            deleteTask(string);
        } else {
            throw new HalException("I'm sorry, but I don't know what that means :((");
        }
        UserData.writeToFile(storageParser.saveListAsString(listTasks));
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

    //function to exit program
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
