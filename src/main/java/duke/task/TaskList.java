package duke.task;

import duke.exception.EmptyCommandArgumentException;
import duke.exception.InvalidCommandSeparatorException;
import duke.exception.InvalidTaskIndexException;
import duke.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    Ui ui = new Ui();

    /**
     * Deletes a task from the current task list.
     * Prints out a message indicating successful deletion and number of tasks remaining.
     *
     * @param inputWords Array of strings entered by the user, containing task information.
     * @throws EmptyCommandArgumentException If the command or its arguments are empty.
     * @throws InvalidTaskIndexException If the input task index is out-of-bounds.
     */
    public void deleteTask(String[] inputWords)
            throws EmptyCommandArgumentException, InvalidTaskIndexException {

        if (inputWords.length < 2) {
            throw new EmptyCommandArgumentException();
        }

        int taskIndex = Integer.parseInt(inputWords[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }

        Task deletedTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);

        ui.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
        ui.printLine();
        updateTasksInFile();
    }

    /**
     * Adds a deadline task or an event task to the task list.
     * Prints out the task that was added.
     *
     * @param inputWords Array of strings entered by the user, containing task information.
     * @param type String which indicates whether the task is a deadline or an event.
     * @throws EmptyCommandArgumentException If the command or its arguments are empty.
     * @throws InvalidCommandSeparatorException If the command separator is missing or invalid.
     */
    public void addDeadlineOrEventTask(String[] inputWords, String type)
            throws EmptyCommandArgumentException, InvalidCommandSeparatorException {
        // Throw exception where command argument is empty
        if (inputWords.length < 2) {
            throw new EmptyCommandArgumentException();
        }

        // Find separator index
        int separatorIndex = -1;
        for (int i = 1; i < inputWords.length; i++) {
            if (inputWords[i].equals("/by") || inputWords[i].equals("/at")) {
                separatorIndex = i;
                break;
            }
        }

        // Throw exception where separator is not found
        if (separatorIndex == -1) {
            throw new InvalidCommandSeparatorException();
        }

        // Set description
        String description = inputWords[1];
        for (int i = 2; i < separatorIndex; i++) {
            description = description + " " + inputWords[i];
        }

        // Set deadline (by when) or event time (at what time)
        String time = inputWords[separatorIndex + 1];
        for (int i = separatorIndex + 2; i < inputWords.length; i++) {
            time = time + " " + inputWords[i];
        }

        if (type.equals("deadline")) {
            taskList.add(new Deadline(description, time));
        } else {
            taskList.add(new Event(description, time));
        }

        ui.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        ui.printLine();
        appendTaskToFile(taskList.get(taskList.size() - 1));
    }

    /**
     * Adds a todo task to the task list.
     * Prints out the task that was added.
     *
     * @param inputWords Array of strings entered by the user, containing task information.
     * @throws EmptyCommandArgumentException If the command or its arguments are empty.
     */
    public void addTodoTask(String[] inputWords) throws EmptyCommandArgumentException {
        // Throw exception where command argument is empty
        if (inputWords.length < 2) {
            throw new EmptyCommandArgumentException();
        }

        String description = inputWords[1];
        for (int i = 2; i < inputWords.length; i++) {
            description = description + " " + inputWords[i];
        }

        taskList.add(new Todo(description));

        ui.printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1));
        ui.printLine();
        appendTaskToFile(taskList.get(taskList.size() - 1));
    }

    /**
     * Marks a task as done in the task list.
     * Prints the task that was marked as done.
     *
     * @param taskNumber The task number entered by the user to be marked as done.
     * @throws InvalidTaskIndexException If the input task index is out-of-bounds.
     */
    public void markTaskAsDone(String taskNumber) throws InvalidTaskIndexException {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        taskList.get(taskIndex).setAsDone();
        ui.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskIndex));
        ui.printLine();
        updateTasksInFile();
    }

    /**
     * Prints the whole task list as a numbered list.
     */
    public void showList() {
        ui.printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
        ui.printLine();
    }

    /**
     * Adds a task to the txt file when the user adds a task from the terminal interface.
     *
     * @param task The task that was added to the task list.
     */
    public void appendTaskToFile(Task task) {
        try {
            FileWriter file = new FileWriter("data/duke.txt", true);
            String taskDetails = task.getTaskDetailsInFileFormat() + "\n";
            file.write(taskDetails);
            file.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Updates or removes a task from the txt file when the user updates or deletes a task through
     * the terminal interface.
     */
    public void updateTasksInFile() {
        try {
            FileWriter file = new FileWriter("data/duke.txt");
            String taskDetails = "";

            for (int i = 0; i < taskList.size(); i++) {
                taskDetails += taskList.get(i).getTaskDetailsInFileFormat() + "\n";
            }

            file.write(taskDetails);
            file.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Adds tasks to the task list based on the tasks from the txt file.
     *
     * @param dataTask String containing the task information in txt file format.
     */
    public void processDataFile(String dataTask) {
        String[] taskDetails = dataTask.split(" \\| ");
        String taskType = taskDetails[0];
        boolean taskIsDone = Boolean.parseBoolean(taskDetails[1]);
        String taskDescription = taskDetails[2];

        switch (taskType) {
        case "T":
            taskList.add(new Todo(taskDescription));
            if (taskIsDone) {
                taskList.get(taskList.size() - 1).setAsDone();
            }
            break;
        case "D":
            String deadline = taskDetails[3];
            taskList.add(new Deadline(taskDescription, deadline));
            if (taskIsDone) {
                taskList.get(taskList.size() - 1).setAsDone();
            }
            break;
        case "E":
            String eventTime = taskDetails[3];
            taskList.add(new Event(taskDescription, eventTime));
            if (taskIsDone) {
                taskList.get(taskList.size() - 1).setAsDone();
            }
            break;
        default:
            break;
        }
    }

    /**
     * Finds a task from the task list using a single keyword entered by the user.
     * Prints out the tasks that contain the matching keyword.
     *
     * @param inputWords Array of strings entered by the user, containing the command word and keyword.
     * @throws EmptyCommandArgumentException If the command or its arguments are empty.
     */
    public void findTask(String[] inputWords) throws EmptyCommandArgumentException {
        if (inputWords.length < 2) {
            throw new EmptyCommandArgumentException();
        }

        ui.printLine();
        System.out.println("Here are the matching tasks in your list:");

        int counter = 1;
        for (int i = 0; i < taskList.size(); i++) {
            String currentTaskDescription = taskList.get(i).getDescription();

            if (currentTaskDescription.contains(inputWords[1])) {
                System.out.println(counter + ". " + taskList.get(i));
                counter++;
            }
        }

        if (counter == 1) {
            System.out.println("No matching tasks found.");
        }
        ui.printLine();
    }
}
