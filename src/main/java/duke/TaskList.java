package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public static final String TO_DO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";

    // Specific indexes of the start of task descriptions in the user's command inputs
    public static final int INDEX_OF_TO_DO_TASK = 4;
    public static final int INDEX_OF_DEADLINE_TASK = 8;
    public static final int INDEX_OF_EVENT_TASK = 5;

    public static final String HORIZONTAL = "____________________________________________________________\n";

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a to-do task to the ArrayList and shows the user the task that is added.
     *
     * @param line The description of the task.
     * @throws DukeException If the user inputs without a description.
     */
    public void addToDo(String line) throws DukeException {
        String task = line.substring(INDEX_OF_TO_DO_TASK).trim();
        if (task.equals("")) {
            throw new DukeException();
        }
        tasks.add(new ToDo(task));
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks.get(tasks.size() - 1) +
                "\n" + "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    /**
     * Adds a deadline to the ArrayList and shows the user the task that is added.
     *
     * @param line The description and due date of the task separated by "/by".
     * @throws DukeException If the user inputs without a description or a due date.
     */
    public void addDeadline(String line) throws DukeException {
        if (!line.contains("/by")) {
            throw new DukeException();
        }
        int slash = line.indexOf("/by");
        String task = line.substring(INDEX_OF_DEADLINE_TASK, slash).trim();
        if (task.equals("")) {
            throw new DukeException();
        }
        String dueDate = line.substring(slash + 3).trim();
        if (dueDate.equals("")) {
            throw new DukeException();
        }
        tasks.add(new Deadline(task, dueDate));
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks.get(tasks.size() - 1) +
                "\n" + "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    /**
     * Adds an event to the ArrayList and shows the user the task that is added.
     *
     * @param line The description and event time of the task separated by "/at".
     * @throws DukeException If the user inputs without a description or an event time.
     */
    public void addEvent(String line) throws DukeException {
        if (!line.contains("/at")) {
            throw new DukeException();
        }
        int slash = line.indexOf("/at");
        String task = line.substring(INDEX_OF_EVENT_TASK, slash).trim();
        if (task.equals("")) {
            throw new DukeException();
        }
        String eventTime = line.substring(slash + 3).trim();
        if (eventTime.equals("")) {
            throw new DukeException();
        }
        tasks.add(new Event(task, eventTime));
        System.out.println(HORIZONTAL + "Got it. I've added this task: \n  " + tasks.get(tasks.size() - 1) +
                "\n" + "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? " " : "s ") +
                "in the list. \n" + HORIZONTAL);
    }

    /**
     * Filters the command input by the user to determine the type of task
     * and calls addToDo, addDeadline and addEvent to add the respective task
     * to the ArrayList. If the command is an invalid task, or if one of the
     * task components is missing, this function will show an error message.
     *
     * @param line The command input by the user to add a task.
     */
    public void addTask(String line) {
        if (line.contains(TO_DO)) {
            try {
                addToDo(line);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! The description of a todo cannot be empty. \n" +
                        HORIZONTAL);
            }
        } else if (line.contains(DEADLINE)) {
            try {
                addDeadline(line);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! A component of the deadline task is empty. \n" +
                        HORIZONTAL);
            }
        } else if (line.contains(EVENT)) {
            try {
                addEvent(line);
            } catch (DukeException e) {
                System.out.println(HORIZONTAL + "☹ OOPS!!! A component of the event task is empty. \n" +
                        HORIZONTAL);
            }
        } else {
            System.out.println(HORIZONTAL + "☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n" + HORIZONTAL);
        }
    }

    /**
     * Shows the user a numbered list of all tasks stored in the ArrayList,
     * including their descriptions and whether they are done.
     */
    public void listTasks() {
        System.out.print(HORIZONTAL + "Here are the tasks in your list: \n");
        for (Task task : tasks) {
            System.out.println((tasks.indexOf(task) + 1) + "." + task);
        }
        System.out.println(HORIZONTAL);
    }

    /**
     * Shows the user a list of tasks containing a given keyword.
     *
     * @param keyword The keyword the user is searching for.
     */
    public void findTasks(String keyword) {
        System.out.println(HORIZONTAL + "Here are the matching tasks in your list: ");
        int counter = 0;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                counter++;
                System.out.println(counter + "." + task);
            }
        }
        System.out.println(HORIZONTAL);
    }

    /**
     * Marks a task in the ArrayList as done and shows the user the task
     * that is marked done.
     *
     * @param taskDone The index of the task on the ArrayList to be marked done.
     */
    public void markDone(int taskDone) {
        tasks.get(taskDone - 1).markAsDone();
        System.out.println(HORIZONTAL + "Nice! I've marked this task as done: \n  "
                + tasks.get(taskDone - 1) + "\n" + HORIZONTAL);
    }

    /**
     * Removes a task from the ArrayList and shows the user the task that
     * is removed.
     *
     * @param taskDeleted The index of the task on the ArrayList to be deleted.
     */
    public void delete(int taskDeleted) {
        System.out.println(HORIZONTAL + "Noted. I've removed this task: \n  " + tasks.get(taskDeleted - 1));
        tasks.remove(taskDeleted - 1);
        System.out.println("Now you have " + tasks.size() + " task"
                + (tasks.size() == 1 ? " " : "s ") + "in the list. \n" + HORIZONTAL);
    }
}
