package duke;

import duke.task.*;
import duke.task.TaskList;

public class Parser {

    public static final int DESCRIPTION = 0;
    public static final int TASK_NUMBER = 1;
    public static final int TIMING = 1;
    public static final int TODO_HEADER = 5;
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

    public Parser(TaskList taskList, Ui userInterface, Storage storageSpace) {
        this.tasks = taskList;
        this.ui = userInterface;
        this.storage = storageSpace;
    }

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

        case "index":
            tasks.getTaskIndex();
            return;

        default:
            ui.showListOfCommands();
        }
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
     * Prints a message denying any and all allegations of DAHNAM being a bot when prompted with 'bot?'
     */
    public void denyBotNature() {
        String denyBotNature = "No, I am definitely not a bot. Why do you ask?\n";

        System.out.println(LINEBAR);
        System.out.println(denyBotNature);
        System.out.println(LINEBAR);
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

            //Navigate to the given index and change the sign
            int taskIndex = taskNumber - 1;

            tasks.get(taskIndex).markAsDone();
            storage.updateFile(tasks.TaskList);
            System.out.println(
                    "Bueno! The following task is marked as done: \n[" + tasks.get(taskIndex).getStatusIcon() + "] "
                            + tasks.get(taskIndex).taskDescription);
        } catch (NumberFormatException e) {
            ui.printNumberFormatException();
        } catch (NullPointerException e) {
            ui.printNullPtrException();
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOOBException();
        }
        System.out.println(LINEBAR);
    }

    public void deleteTask() {
        System.out.println(LINEBAR);

        try {
            int taskNumber = Integer.parseInt(userIn.split(" ")[TASK_NUMBER]);

            //Navigate to the given index and change the sign
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
}
