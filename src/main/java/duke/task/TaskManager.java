package duke.task;

import duke.DukeException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskManager {
    public static ArrayList<Task> taskList;

    private static final String TO_DO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String ADDED_TASK = "     Got it. I've added this task:\n       ";
    private static final String ENTER_DATE = "     ☹ OOPS!!! Please enter a date";
    private static final String OUT_OF_BOUNDS = "Error! Index out of bounds";
    private static final String EMPTY_LIST = "     ☹ OOPS!!! List is empty";
    private static final String SHOW_TASKS = "     Here are the tasks in your list:";
    private static final String INVALID_COMMAND = "     Invalid command, please try again";
    private static final String BY_SEPARATOR = "/by";
    private static final String AT_SEPARATOR = "/at";
    private static final int TODO_STRING_LENGTH = 5;
    private static final int DEADLINE_STRING_LENGTH = 9;
    private static final int EVENT_STRING_LENGTH = 6;
    private static final int SEPARATOR_STRING_LENGTH = 3;
    private static final int MINIMUM_COMMAND_LENGTH = 2;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds task to taskList based on their task Type.
     *
     * @param input Command input.
     * @throws DukeException If there is no input after command.
     */
    public static void add(String input) throws DukeException {
        String[] command = input.split(" ");
        if (command.length < MINIMUM_COMMAND_LENGTH) {
            throw new DukeException();
        }
        String taskType = getCommand(input);
        String description = getDescription(input);
        String date;
        switch (taskType) {
        case TO_DO:
            addToDo(description);
            break;
        case DEADLINE:
            date = getDate(input);
            addDeadline(description, date);
            break;
        case EVENT:
            date = getDate(input);
            addEvent(description, date);
            break;
        default:
            System.out.println(INVALID_COMMAND);
            return;
        }
        printSize();
    }

    /**
     * Adds ToDo to taskList given description.
     *
     * @param description Description of ToDo.
     */
    public static void addToDo(String description) {
        taskList.add(new ToDo(description));
        System.out.println(ADDED_TASK + new ToDo(description));
    }

    /**
     * Loads ToDo from file to taskList without printing.
     *
     * @param description Description of ToDo.
     * @param isDone Previous status of ToDo from file.
     */
    public static void loadToDoFromFile(String description, boolean isDone) {
        taskList.add(new ToDo(description, isDone));
    }

    /**
     * Adds Deadline to taskList given description.
     *
     * @param description Description of deadline.
     * @param time Time of deadline.
     */
    public static void addDeadline(String description, String time) {
        taskList.add(new Deadline(description, time));
        System.out.println(ADDED_TASK + new Deadline(description, time));
    }

    /**
     * Loads Deadline from file to taskList without printing.
     *
     * @param description Description of Deadline.
     * @param time Time of Deadline.
     * @param isDone Previous status of Deadline from file.
     */
    public static void loadDeadlineFromFile(String description, String time, boolean isDone) {
        taskList.add(new Deadline(description, time, isDone));
    }

    /**
     * Adds Event to taskList given description.
     *
     * @param description Description of event.
     * @param time Time of event.
     */
    public static void addEvent(String description, String time) {
        taskList.add(new Event(description, time));
        System.out.println(ADDED_TASK + new Event(description, time));
    }

    /**
     * Loads Event from file to taskList without printing.
     *
     * @param description Description of Event.
     * @param time Time of Event.
     * @param isDone Previous status of Event from file.
     */
    public static void loadEventFromFile(String description, String time, boolean isDone) {
        taskList.add(new Event(description, time, isDone));
    }

    /**
     * Sets task to done.
     *
     * @param input Array of command for the task.
     * @throws NumberFormatException If input is not in numeric type.
     */
    public static void setDone(String[] input) throws NumberFormatException{
        try {
            Integer.parseInt(input[1]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
        taskList.get(Integer.parseInt(input[1]) - 1).taskDone(true);
    }

    /**
     * Gets description of given task.
     *
     * @param input Task where description is needed.
     * @return Description of task.
     */
    public static String getDescription(String input) {
        String description;
        int separator;
        if (getCommand(input).equals(TO_DO)) {
            description = input.substring(TODO_STRING_LENGTH);
        } else if (getCommand(input).equals(DEADLINE)) {
            separator = input.indexOf(BY_SEPARATOR);
            description = input.substring(DEADLINE_STRING_LENGTH, separator - 1);
        } else if (getCommand(input).equals(EVENT)) {
            separator = input.indexOf(AT_SEPARATOR);
            description = input.substring(EVENT_STRING_LENGTH, separator - 1);
        } else {
            description = null;
        }
        return description;
    }

    /**
     * Returns type of Task given command.
     *
     * @param input Input used to obtain task type.
     * @return Type of Task.
     */
    public static String getCommand(String input) {
        String[] task = input.split(" ");
        String taskType = task[0];

        return taskType;
    }

    /**
     * Prints number of tasks in list
     */
    public static void printSize() {
        if (getSize() == 0) {
            System.out.println("     Now you have 0 task in the list.");
        } else if (getSize() == 1) {
            System.out.println("     Now you have 1 task in the list.");
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
    public static String getName(int index) {
        return taskList.get(index - 1).toString();
    }

    /**
     * Returns size of list.
     * @return Size of list
     */
    public static int getSize() {
        return taskList.size();
    }

    /**
     * Lists out all the tasks in taskList
     */
    public static void list() {
        try {
            if (taskList.size() == 0) {
                System.out.println(EMPTY_LIST);
                return;
            }
            System.out.println(SHOW_TASKS);
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                System.out.println("     " + (i + 1) + "." + t);
            }
        } catch (IndexOutOfBoundsException i) {
            System.out.println(OUT_OF_BOUNDS);
        }

    }

    /**
     * Returns date of event or deadline.
     *
     * @param description command entered.
     * @return Date of event or deadline.
     */
    public static String getDate(String description) {
        String date;
        String taskType = getCommand(description);
        int indexOfSeparator;
        switch (taskType) {
        case DEADLINE:
            indexOfSeparator = description.indexOf(BY_SEPARATOR);
            date = description.substring(indexOfSeparator + SEPARATOR_STRING_LENGTH + 1);
            if (date.isEmpty()) {
                System.out.println(ENTER_DATE);
            }
            break;
        case EVENT:
            indexOfSeparator = description.indexOf(AT_SEPARATOR);
            date = description.substring(indexOfSeparator + SEPARATOR_STRING_LENGTH + 1);
            if (date.isEmpty()) {
                System.out.println(ENTER_DATE);
            }
            break;
        default:
            date = null;
            break;
        }
        return date;
    }

    /**
     * Deletes task given task index
     * @param input Input with the index of task to be deleted
     */
    public static void deleteTask(String[] input) {
        int taskToDelete = Integer.parseInt(input[1]);
        Task thisTask = taskList.get(taskToDelete - 1);
        System.out.println("       " + thisTask);
        taskList.remove(taskToDelete - 1);
        printSize();
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasks, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(Collectors.toList());
        return filteredList;
    }

    public static void find(String input) {
        ArrayList<Task> filteredList = TaskManager.filterTasksByString(taskList, input);
        for (int i = 0; i < filteredList.size(); i++) {
            Task t = filteredList.get(i);
            System.out.println("     " + (i + 1) + "." + t);
        }
    }
}
