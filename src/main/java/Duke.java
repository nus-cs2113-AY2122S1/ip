import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static Ui ui;
    /**
     * Error Messages
     */
    private static final String INVALID_TASK_MESSAGE = ui.CONSOLE_LINE_PREFIX + ui.LINE_BREAK
            + ui.SPACE_PREFIX + "That is invalid... Please use the syntax - "
            + "[taskType]" + ui.SPACE_PREFIX + "[taskName] ([/by dateTime] or [/at dateTime] depending on taskType)"
            + ui.LINE_BREAK + ui.CONSOLE_LINE_PREFIX;
    private static final String UNKNOWN_COMMAND_MESSAGE = ui.CONSOLE_LINE_PREFIX + ui.LINE_BREAK
            + ui.SPACE_PREFIX + "Hey, I don't quite understand this command. Please install a new CPU for me ;D"
            + ui.LINE_BREAK + ui.SPACE_PREFIX + "Just kidding, it's too expensive, just try again..." + ui.LINE_BREAK
            + ui.CONSOLE_LINE_PREFIX;
    private static final String MISSING_INDEX_MESSAGE = ui.CONSOLE_LINE_PREFIX + ui.LINE_BREAK
            + ui.SPACE_PREFIX + "Excuse me Sir/Madam, which task number? Where is it? Under the Sea?" + ui.LINE_BREAK
            + ui.CONSOLE_LINE_PREFIX;
    private static final String NO_TASK_MESSAGE = ui.CONSOLE_LINE_PREFIX + ui.LINE_BREAK
            + ui.SPACE_PREFIX + "Woah woah, you can't just mark something when your list of tasks is empty"
            + ui.LINE_BREAK + ui.CONSOLE_LINE_PREFIX;
    private static final String TODO_EMPTY_MESSAGE = ui.CONSOLE_LINE_PREFIX + ui.LINE_BREAK
            + ui.SPACE_PREFIX + "Excuse you? The description for todo can NEVER be empty!" + ui.LINE_BREAK
            + ui.CONSOLE_LINE_PREFIX;

    // Command Prefixes for checking type of command
    private static final String COMMAND_BYE = "Bye";
    private static final String COMMAND_DONE = "Done";
    private static final String COMMAND_LIST = "List";

    // Prefixes meant for processing data parameters for Task
    private static final String BY_WHEN_PREFIX = "/by";
    private static final String AT_WHEN_PREFIX = "/at";
    private static final String TASK_TODO_PREFIX = "Todo";
    private static final String TASK_DEADLINE_PREFIX = "Deadline";
    private static final String TASK_EVENT_PREFIX = "Event";
    private static final String COMMAND_DELETE = "Delete";

    /**
     * Initializing Scanner variable to allow for reading User Inputs.
     */
    private static final Scanner SC = new Scanner(System.in);

    /**
     * These variables are responsible for the management of Tasks
     */
    private static ArrayList<Task> tasks;

    /**
     * Initializes the list of Tasks and Task Counter
     */
    private static void initTasks() {
        ui = new Ui();
        tasks = new ArrayList<Task>();
        instantiateTasksFromFile();
    }

    /**
     * Returns the user input from I/O
     *
     * @return User Input
     */
    private static String getUserInput() {
        String userInput;
        System.out.print(ui.SPACE_PREFIX + "What's your plans/command for today (No... I am not hitting on you) : ");
        userInput = SC.nextLine();
        return userInput;
    }

    /**
     * Creates a TodoObject from task name given by the user
     * and returns it to be added to Tasks
     *
     * @param taskName name of the Todo_Task to be created
     * @return TodoObject
     */
    private static Todo createNewToDo(String taskName) throws DukeException {
        if (taskName.equals("")) {
            throw new DukeException(TODO_EMPTY_MESSAGE);
        }
        return new Todo(taskName);
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date to be completed ("byWhen").
     * Lastly, creates a Deadline Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return DeadlineObject
     */
    private static Deadline createNewDeadline(String unprocessedTaskName) throws DukeException {
        if (!unprocessedTaskName.contains(BY_WHEN_PREFIX)) {
            throw new DukeException(INVALID_TASK_MESSAGE);
        }
        try {
            String byWhen = unprocessedTaskName.split(BY_WHEN_PREFIX)[1].trim();
            String actualTaskName = unprocessedTaskName.replace(BY_WHEN_PREFIX, "").replace(byWhen, "");
            actualTaskName = actualTaskName.trim();
            return new Deadline(actualTaskName, byWhen);
        } catch (ArrayIndexOutOfBoundsException arrError) {
            throw new DukeException("TOBEREPLACED WITH DEADLINE_EMPTY_MESSAGE");
        }
    }

    /**
     * Processes the unprocessed task name given by user, to get the actual task name
     * and the date of the event ("atWhen").
     * Lastly, creates an Event Object before returning it to be added to Tasks
     *
     * @param unprocessedTaskName task name given by user before removing non-taskName relevant info
     * @return EventObject
     */
    private static Event createNewEvent(String unprocessedTaskName) throws DukeException {
        if (!unprocessedTaskName.contains(AT_WHEN_PREFIX)) {
            throw new DukeException(INVALID_TASK_MESSAGE);
        }
        try {
            String atWhen = unprocessedTaskName.split(AT_WHEN_PREFIX)[1].trim();
            String actualTaskName = unprocessedTaskName.replace(AT_WHEN_PREFIX, "").replace(atWhen, "");
            actualTaskName = actualTaskName.trim();
            return new Event(actualTaskName, atWhen);
        } catch (ArrayIndexOutOfBoundsException arrError) {
            throw new DukeException("TOBEREPLACED WITH EVENT_EMPTY_MESSAGE");
        }
    }

    private static Task createSavedTask(String fileLine) {
        String[] tokens = fileLine.split("\\|\\|");
        String taskType = tokens[0];
        String taskName = tokens[2];
        boolean isDone = Boolean.valueOf(tokens[1]);
        Task savedTask;
        switch (taskType) {
        case "D":
            savedTask = new Deadline(taskName, isDone, tokens[3]);
            break;
        case "E":
            savedTask = new Event(taskName, isDone, tokens[3]);
            break;
        default:
            savedTask = new Todo(taskName, isDone);
        }
        return savedTask;
    }

    /**
     * Adds a new Task to list of Tasks.
     *
     * @param userInput The type of task and relevant details
     */
    private static void addToTasks(String userInput) {
        String taskType = userInput.split(ui.SPACE_PREFIX)[0];
        // Remove the Type of Task from the user input
        String taskName = userInput.replace(taskType, "").trim();
        try {
            Task newTask;
            if (taskType.equalsIgnoreCase(TASK_TODO_PREFIX)) {
                newTask = createNewToDo(taskName);
            } else if (taskType.equalsIgnoreCase(TASK_DEADLINE_PREFIX)) {
                newTask = createNewDeadline(taskName);
            } else if (taskType.equalsIgnoreCase(TASK_EVENT_PREFIX)) {
                newTask = createNewEvent(taskName);
            } else {
                System.out.println(UNKNOWN_COMMAND_MESSAGE);
                return;
            }
            tasks.add(newTask);
            saveToFile();
            ui.printAddedTaskMessage(newTask.getTaskName());
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Mark the task as done and print out marked as done message.
     *
     * @param index task index of task that user wants to mark as done in the list
     */
    private static void markTaskAsDone(int index) throws DukeException{
        try {
            if (tasks.size() == 0) {
                throw new DukeException(NO_TASK_MESSAGE);
            } else if (tasks.size() <= index) {
                throw new DukeException("OVERFLOWED INDEX PLEASE REPLACE ME");
            }
            Task task = tasks.get(index);
            task.setDone();
            ui.printMarkedTaskDoneMessage(task);
            saveToFile();
        } catch (NullPointerException e) {
            throw new DukeException("TOBECHANGED");
        }
    }

    private static void deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        // Print deleted message
        ui.printDeletedTaskMessage(deletedTask, tasks.size());
        saveToFile();
    }

    private static void saveToFile() {
        ArrayList<String> stringFormattedTasks;
        File saveDir = new File("data");
        saveDir.mkdir();
        File saveFile = new File(saveDir, "duke.txt");
        try {
            saveFile.createNewFile();
            FileWriter fw = new FileWriter(saveFile);
            stringFormattedTasks = getTasksAsStringArrayList();
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(stringFormattedTasks.get(i) + ui.LINE_BREAK);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    /**
     * Formats the list of Tasks and
     * returns a List of Tasks formatted as string
     *
     * @return ArrayList of String formatted Tasks
     */
    private static ArrayList<String> getTasksAsStringArrayList() {
        ArrayList<String> stringFormattedTasks = new ArrayList<String> ();
        for (int i = 0; i < tasks.size(); i++) {
            char taskIdentifier = tasks.get(i).toString().charAt(1);
            String temp = taskIdentifier + "||" + tasks.get(i).isDone() + "||" + tasks.get(i).getTaskName();
            if (taskIdentifier == 'D') {
                temp += "||" + ((Deadline) tasks.get(i)).getByWhen();
            } else if (taskIdentifier == 'E') {
                temp += "||" + ((Event) tasks.get(i)).getAtWhen();
            }
            stringFormattedTasks.add(temp);
        }
        return stringFormattedTasks;
    }

    private static void instantiateTasksFromFile() {
        File saveDir = new File("data");
        saveDir.mkdir();
        File savedFile = new File(saveDir, "duke.txt");
        if (!savedFile.exists()) {
            return;
        }
        try {
            Scanner fileScanner = new Scanner(savedFile);
            while (fileScanner.hasNext()) {
                String fileLine = fileScanner.nextLine();
                Task savedTask = createSavedTask(fileLine);
                tasks.add(savedTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void parseTaskCommands(String userInput) {
        try {
            String[] userParams = userInput.split(ui.SPACE_PREFIX);
            if (userParams[0].equalsIgnoreCase(COMMAND_DONE)) {
                int index = Integer.parseInt(userParams[1]);
                markTaskAsDone(index - 1);
            } else if (userParams[0].equalsIgnoreCase(COMMAND_DELETE)) {
                int index = Integer.parseInt(userParams[1]);
                deleteTask(index - 1);
            } else {
                addToTasks(userInput);
            }
        } catch (ArrayIndexOutOfBoundsException arrError) {
            System.out.println(MISSING_INDEX_MESSAGE);
        } catch (DukeException dukeError) {
            ui.printErrorMessage(dukeError.getMessage());
        }
    }

    public static void main(String[] args) {
        initTasks();
        ui.printLogo();
        ui.printGreeting();
        String userInput;
        while (true) {
            userInput = getUserInput();
            if (userInput.equalsIgnoreCase(COMMAND_BYE)) {
                break;
            }
            if (userInput.equalsIgnoreCase(COMMAND_LIST) || userInput.equals("")) {
                ui.printTasks(tasks);
            } else {
                parseTaskCommands(userInput);
            }
        }
        SC.close();
        ui.printFarewell();
    }
}