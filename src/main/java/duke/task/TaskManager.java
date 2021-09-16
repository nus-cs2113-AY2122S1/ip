package duke.task;

import duke.DukeException;

import java.util.ArrayList;

public class TaskManager {
    public static ArrayList<Task> taskList;

    private static final String TO_DO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String ADDED_TASK = "     Got it. I've added this task:\n       ";
    private static final String BY_SEPARATOR = "/by";
    private static final String AT_SEPARATOR = "/at";
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
        String description = getDescription(task);
        String date;
        switch (taskType) {
        case TO_DO:
            addToDo(description);
            break;
        case DEADLINE:
            date = getDate(task);
            addDeadline(description, date);
            break;
        case EVENT:
            date = getDate(task);
            addEvent(description, date);
            break;
        default:
            System.out.println("     Invalid command, please try again");
            return;
        }
        printSize();
    }

    public static void addToDo(String description) {
        taskList.add(new ToDo(description));
        System.out.println(ADDED_TASK + new ToDo(description));
    }

    public static void addDeadline(String description, String time) {
        taskList.add(new Deadline(description, time));
        System.out.println(ADDED_TASK + new Deadline(description, time));
    }

    public static void addEvent(String description, String time) {
        taskList.add(new Event(description, time));
        System.out.println(ADDED_TASK + new Event(description, time));
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
            description = task.substring(DEADLINE_STRING_LENGTH, separator - 1);
        } else if (getCommand(task).equals(EVENT)) {
            separator = task.indexOf(AT_SEPARATOR);
            description = task.substring(EVENT_STRING_LENGTH, separator - 1);
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
