package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    private static final String TO_DO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String ADDED_TASK = "     Got it. I've added this task:\n       ";
    private static final String BY_SEPARATOR = "by";
    private static final String AT_SEPARATOR = "at";
    private static final int TODO_STRING_LENGTH = 5;
    private static final int DEADLINE_STRING_LENGTH = 9;
    private static final int EVENT_STRING_LENGTH = 6;
    private static final int SEPARATOR_STRING_LENGTH = 3;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds task to taskList ArrayList
     *
     * @param task Task to be checked
     */
    public void add(String task) throws DukeException {
        String[] command = task.split(" ");
        if (command.length < 2) {
            throw new DukeException();
        }
        String taskType = getCommand(task);
        switch (taskType) {
        case TO_DO:
            addToDo(task);
            break;
        case DEADLINE:
            addDeadline(task);
            break;
        case EVENT:
            addEvent(task);
            break;
        default:
            System.out.println("     Invalid command, please try again");
            return;
        }
        printSize();
    }

    public void addToDo(String task) {
        Task newTask;
        String description = getDescription(task);
        newTask = new ToDo(description);
        taskList.add(newTask);
        System.out.println(ADDED_TASK + newTask);
    }

    public void addDeadline(String task) {
        Task newTask;
        String description = getDescription(task);
        String by = getDate(task);
        newTask = new Deadline(description, by);
        taskList.add(newTask);
        System.out.println(ADDED_TASK + newTask);
    }

    public void addEvent(String task) {
        Task newTask;
        String description = getDescription(task);
        String at = getDate(task);
        newTask = new Event(description, at);
        taskList.add(newTask);
        System.out.println(ADDED_TASK + newTask);
    }

    public void checkDone(String[] command) {
        try {
            Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        taskList.get(Integer.parseInt(command[1]) - 1).taskDone();
    }

    public String getDescription(String task) {
        String description;
        int separator;
        if (getCommand(task).equals(TO_DO)) {
            description = task.substring(TODO_STRING_LENGTH);
        } else if (getCommand(task).equals(DEADLINE)) {
            separator = task.indexOf(BY_SEPARATOR);
            description = task.substring(DEADLINE_STRING_LENGTH, separator);
        } else if (getCommand(task).equals(EVENT)) {
            separator = task.indexOf(AT_SEPARATOR);
            description = task.substring(EVENT_STRING_LENGTH, separator);
        } else {
            description = null;
        }
        return description;
    }



    /**
     *  Returns type of Task given command
     * @param command
     * @return Type of Task
     */
    public String getCommand(String command) {
        String[] task = command.split(" ");
        String taskType = task[0];

        return taskType;
    }

    public void printSize() {
        if (getSize() == 1) {
            System.out.println("     Now you have " + 1 + " task in the list.");
        } else {
            System.out.println("     Now you have " + getSize() + " tasks in the list.");
        }
    }

    /**
     * Returns name of task given task number
     *
     * @param index index of task in taskList ArrayList
     * @return Name of Task
     */
    public String getName(int index) {
        return taskList.get(index - 1).toString();
    }

    public int getSize() {
        return taskList.size();
    }

    public void list() {
        try {
            if (taskList.size() == 0) {
                System.out.println("     ☹ OOPS!!! List is empty");
                return;
            }
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                System.out.println("     " + (i + 1) + "." + t);
            }
        } catch (IndexOutOfBoundsException i) {
            System.out.println("Error! Please contact admin");
        }

    }

    /**
     * Returns date of event or deadline.
     *
     * @param description command entered.
     * @return Date of event or deadline.
     */
    public String getDate(String description) {
        String date;
        String taskType = getCommand(description);
        int indexOfSeparator;
        switch (taskType) {
        case DEADLINE:
            indexOfSeparator = description.indexOf(BY_SEPARATOR);
            date = description.substring(indexOfSeparator + SEPARATOR_STRING_LENGTH);
            if (date.isEmpty()) {
                System.out.println("     ☹ OOPS!!! Please enter a date");
            }
            break;
        case EVENT:
            indexOfSeparator = description.indexOf(AT_SEPARATOR);
            date = description.substring(indexOfSeparator + SEPARATOR_STRING_LENGTH);
            if (date.isEmpty()) {
                System.out.println("     ☹ OOPS!!! Please enter a date");
            }
            break;
        default:
            date = null;
            break;
        }
        return date;
    }

    public void deleteTask(String[] input) {
        int taskToDelete = Integer.parseInt(input[1]);
        Task thisTask = taskList.get(taskToDelete - 1);
        System.out.println("       " + thisTask);
        taskList.remove(taskToDelete - 1);
        printSize();
    }
}
