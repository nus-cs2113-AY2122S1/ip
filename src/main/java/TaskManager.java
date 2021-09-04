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
     * Prints a list of the current tasks
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

    public void addTodo(Task[] tasks, int taskCount, String addedTodo) throws TodoInvalidFormatException {
        String[] splitTodo = addedTodo.split("\\s+", 2);

        // Makes sure that the todo entered is of the format "todo {description}"
        if(splitTodo.length != 2) {
            throw new TodoInvalidFormatException();
        }

        String todoDescription = splitTodo[1];
        Todo newTodo = new Todo(todoDescription);
        tasks[taskCount] = newTodo;
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_TASK_ADDED);
        System.out.println("   " + newTodo.toString());
        System.out.println(" You now have " + (taskCount + 1) + " task(s) in the list.");
        System.out.println(OUTPUT_DIVIDER);
    }

    public void addDeadline(Task[] tasks, int taskCount, String addedDeadline) throws DeadlineInvalidFormatException, DeadlineLacksArgumentsException {
        String[] splitDeadline = addedDeadline.split("\\s+", 2);

        // Makes sure that the deadline entered is of the format "deadline {description} /by {deadline}"
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
        // deadline with valid format is added to task list
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

    public void addEvent(Task[] tasks, int taskCount, String addedEvent) throws EventInvalidFormatException, EventLacksArgumentsException {
        String[] splitEvent = addedEvent.split("\\s+", 2);

        // Makes sure that the event entered is of the format "event {description} /at {event time}"
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
        // event with valid format is added to task list
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

    public void handleTodo(String userInput) {
        try {
            addTodo(tasks, taskCount, userInput);
            taskCount++;
        } catch (TodoInvalidFormatException todoInvalidFormatException) {
            todoInvalidFormatException.printTodoInvalidFormatMessage();
        }
    }

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

    public static void printInvalidCommandMessage() {
        System.out.println(OUTPUT_DIVIDER);
        System.out.println(MESSAGE_INVALID_COMMAND);
        System.out.println(OUTPUT_DIVIDER);
    }


    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
