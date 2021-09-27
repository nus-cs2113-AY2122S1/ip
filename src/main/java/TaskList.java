import java.util.ArrayList;

/**
 * TaskList class contains and facilitate operations on the inputTasks.
 */
public class TaskList {
    private static ArrayList<Task> inputTasks = new ArrayList<>();
    private static String acknowledgeMessage;

    public static int getTaskListSize() {
        return inputTasks.size();
    }

    public static ArrayList<Task> getInputTasks() {
        return inputTasks;
    }

    private static Todo createTodo(String input) {
        return new Todo(input.substring(Todo.SIGNATURE.length()).trim());
    }

    private static Event createEvent(String input) {
        String[] inputSlices = input.substring(Event.SIGNATURE.length()).trim().split(Event.DELIMITER);
        return new Event(inputSlices[0].trim(), inputSlices[1].trim());
    }

    private static Deadline createDeadline(String input) {
        String[] inputSlices = input.substring(Deadline.SIGNATURE.length()).trim().split(Deadline.DELIMITER);
        return new Deadline(inputSlices[0].trim(), inputSlices[1].trim());
    }

    /**
     * Instantiates a new Task object corresponding to todo/event/deadline by parsing user input.
     *
     * @param input Command entered by the user.
     * @return A new Task object containing data from user input.
     * @throws DukeException If neither todo, deadline or event correspond to the input String.
     */
    public static Task createTask(String input) throws DukeException {
        Task newTask;
        if (input.startsWith(Todo.SIGNATURE)) {
            newTask = createTodo(input);
        } else if (input.startsWith(Deadline.SIGNATURE)) {
            newTask = createDeadline(input);
        } else if (input.startsWith(Event.SIGNATURE)) {
            newTask = createEvent(input);
        } else {
            throw new DukeException();
        }

        return newTask;
    }

    /**
     * Stores a given task corresponding to todo/event/deadline.
     *
     * @param newTask            Latest Task to be stored.
     * @param shouldAppendToFile Flag to indicate whether the newTask should be appended to file.
     */
    public static void storeTask(Task newTask, boolean shouldAppendToFile) {
        inputTasks.add(newTask);

        acknowledgeMessage = "Got it. I've added this task: \n  " + inputTasks.get(inputTasks.size() - 1) + "\n"
                + "Now you have: " + inputTasks.size() + " tasks in the list";

        Ui.echo(acknowledgeMessage);

        if (shouldAppendToFile) {
            Storage.appendTaskToFile(newTask);
        }
    }

    /**
     * Deletes a task from the list inputTasks based on its index.
     *
     * @param taskNumber Index of the task in inputTasks to be deleted.
     */
    public static void deleteTask(int taskNumber) {
        int taskIndex = taskNumber - 1;
        try {
            acknowledgeMessage = "Noted. I've removed this task: \n" + inputTasks.get(taskIndex) + "\n"
                    + "Now you have: " + (inputTasks.size() - 1) + " tasks in the list";

            inputTasks.remove(taskIndex);

            Ui.echo(acknowledgeMessage);

            Storage.writeTasksToFile();
        } catch (IndexOutOfBoundsException e) {
            Ui.echo("Please enter a task number from the list");
        }
    }

    /**
     * Prints all tasks in inputTasks that contain a given query String in their descriptions.
     *
     * @param query Substring to search for within each task's description.
     */
    public static void findTask(String query) {
        System.out.println(Ui.getLine());
        System.out.println("Here are the matching tasks in your list:");
        int taskNumber = 1;
        for (Task task : inputTasks) {
            if (task.description.contains(query)) {
                System.out.println(taskNumber + ". " + task);
                taskNumber++;
            }
        }

        System.out.println(Ui.getLine());
    }

    /**
     * Prints all tasks in inputTasks.
     */
    public static void list() {
        System.out.println(Ui.getLine());

        for (int i = 0; i < inputTasks.size(); i++) {
            System.out.println((i + 1) + ". " + inputTasks.get(i));
        }

        System.out.println(Ui.getLine());
    }

    /**
     * Marks a pre-existing task in the inputTasks list as completed.
     *
     * @param completedTask     Index of the task in inputTasks to be marked as complete.
     * @param shouldWriteToFile Flag indicating whether the updated inputTasks list should be written to file.
     */
    public static void markComplete(int completedTask, boolean shouldWriteToFile) {
        try {
            inputTasks.get(completedTask - 1).markComplete();
            Ui.echo("Nice! I've marked this task as done: \n" + inputTasks.get(completedTask - 1));

            if (shouldWriteToFile) {
                Storage.writeTasksToFile();
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.echo("Please enter a task number from the list");
        }
    }
}
