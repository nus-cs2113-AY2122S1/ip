package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.TaskList;

/**
 * Receives a String user input and parses it, performing any valid commands
 */
public class Parser {

    public static final int DESCRIPTION = 0;
    public static final int TASK_NUMBER = 1;
    public static final int TIMING = 1;
    public static final int TODO_HEADER = 5;
    public static final int FIND_HEADER = 5;
    public static final int EVENT_HEADER = 6;
    public static final int DEADLINE_HEADER = 9;
    public static final String LINEBAR = "____________________________________________________________\n";
    public static final String EVENT_IDENTIFIER = "/at ";
    public static final String DEADLINE_IDENTIFIER = "/by ";

    String userIn;
    String identifier;
    TaskList tasks;
    Ui ui;
    Storage storage;

    /**
     * Assigns TaskList, Ui and Storage to be used
     * @param taskList TaskList containing list of Tasks
     * @param userInterface Ui to print out relevant messages
     * @param storageSpace Storage to write/read data
     */
    public Parser(TaskList taskList, Ui userInterface, Storage storageSpace) {
        this.tasks = taskList;
        this.ui = userInterface;
        this.storage = storageSpace;
    }

    /**
     * Parses user input into commands. The list of supported commands are:
     * "done ~index~" : Marks the Task at index as done
     * "bot?" : Ask DAHNAM whether he's a bot
     * "list" : Displays all current tasks in TaskList
     * "todo ~desc~" : Creates a ToDo with desc
     * "event ~desc~ /at ~time~" : Creates an Event with desc and time. The /at is used to separate desc and time
     * "deadline ~desc~ /by ~time~" : Creates a Deadline with desc and time. The /by is used to separate desc and time
     * "delete ~index~" : Deletes the Task stored at the given index in TaskList
     * "find ~desc~" : Enumerates through all current Tasks in TaskList and print all tasks with ~desc~
     *
     * @param userInput String user input
     */
    public void parseInput(String userInput) {
        this.userIn = userInput;

        this.identifier = userIn.split(" ")[0];

        switch (identifier) {
        case "done":
            completeTask();
            return;

        case "bot?":
            denyBotNature();
            return;

        case "list":
            listAllTasks();
            return;

        case "todo":
            addToDo();
            return;

        case "event":
            addEvent();
            return;

        case "deadline":
            addDeadline();
            return;

        case "delete":
            deleteTask();
            return;

        case "find":
            findTask();
            return;

        default:
            ui.showListOfCommands();
        }
    }

    /**
     * Modifies a task and sets its boolean isDone to true. Prints out an acknowledgement after.
     *
     * @throws NumberFormatException     Thrown when the input is not a valid positive integer e.g. an alphabet
     * @throws NullPointerException      Thrown when the value inserted exceeds the number of tasks
     * @throws IndexOutOfBoundsException Thrown when input is a negative value, or does not include a value
     */
    public void completeTask()
            throws NumberFormatException, NullPointerException, IndexOutOfBoundsException {
        System.out.println(LINEBAR);
        try {
            int taskNumber = Integer.parseInt(userIn.split(" ")[TASK_NUMBER]);

            int taskIndex = taskNumber - 1;

            tasks.get(taskIndex).markAsDone();
            storage.updateFile(tasks.TaskList);
            System.out.println("Bueno! The following task is marked as done: \n" + tasks.get(taskIndex));
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        } catch (NullPointerException e) {
            ui.printNullPtrException();
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOOBException();
        }
        System.out.println(LINEBAR);
    }

    /**
     * Prints a message denying any and all allegations of DAHNAM being a bot when prompted with 'bot?'
     */
    public void denyBotNature() {
        String denyBotNature = "No, I am definitely not a bot. Why do you ask?\n";

        System.out.println(LINEBAR);
        System.out.println(denyBotNature);
        System.out.println(LINEBAR);
    }

    /**
     * Enumerates through an array of tasks and prints out all tasks input by user
     */
    public void listAllTasks() {
        System.out.println(LINEBAR);
        if (tasks.taskIndex == 0) {
            ui.printNoItemInList();
            System.out.println(LINEBAR);
            return;
        }

        int taskNumber = 1;
        for (Task t : tasks.TaskList) {
            System.out.println(taskNumber + ". " + t);
            taskNumber++;
        }
        System.out.println(LINEBAR);
    }

    /**
     * Adds a toDo to the task list
     *
     * @throws StringIndexOutOfBoundsException Thrown when user does not include description in input
     */
    public void addToDo() throws StringIndexOutOfBoundsException {
        System.out.println(LINEBAR);
        try {
            String taskDescription = userIn.substring(TODO_HEADER);
            ToDo todo = new ToDo(taskDescription);
            tasks.add(todo);
            storage.writeToFile(todo);
            ui.echoUserInput(todo, tasks.taskIndex);
        } catch (StringIndexOutOfBoundsException e) {
            ui.printStringIndexOOB();
        }
        System.out.println(LINEBAR);
    }

    /**
     * Adds an event to the task list
     *
     * @throws StringIndexOutOfBoundsException Thrown when userInput does not have a description after 'event'
     * @throws ArrayIndexOutOfBoundsException  Thrown when userInput does not follow [Description] /at [Time]
     */
    public void addEvent()
            throws StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException {
        System.out.println(LINEBAR);
        try {
            String taskDescription = userIn.split(EVENT_IDENTIFIER)[DESCRIPTION].substring(EVENT_HEADER);
            String timing = userIn.split(EVENT_IDENTIFIER)[TIMING];
            Event event = new Event(taskDescription, timing);
            tasks.add(event);
            storage.writeToFile(event);
            ui.echoUserInput(event, tasks.taskIndex);
        } catch (StringIndexOutOfBoundsException e) {
            ui.printStringIndexOOB();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printArrayIndexOOB();
        }
        System.out.println(LINEBAR);
    }

    /**
     * Adds a deadline to the task list
     *
     * @throws StringIndexOutOfBoundsException Thrown when user does not include any description after 'deadline'
     * @throws ArrayIndexOutOfBoundsException  Thrown when user input does not follow [description] /by [time]
     */
    public void addDeadline()
            throws StringIndexOutOfBoundsException, ArrayIndexOutOfBoundsException {
        System.out.println(LINEBAR);

        try {
            String taskDescription = userIn.split(DEADLINE_IDENTIFIER)[DESCRIPTION].substring(DEADLINE_HEADER);
            String timing = userIn.split(DEADLINE_IDENTIFIER)[TIMING];
            Deadline deadline = new Deadline(taskDescription, timing);
            tasks.add(deadline);
            storage.writeToFile(deadline);
            ui.echoUserInput(deadline, tasks.taskIndex);
        } catch (StringIndexOutOfBoundsException e) {
            ui.printStringIndexOOB();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printArrayIndexOOB();
        }
        System.out.println(LINEBAR);
    }

    /**
     * Deletes the task stored at index provided in user input
     *
     * @throws NumberFormatException     Thrown when the input is not a valid positive integer e.g. an alphabet
     * @throws NullPointerException      Thrown when the value inserted exceeds the number of tasks
     * @throws IndexOutOfBoundsException Thrown when input is a negative value, or does not include a value
     */
    public void deleteTask() throws NumberFormatException, NullPointerException, IndexOutOfBoundsException {
        System.out.println(LINEBAR);

        try {
            int taskNumber = Integer.parseInt(userIn.split(" ")[TASK_NUMBER]);

            int taskIndex = taskNumber - 1;

            Task toBeDeleted = tasks.get(taskIndex);
            tasks.remove(taskIndex);
            storage.updateFile(tasks.TaskList);
            System.out.println(
                    "The following task has been deleted!\n" + toBeDeleted + "\n");
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        } catch (NullPointerException e) {
            ui.printNullPtrException();
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOOBException();
        }

        System.out.println(LINEBAR);

    }

    /**
     * Enumerates through all tasks in TaskList, and prints out all tasks which contains the descriptor provided
     */
    public void findTask() {
        System.out.println(LINEBAR);
        System.out.println("Below are a list of tasks matching your description\n");

        String targetDesc = userIn.substring(FIND_HEADER);

        int matching = 0;

        for (Task t: tasks.TaskList) {
            String currDesc = t.taskDescription;

            if (currDesc.contains(targetDesc)) {
                matching++;
                System.out.println(matching + ". " + t);
            }
        }

        if (matching == 0) {
            System.out.println("No matches found\n");
        }
        System.out.println(LINEBAR);
    }
}
