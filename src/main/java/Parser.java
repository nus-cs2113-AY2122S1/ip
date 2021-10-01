public class Parser {
    public static final int BYE = 0;
    public static final int LIST = 1;
    public static final int DONE = 2;
    public static final int DELETE = 3;
    public static final int TODO = 4;
    public static final int EVENT = 5;
    public static final int DEADLINE = 6;
    public static final int FIND = 7;
    public static final int NOT_VALID = -1;

    private String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Reads user input and deciphers which command it is.
     * @return Integer to represent one of the commands.
     */
    public int command() {
        if (userInput.equals("bye")) {
            return BYE;
        }
        if (userInput.equals("list")) {
            return LIST;
        }
        if (userInput.startsWith("done")) {
            return DONE;
        }
        if (userInput.startsWith("delete")) {
            return DELETE;
        }
        if (userInput.startsWith("todo")) {
            return TODO;
        }
        if (userInput.startsWith("event")) {
            return EVENT;
        }
        if (userInput.startsWith("deadline")) {
            return DEADLINE;
        }
        if (userInput.startsWith("find")) {
            return FIND;
        }
        return NOT_VALID;
    }

    /**
     * Reads user input for the command "done" and returns the index of the task that has been completed.
     * @return The index of the task that is done.
     */
    public int doneTaskIndex() {
        int doneIndex = -1;
        try {
            String n = userInput.substring(5);
            doneIndex = Integer.parseInt(n) - 1;
        } catch (NumberFormatException e) {
        }
        return doneIndex;

    }

    /**
     * Reads user input for the command "delete" and returns the index of the task that has been deleted.
     * @return The index of the task that is deleted.
     */
    public int deleteTaskIndex() {
        int delIndex = -1;
        try {
            String n = userInput.substring(7);
            delIndex = Integer.parseInt(n) - 1;
        } catch (NumberFormatException e) {
        }
        return delIndex;
    }

    /**
     * Reads user input for the command "todo" and finds the description of the Todo, to store it in the Task List.
     * @return The task that has been newly created, to be stored in the Task List.
     * @throws IllegalTaskException Exception thrown when there is no name of the task. Asks user to input valid task.
     */
    public Todo getTodo() throws InvalidTask {
        String taskName = userInput.substring(4).strip();
        if (taskName.isEmpty()) {
            throw new InvalidTask();
        }
        Todo newTask = new Todo(taskName);
        return newTask;
    }

    /**
     * Reads in user input for the command "event" and finds the description of the Event, which will be stored in the
     * Task List.
     * @return The task that has been newly created, to be stored in the Task List.
     * @throws InvalidEventFormat Exception thrown when the format of the Event does not match what is expected, which
     * is "/by".
     * @throws InvalidTask Exception thrown when there is no description that has been added for the Event.
     */
    public Event getEvent() throws InvalidEventFormat, InvalidTask {
        int startOfDate = userInput.indexOf('/');
        if (!userInput.contains("/at")) {
            throw new InvalidEventFormat();
        }
        if (startOfDate - 1 < 6) { // make sure the substring exists
            throw new InvalidTask();
        }
        String task = userInput.substring(6, startOfDate - 1);
        String date = userInput.substring(startOfDate + 4);
        Event newTask = new Event(task, date);
        return newTask;
    }

    /**
     * Reads in user input for the command "deadline" and finds the description of the Deadline, which will be stored in
     * the Task List.
     * @return The task that has been newly created, to be stored in the Task List.
     * @throws InvalidDeadlineFormat Exception thrown when the format of the Deadline does not match what is expected,
     * which is "/at".
     * @throws InvalidTask Exception thrown when there is no description that has been added for the Deadline.
     */
    public Deadline getDeadline() throws InvalidDeadlineFormat, InvalidTask {
        int startOfDate = userInput.indexOf('/');
        if (!userInput.contains("/by")) {
            throw new InvalidDeadlineFormat();
        }
        if (startOfDate - 1 < 9) { // make sure the substring exists
            throw new InvalidTask();
        }
        String task = userInput.substring(9, startOfDate - 1);
        String date = userInput.substring(startOfDate + 4);
        Deadline newTask = new Deadline(task, date);
        return newTask;
    }

    /**
     * Reads user input for the command "find", and takes the description in the task list that the user is trying to
     * find
     * @return The description in the task list that the user is trying to find.
     */
    public String getTask() {
        String newTask = userInput.substring(5);
        return newTask;
    }

}
