package triss;

import triss.exception.TrissException;
import triss.task.Deadline;
import triss.task.Event;
import triss.task.Task;
import triss.task.Todo;

import java.util.ArrayList;

public class TaskList {

    /** Array to keep track of user's tasks */
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private Ui ui;
    private Parser parser;

    /** Length of the word "todo" */
    public final int LENGTH_OF_WORD_TODO = 4;

    /** Length of the word "deadline" */
    public final int LENGTH_OF_WORD_DEADLINE = 8;
    /** Length of the word "event" */
    public final int LENGTH_OF_WORD_EVENT = 5;

    public TaskList() {
        ui = new Ui();
        parser = new Parser();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Creates a new todo based on user's input.
     * If user did not type in this format: "todo Eat with Friends", it asks the user to try again.
     * @param userInput Any user input starting with the words "todo"
     */
    public void createNewTodo(String userInput, boolean isSilent) throws TrissException {
        String taskName;
        taskName = userInput.substring(LENGTH_OF_WORD_TODO).trim();

        if (taskName.isBlank()) {
            String errorMessage = "You didn't specify a name for your todo! Let's try that again.\n"
                    + " \n" + "Type a todo in this format:\n" + "    todo Eat with Friends";
            throw new TrissException(errorMessage);
        }

        // Add todo to tasks
        Task newTodo = new Todo(taskName);
        addTask(newTodo);

        // Then, echo the task if not silent
        if (!isSilent) {
            ui.printLine("I've added: " + newTodo.printTask());
        }
    }

    /**
     * Creates a new event based on the user's input.
     * User has to type the input in this format: "event Stay in a log cabin /Friday the 13th".
     * If the user types incorrectly, it asks the user to try again.
     * @param userInput Any user input starting with the word "event".
     */
    public void createNewEvent(String userInput, boolean isSilent) throws TrissException {
        String taskName;
        String eventTiming;

        // Parse the task's name from the user's input
        try {
            taskName = parser.getTaskName(userInput, LENGTH_OF_WORD_EVENT);
            eventTiming = parser.getDeadlineOrTiming(userInput);
        } catch (Exception error) {
            String errorMessage = "You didn't format your event properly!\n"
                    + " \n"
                    + "Try inserting an event in this format:\n"
                    + "    event Stay in a log cabin /Friday the 13th";
            throw new TrissException(errorMessage);
        }

        // Catch other possible errors
        // Throw error if user did not type in a name for the task
        if (taskName.isBlank()) {
            throw new TrissException("Your event name is blank! Let's try that again.");
        }

        // Throw error if user did not type in a timing for the event
        if (eventTiming.isBlank()) {
            throw new TrissException("You didn't insert a date in your event! Let's try that again.");
        }

        // Add event to tasks
        Event newEvent = new Event(taskName, eventTiming);
        addTask(newEvent);

        // Then, echo the task if not silent
        if (!isSilent) {
            ui.printLine("I've added: " + newEvent.printTask());
        }
    }

    /**
     * Creates a new deadline based on the user's input.
     * User has to type the input in this format: "deadline Meet with Friends /12th July".
     * If the user types incorrectly, it asks the user to try again.
     * @param userInput Any user input starting with the word "deadline".
     */
    public void createNewDeadline(String userInput, boolean isSilent) throws TrissException {
        String deadlineDate;
        String taskName;

        try {
            deadlineDate = parser.getDeadlineOrTiming(userInput);
            taskName = parser.getTaskName(userInput, LENGTH_OF_WORD_DEADLINE);
        } catch (Exception error) {
            String errorMessage = "You didn't write your deadline properly!\n"
                    + " \n"
                    + "Try inserting a deadline in this format:\n"
                    + "    deadline Meet with Friends /12th July";
            throw new TrissException(errorMessage);
        }

        // Catch other possible errors
        // Throw error if user did not type in a name for the task
        if (taskName.isBlank()) {
            throw new TrissException("Your deadline name is blank! Let's try that again.");
        }

        // Throw error if user did not type in a timing for the event
        if (deadlineDate.isBlank()) {
            throw new TrissException("You didn't insert a date in your deadline! Let's try that again.");
        }

        // Add deadline to tasks
        Deadline newDeadline = new Deadline(taskName, deadlineDate);
        tasks.add(newDeadline);

        // Then, echo the task if not silent
        if (!isSilent) {
            ui.printLine("I've added: " + newDeadline.printTask());
        }
    }

    public int getIndexOfTask(Task task) {
        return tasks.indexOf(task);
    }

    /**
     * Prints all tasks stored in Task Array tasks.
     */
    public void printAllTasks() {
        // If user said "list", print a list of all saved tasks
        for (Task task: getTasks()) {
            ui.printLine(getIndexOfTask(task) + 1 + "." + task.printTask());
        }
    }

}
