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
import java.util.ArrayList;

public class TaskList {
    final private static String GOODBYE_COMMENT = "Bye. Hope to see you again soon!";
    final private static String ERROR_MARK_TASK = "Please do not leave your task number empty :-(";
    final private static String ERROR_UNKNOWN_INPUT = ":-( OOPS!!! I'm sorry, but I don't know what that means :-(";
    final private static String ERROR_EMPTY_TODO_DESCRIPTION = "Please do not leave your todo description empty :-(";
    final private static String ERROR_EMPTY_DEADLINE_DESCRIPTION = "Please do not leave your deadline description empty :-(";
    final private static String ERROR_EMPTY_EVENT_DESCRIPTION = "Please do not leave your event description empty :-(";
    final private static String ERROR_EMPTY_DELETE_DESCRIPTION = "Please do not leave your delete task number empty :-(";
    final private static String ERROR_WRONG_HANDLE_TODO_DESCRIPTION = "Check for missing fields in your description!";
    final private static String ERROR_WRONG_HANDLE_EVENT_DESCRIPTION = "Include /at handler and insert date of event!";
    final private static String ERROR_WRONG_HANDLE_DEADLINE_DESCRIPTION = "Include /by handler and insert deadline!";
    final private static String ERROR_MARK_TASK_UNKNOWN_INPUT = "Please enter as follows: done (INT in number)";
    final private static String ERROR_DELETE_TASK_UNKNOWN_INPUT = "Please enter as follows: delete (INT in number)";
    final private static String ERROR_OUT_OF_BOUNDS = "That task does not exist! Stop fooling around!";
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
                newTask = new Todo(userInput, taskCount);
                tasks.add(taskCount, newTask);
                break;
            case ("E"):
                userInput = String.format("event %s", description.trim());
                newTask = new Event(userInput, taskCount);
                tasks.add(taskCount, newTask);
                break;
            case ("D"):
                userInput = String.format("deadline %s", description.trim());
                newTask = new Deadline(userInput, taskCount);
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
        ui.handleDelete(userInputInt, taskCount, tasks);
    }

    private static void doneTask(String userInput) throws DukeException, NumberFormatException, OutOfBoundsException {
        int userInputInt = parser.getUserInputInt(userInput, tasks.size());
        tasks.get(userInputInt).markAsDone();
        ui.handleDone(userInputInt, tasks);
    }

    private static void listTask() {
        ui.handleListComment();
        for (int i = 0; i < taskCount; i++) {
            ui.handleListFormat(i, tasks);
        }
    }

    private static void addTask(String userInput, TaskType specificTask) throws DukeException, FormatException {
        parser.addTaskExceptionHandler(userInput, specificTask);
        Task newTask;
        switch (specificTask) {
        case EVENT:
            newTask = new Event(userInput, taskCount);
            break;
        case TODO:
            newTask = new Todo(userInput, taskCount);
            break;
        case DEADLINE:
            newTask = new Deadline(userInput, taskCount);
            break;
        default:
            newTask = new Task(userInput, taskCount);
            break;
        }
        tasks.add(newTask);
        taskCount++;

        ui.handleAdd(newTask, taskCount);
    }

    public void listOperations() {

        boolean isBye;
        boolean isList;
        boolean isDone;
        boolean isTodo;
        boolean isDeadline;
        boolean isEvent;
        boolean isDelete;

        do {
            String userInput = parser.getUserInput();
            isBye = userInput.equals("bye");
            isList = userInput.equals("list");
            isDone = userInput.startsWith("done");
            isTodo = userInput.startsWith("todo");
            isDeadline = userInput.startsWith("deadline");
            isEvent = userInput.startsWith("event");
            isDelete = userInput.startsWith("delete");

            Ui.showHorizontalLine();
            if (isBye) {
                System.out.println(GOODBYE_COMMENT);
            } else if (isList) {
                listTask();
            } else if (isDone) {
                try {
                    doneTask(userInput);
                } catch (DukeException e) {
                    System.out.println(ERROR_MARK_TASK);
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
                }
            } else if (isEvent) {
                try {
                    addTask(userInput, TaskType.EVENT);
                } catch (DukeException e) {
                    System.out.println(ERROR_EMPTY_EVENT_DESCRIPTION);
                } catch (FormatException e) {
                    System.out.println(ERROR_WRONG_HANDLE_EVENT_DESCRIPTION);
                }
            } else {
                System.out.println(ERROR_UNKNOWN_INPUT);
            }
            Ui.showHorizontalLine();

        } while (!isBye);
    }

}
