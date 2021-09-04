import java.util.Arrays;

public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    // Constants
    private static final int MAX_TASKS = 100;
    private static final String LIST_STRING = "list";
    private static final String DONE_STRING = "done";
    private static final String TODO_STRING = "todo";
    private static final String DEADLINE_STRING = "deadline";
    private static final String EVENT_STRING = "event";
    private static final String OUTPUT_DIVIDER = "____________________________________________________________";
    private static final String MESSAGE_LIST_TASKS = " Here are the tasks in your list:";
    private static final String MESSAGE_TASK_ADDED = " I have added a task:";
    private static final String MESSAGE_TASK_MARKED_DONE = " Great! I have marked the following task as done:";
    private static final String MESSAGE_INVALID_COMMAND = " Please enter a valid command!";
    private static final String DEADLINE_PREFIX = "/by";
    private static final String EVENT_PREFIX = "/at";

    // Constructor
    public TaskManager() {
        tasks = new Task[MAX_TASKS];
        taskCount = 0;
    }

    /**
     * Handles commands input by the user, will print error message if command is of
     * the wrong format.
     *
     * @param userInput Command input by the user
     */
    public void handleUserInput(String userInput) {
        if (userInput.trim().equalsIgnoreCase(LIST_STRING)) {
            printTaskList(Arrays.copyOf(tasks, taskCount));
        } else if (userInput.trim().toLowerCase().startsWith(DONE_STRING)) {
            handleTaskDone(userInput);
        } else if (userInput.trim().toLowerCase().startsWith(TODO_STRING)) {
            handleTodo(userInput);
        } else if (userInput.trim().toLowerCase().startsWith(DEADLINE_STRING)) {
            handleDeadline(userInput);
        } else if (userInput.trim().toLowerCase().startsWith(EVENT_STRING)) {
            handleEvent(userInput);
        } else {
            printInvalidCommandMessage();
        }
    }

    /**
     * Prints a list of the current tasks Duke has
     *
     * @param tasks The array containing tasks to be printed out
     */
    public void printTaskList(Task[] tasks) {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_LIST_TASKS);
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i].toString());
        }
        System.out.println(OUTPUT_DIVIDER);
    }

    /**
     * Marks a task as done
     *
     * @param tasks The array containing the task to be mark done
     * @param taskCount The number of tasks in the tasks array
     * @param doneTask User command containing the task ID of the task to mark done
     * @throws DoneInvalidFormatException if command does not follow correct format "done {task ID}"
     * @throws NonNumericTaskIdException if task ID entered is not an integer
     * @throws TaskNotInListException if the task to mark done is not in the task list
     */
    public void markTaskDone(Task[] tasks, int taskCount, String doneTask) throws DoneInvalidFormatException, NonNumericTaskIdException, TaskNotInListException {
        String[] doneSentence = doneTask.split(" ");

        // Checks if done command entered does not follow the correct format of "done {task ID}".
        if (doneSentence.length != 2) {
            throw new DoneInvalidFormatException();
        }

        // Checks if the task ID entered is numeric.
        if (!isNumeric(doneSentence[1])) {
            throw new NonNumericTaskIdException();
        }

        int taskToMarkDone = Integer.parseInt(doneSentence[1]);
        // Makes sure that the task being mark done is in the task list.
        if (taskToMarkDone > taskCount || taskToMarkDone <= 0) {
            throw new TaskNotInListException();
        } else {
            tasks[taskToMarkDone - 1].setDone(true);
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_TASK_MARKED_DONE);
            System.out.println("   " + tasks[taskToMarkDone - 1].toString());
            System.out.println(OUTPUT_DIVIDER);
        }
    }

    /**
     * Add a todo to Duke's task list.
     * Splits addedTodo by the first whitespace (or sequence of whitespaces) encountered to separate 'todo' command from
     * its description
     *
     * @param tasks The array of tasks to add a todo to
     * @param taskCount The number of tasks in the task array
     * @param addedTodo User command containing the todo description
     * @throws TodoInvalidFormatException if the command does not follow the correct format "todo {description}"
     */
    public void addTodo(Task[] tasks, int taskCount, String addedTodo) throws TodoInvalidFormatException {
        String[] splitTodo = addedTodo.split("\\s+", 2);

        if(splitTodo.length != 2) {
            throw new TodoInvalidFormatException();
        }

        String todoDescription = splitTodo[1].trim();
        Todo newTodo = new Todo(todoDescription);
        tasks[taskCount] = newTodo;
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_TASK_ADDED);
        System.out.println("   " + newTodo.toString());
        System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
        System.out.println(OUTPUT_DIVIDER);
    }

    /**
     * Add a deadline to Duke's task list
     * Splits addedDeadline initially by the first whitespace (or sequence of whitespaces) encountered to separate
     * 'deadline' command from its description and deadline. The description and deadline is then split by DEADLINE_PREFIX
     * to obtain the arguments needed for Deadline constructor
     *
     * @param tasks The array of tasks to add a deadline to
     * @param taskCount The number of tasks in the tasks array
     * @param addedDeadline User command containing the deadline description and deadline
     * @throws DeadlineInvalidFormatException if the command does not follow the correct format "deadline {description} /by {deadline}
     * @throws DeadlineLacksArgumentsException if the command does not contain the deadline description or the deadline
     */
    public void addDeadline(Task[] tasks, int taskCount, String addedDeadline) throws DeadlineInvalidFormatException, DeadlineLacksArgumentsException {
        String[] splitDeadline = addedDeadline.split("\\s+", 2);

        if(splitDeadline.length != 2) {
            throw new DeadlineInvalidFormatException();
        }

        String[] deadlineDescriptionAndDeadline = splitDeadline[1].split(DEADLINE_PREFIX, 2);
        if(deadlineDescriptionAndDeadline.length != 2) {
            throw new DeadlineInvalidFormatException();
        }

        String deadlineDescription = deadlineDescriptionAndDeadline[0].trim();
        String deadlineDeadline = deadlineDescriptionAndDeadline[1].trim();
        boolean isValidDeadline = !deadlineDescription.isEmpty() && !deadlineDeadline.isEmpty();
        // Deadline with valid format is added to task list
        if (isValidDeadline) {
            Deadline newDeadline = new Deadline(deadlineDescription, deadlineDeadline);
            tasks[taskCount] = newDeadline;
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_TASK_ADDED);
            System.out.println("   " + newDeadline.toString());
            System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
            System.out.println(OUTPUT_DIVIDER);
        } else {
            throw new DeadlineLacksArgumentsException();
        }
    }

    /**
     * Add an event to Duke's task list
     * Splits addedEvent initially by the first whitespace (or sequence of whitespaces) encountered to separate
     * 'event' command from its description and time. The description and time is then split by EVENT_PREFIX
     * to obtain the arguments needed for Event constructor
     *
     * @param tasks The array of tasks to add an event to
     * @param taskCount The number of tasks in the tasks array
     * @param addedEvent User command containing the event description and time
     * @throws EventInvalidFormatException if the command does not follow the correct format "event {description} /at {time}"
     * @throws EventLacksArgumentsException if the command does not contain the event description or the event time
     */
    public void addEvent(Task[] tasks, int taskCount, String addedEvent) throws EventInvalidFormatException, EventLacksArgumentsException {
        String[] splitEvent = addedEvent.split("\\s+", 2);

        if(splitEvent.length != 2) {
            throw new EventInvalidFormatException();
        }

        String[] eventDescriptionAndTime = splitEvent[1].split(EVENT_PREFIX, 2);
        if (eventDescriptionAndTime.length != 2) {
            throw new EventInvalidFormatException();
        }

        String eventDescription = eventDescriptionAndTime[0].trim();
        String eventTime = eventDescriptionAndTime[1].trim();
        boolean isValidEvent = !eventDescription.isEmpty() && !eventTime.isEmpty();
        // Event with valid format is added to task list
        if (isValidEvent) {
            Event newEvent = new Event(eventDescription, eventTime);
            tasks[taskCount] = newEvent;
            System.out.println(OUTPUT_DIVIDER);
            System.out.println(MESSAGE_TASK_ADDED);
            System.out.println("   " + newEvent.toString());
            System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
            System.out.println(OUTPUT_DIVIDER);
        } else {
            throw new EventLacksArgumentsException();
        }
    }

    /**
     * Handles the adding of an event to Duke's task list, including any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleEvent(String userInput) {
        try {
            addEvent(tasks, taskCount, userInput);
            taskCount++;
        } catch (EventInvalidFormatException eventInvalidFormatException) {
            eventInvalidFormatException.printEventInvalidFormatMessage();
        } catch (EventLacksArgumentsException eventLacksArgumentsException) {
            eventLacksArgumentsException.printEventLacksArgumentsMessage();
        }
    }

    /**
     * Handles the adding of a deadline to Duke's task list, including any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleDeadline(String userInput) {
        try {
            addDeadline(tasks, taskCount, userInput);
            taskCount++;
        } catch (DeadlineInvalidFormatException deadlineInvalidFormatException) {
            deadlineInvalidFormatException.printDeadlineInvalidFormatMessage();
        } catch (DeadlineLacksArgumentsException deadlineLacksArgumentsException) {
            deadlineLacksArgumentsException.printDeadlineLacksArgumentsMessage();
        }
    }

    /**
     * Handles the adding of a todo to Duke's task list, including any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleTodo(String userInput) {
        try {
            addTodo(tasks, taskCount, userInput);
            taskCount++;
        } catch (TodoInvalidFormatException todoInvalidFormatException) {
            todoInvalidFormatException.printTodoInvalidFormatMessage();
        }
    }

    /**
     * Handles the marking of a task as done, taking into account any erroneous input
     *
     * @param userInput Command input by the user
     */
    public void handleTaskDone(String userInput) {
        try {
            markTaskDone(tasks, taskCount, userInput);
        } catch (DoneInvalidFormatException doneInvalidFormatException) {
            doneInvalidFormatException.printDoneInvalidFormatMessage();
        } catch (NonNumericTaskIdException nonNumericTaskIdException) {
            nonNumericTaskIdException.printNonNumericTaskIdMessage();
        } catch (TaskNotInListException taskNotInListException) {
            taskNotInListException.printTaskNotInListMessage();
        }
    }

    /**
     * Prints invalid command message if the command input by the user does not match any of the specified commands
     */
    public static void printInvalidCommandMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_INVALID_COMMAND);
        System.out.println(OUTPUT_DIVIDER);
    }

    /**
     * Checks if a string can be parsed to obtain an integer
     *
     * @param str String to be parsed
     * @return true if the string is numeric and can be parsed, false otherwise
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }


}
