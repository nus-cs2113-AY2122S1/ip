package duke;

import duke.exception.DukeException;
import duke.exception.FormatException;
import duke.exception.OutOfBoundsException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static duke.ui.Ui.*;

public class TaskList {

    private static ArrayList<Task> tasks;
    private static int taskCount = 0;
    private static final Parser parser = new Parser();
    private static final Ui ui = new Ui();

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(String filePath) throws IOException, DukeException {
        tasks = new ArrayList<>();
        setUpDuke(filePath);
    }

    public Task get(int indexNumber) {
        return tasks.get(indexNumber);
    }

    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Sets up the array at the start of runtime of the programme, using the database.
     * Scans through the entire document to get data line by line.
     * Adds the task and its descriptive fields into the list.
     * Depending on the task type indicator, adds the specific task and its description into the list.
     *
     * @param filePath File path of database.
     * @throws IOException   If file to scan does not exist.
     * @throws DukeException If data fields of one line within database is insufficient (i.e. <3)
     */
    private static void setUpDuke(String filePath) throws IOException, DukeException {
        parser.setScanner(filePath);

        while (parser.hasNext()) {
            String[] lineData = parser.getLineData();
            final int TASK_TYPE_INDEX = 0;
            final int DONE_INDEX = 1;
            final int DESCRIPTION_INDEX = 2;
            String taskTypeString = lineData[TASK_TYPE_INDEX];
            String isDoneString = lineData[DONE_INDEX];
            String description = lineData[DESCRIPTION_INDEX];

            Task newTask;
            String userInput;

            switch (taskTypeString) {
            case ("T"):
                userInput = String.format("todo %s", description.trim());
                newTask = new Todo(userInput);
                tasks.add(taskCount, newTask);
                break;
            case ("E"):
                userInput = String.format("event %s", description.trim());
                newTask = new Event(userInput);
                tasks.add(taskCount, newTask);
                break;
            case ("D"):
                userInput = String.format("deadline %s", description.trim());
                newTask = new Deadline(userInput);
                tasks.add(taskCount, newTask);
                break;
            }

            if (parser.isDone(isDoneString)) {
                tasks.get(taskCount).markAsDone();
            }
            taskCount++;
        }
    }

    private static void deleteTask(String userInput) throws DukeException, OutOfBoundsException, NumberFormatException {
        int userInputInt = parser.getUserInputInt(userInput, tasks.size());
        taskCount--;
        int zeroIndexInputInt = userInputInt - 1;
        Task specifiedTask = tasks.get(zeroIndexInputInt);
        ui.handleDelete(specifiedTask, tasks, zeroIndexInputInt, taskCount);
    }

    private static void doneTask(String userInput) throws DukeException, NumberFormatException, OutOfBoundsException {
        int userInputInt = parser.getUserInputInt(userInput, tasks.size());
        int zeroIndexInputInt = userInputInt - 1;
        Task specifiedTask = tasks.get(zeroIndexInputInt);
        specifiedTask.markAsDone();
        ui.handleDone(specifiedTask);
    }

    private static void listTask() {
        ui.handleListComment();
        int taskIndex = 1;
        for (Task task : tasks) {
            ui.handleListFormat(taskIndex, task);
            taskIndex++;
        }
    }

    /**
     * Iterates through the list of tasks to check if tasks contains the search key.
     * If so, the ui handles the tasks that contains description that matches the search key and lists them out.
     *
     * @param userInput User's input to Command Line.
     * @throws DukeException If user's input lacks the search key.
     */
    private static void findTask(String userInput) throws DukeException {
        String userSearchKey = parser.getUserSearchKey(userInput);
        ui.handleListComment();
        int tasksFoundIndex = 1;
        for (Task task : tasks) {
            boolean hasSearchKey = parser.hasSearchKey(userSearchKey, task.description);
            if (hasSearchKey) {
                ui.handleFind(tasksFoundIndex, task);
                tasksFoundIndex++;
            }
        }
    }

    /**
     * Iterates through the list of tasks, and only checks the tasks in the list labelled as deadlines.
     * Tasks that are upcoming (due within less than three days) are listed by the Ui.
     */
    private static void listUpcoming() {
        ui.handleUpcomingComment();
        for (Task t : tasks) {
            if (t instanceof Deadline) {
                ui.handleUpcoming(t, parser.isThreeDaysAway(t.deadline), t.isDone);
            }
        }
    }

    private static void addTask(String userInput, TaskType specificTask) throws DukeException, FormatException {
        parser.addTaskExceptionHandler(userInput, specificTask);
        Task newTask;
        switch (specificTask) {
        case EVENT:
            newTask = new Event(userInput);
            break;
        case TODO:
            newTask = new Todo(userInput);
            break;
        case DEADLINE:
            newTask = new Deadline(userInput);
            break;
        default:
            newTask = new Task(userInput);
            break;
        }
        tasks.add(newTask);
        taskCount++;

        ui.handleAdd(newTask, taskCount);
    }


    /**
     * Parses user input command to check for the specific command keyword before executing the command.
     * If unknown, prints an unknown input error message.
     * Within each command, try and catch blocks are used to perform exception/error handling.
     * Exits the loop when 'bye' command is input.
     * <p>
     * 'done', 'delete': Catch error if task number is missing, of incorrect format, or out of bounds.
     * 'todo', 'event': Catch error if description field missing, placeholders missing.
     * 'deadline': Additionally, catch error if deadline is formatted incorrectly (unable to be parsed)
     * 'find': Catch error if search key is missing.
     */
    public void listOperations() {

        boolean isBye;
        boolean isList;
        boolean isUpcoming;
        boolean isDone;
        boolean isTodo;
        boolean isDeadline;
        boolean isEvent;
        boolean isDelete;
        boolean isFind;

        do {
            String userInput = parser.getUserInput();
            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            isUpcoming = userInput.equals("upcoming");
            isDone = userInput.startsWith("done");
            isTodo = userInput.startsWith("todo");
            isDeadline = userInput.startsWith("deadline");
            isEvent = userInput.startsWith("event");
            isDelete = userInput.startsWith("delete");
            isFind = userInput.startsWith("find");


            Ui.showHorizontalLine();
            if (isBye) {
                System.out.println(GOODBYE_COMMENT);
            } else if (isList) {
                listTask();
            } else if (isUpcoming) {
                listUpcoming();
            } else if (isDone) {
                try {
                    doneTask(userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_MARK_TASK_DESCRIPTION);
                } catch (NumberFormatException e) {
                    System.out.println(ERROR_MARK_TASK_UNKNOWN_INPUT);
                } catch (OutOfBoundsException e) {
                    System.out.println(ERROR_OUT_OF_BOUNDS);
                }
            } else if (isDelete) {
                try {
                    deleteTask(userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DELETE_DESCRIPTION);
                } catch (NumberFormatException e) {
                    System.out.println(ERROR_DELETE_TASK_UNKNOWN_INPUT);
                } catch (OutOfBoundsException e) {
                    System.out.println(ERROR_OUT_OF_BOUNDS);
                }
            } else if (isTodo) {
                try {
                    addTask(userInput, TaskType.TODO);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_TODO_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_TODO_DESCRIPTION);
                }
            } else if (isDeadline) {
                try {
                    addTask(userInput, TaskType.DEADLINE);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_DEADLINE_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION);
                } catch (DateTimeParseException e) {
                    System.out.println(ERROR_WRONG_DEADLINE);
                }
            } else if (isEvent) {
                try {
                    addTask(userInput, TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_EVENT_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_EVENT_DESCRIPTION);
                }
            } else if (isFind) {
                try {
                    findTask(userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_MISSING_FIND_DESCRIPTION);
                }
            } else {
                System.out.println(ERROR_UNKNOWN_INPUT);
            }
            Ui.showHorizontalLine();

        } while (!isBye);
    }

}
