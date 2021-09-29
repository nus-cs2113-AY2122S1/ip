package tasklist;

import exceptions.DeadlineException;
import exceptions.EventException;
import storage.Storage;
import parser.Parser;
import ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private static final String LINE = Ui.LINE;
    private static final String LINE_W_NL = Ui.LINE_W_NL;

    /**
     * Prints out all the tasks in the list when user inputs list keyword as command.
     * @param taskList main list keeping track of user's tasks
     * @throws ArithmeticException if list is empty
     */
    public static void listTasks(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            throw new ArithmeticException();
        } else {
            System.out.println(LINE_W_NL + "Here are the tasks in your list Sir:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i).toString());
            }
            System.out.println(LINE);
        }
    }

    /**
     * Marks a task as done when user inputs that task is done.
     * It will then print a message to show that the task is done.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     */
    public static void markTaskDone(String userLine, ArrayList<Task> taskList) {
        int taskNum = Parser.parseDoneTask(userLine, taskList);
        taskList.get(taskNum-1).markAsDone();
        Ui.printMarkTaskDoneMessage(taskList, taskNum);
        Storage.fillJarvisFile(taskList);
    }

    /**
     * Deletes a task when user inputs to delete that task.
     * It will then print a message to show that the task has been deleted.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     */
    public static void deleteTask(String userLine, ArrayList<Task> taskList) {
        int taskNum = Parser.parseDeleteTask(userLine, taskList);
        Ui.printDeleteMessage(taskList, taskNum);
        taskList.remove(taskNum-1);
        Storage.fillJarvisFile(taskList);
    }

    /**
     * Adds a todo task from user input to the task list.
     * It will then print a message to show that the todo task has been added.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     */
    public static void addTodoTask(String userLine, ArrayList<Task> taskList) {
        String description = Parser.parseTodoTask(userLine);
        Todo t = new Todo(description);
        taskList.add(t);
        Ui.printAddedTask(taskList.get(taskList.size() - 1), taskList);
        Storage.fillJarvisFile(taskList);
    }

    /**
     * Adds a deadline task from user input to the task list.
     * It will then print a message to show that the deadline task has been added.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     * @throws DeadlineException if input does not contain '/by' key to split the description and
     * deadline date & time
     */
    public static void addDeadlineTask(String userLine, ArrayList<Task> taskList) throws DeadlineException {
        String[] deadlineDescriptions = Parser.parseDeadlineTask(userLine);
        Deadline t = new Deadline(deadlineDescriptions[0], Parser.parseDateTime(deadlineDescriptions[1]));
        taskList.add(t);
        Ui.printAddedTask(taskList.get(taskList.size() - 1), taskList);
        Storage.fillJarvisFile(taskList);
    }

    /**
     * Adds an event task from user input to the task list.
     * It will then print a message to show that the event task has been added.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     * @throws EventException if input does not contain '/by' key to split the description and
     * event date & time
     */
    public static void addEventTask(String userLine, ArrayList<Task> taskList) throws EventException {
        String[] eventDescriptions = Parser.parseEventTask(userLine);
        Event t = new Event(eventDescriptions[0], Parser.parseDateTime(eventDescriptions[1]));
        taskList.add(t);
        Ui.printAddedTask(taskList.get(taskList.size() - 1), taskList);
        Storage.fillJarvisFile(taskList);
    }

    /**
     * Adds a todo task from saved file to the task list.
     * @param description is the task description
     * @param taskList main list keeping track of user's tasks
     */
    public static void addTodoFileTask(String description, ArrayList<Task> taskList) {
        Todo t = new Todo(description);
        taskList.add(t);
    }

    /**
     * Adds a deadline task from saved file to the task list.
     * @param description is the task description
     * @param by is the deadline for the task
     * @param taskList main list keeping track of user's tasks
     */
    public static void addDeadlineFileTask(String description, String by, ArrayList<Task> taskList) {
        Deadline t = new Deadline(description, Parser.parseStoredDateTime(by));
        taskList.add(t);
    }

    /**
     * Adds an event task from saved file to the task list.
     * @param description is the task description
     * @param at is when the event will happen
     * @param taskList main list keeping track of user's tasks
     */
    public static void addEventFileTask(String description, String at, ArrayList<Task> taskList) {
        Event t = new Event(description, Parser.parseStoredDateTime(at));
        taskList.add(t);
    }

    /**
     * Find tasks in task list that match the keyword user inputs. It will then print the
     * list of matching tasks.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     * @throws ArithmeticException when task list is empty.
     */
    public static void findKeyTasks(String userLine, ArrayList<Task> taskList) {
        String key = Parser.parseFindTaskKey(userLine).toLowerCase();
        if (taskList.size() == 0) {
            throw new ArithmeticException();
        } else {
            int matchedTaskCount = 0;
            for (Task task : taskList) {
                if (task.getDescription().toLowerCase().contains(key)) {
                    if (matchedTaskCount == 0) {
                        System.out.println(LINE_W_NL + "Here are the matching tasks in your list Sir:");
                    }
                    System.out.println((matchedTaskCount + 1) + "." + task.toString());
                    matchedTaskCount++;
                }
            }
            if (matchedTaskCount == 0) {
                System.out.println(LINE_W_NL + "Apologies, there are no matching tasks in your list Sir.");
            }
            System.out.println(LINE);
        }
    }
}
