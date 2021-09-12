package duke.task;


import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasksList = new ArrayList<>();

    private int totalNumberOfTasks = 0;

    public TaskManager() {

    }

    /**
     * Create a ToDo task and add into tasks list
     *
     * @param description The description of the task.
     */
    public void createToDoTask(String description) {
        Task newTask = new ToDo(description);
        addTask(newTask);
    }

    /**
     * Create an Event task and add into tasks list
     *
     * @param description The description of the task.
     * @param date        Start date for the event.
     */
    public void createEventTask(String description, String date) {
        Task newTask = new Event(description, date);
        addTask(newTask);
    }

    /**
     * Create a deadline task and add into tasks list.
     *
     * @param description The description of the task.
     * @param date        Due date for the deadline task.
     */
    public void createDeadlineTask(String description, String date) {
        Task newTask = new Deadline(description, date);
        addTask(newTask);
    }

    /**
     * Add the given task into the tasks list.
     *
     * @param task Task to be added into tasks list.
     */
    public void addTask(Task task) {
        tasksList.add(task);
        totalNumberOfTasks++;
        System.out.println("Got it. I've added this task:");
        printTask(totalNumberOfTasks - 1);
        System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), totalNumberOfTasks);
    }


    /**
     * Print all task status in the tasks list.
     */
    public void printAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < totalNumberOfTasks; i++) {
            System.out.printf("%s.", i + 1);
            printTask(i);
        }
        System.out.printf("There are currently %d tasks in your list.\n", totalNumberOfTasks);
    }

    /**
     * Print an individual task status in the tasks list, with reference to its index number.
     *
     * @param taskIndex The task index number in the tasks list to be printed out.
     */
    public void printTask(int taskIndex) {
        System.out.printf("%s %s" + System.lineSeparator(),
                tasksList.get(taskIndex).getStatusIcon(),
                tasksList.get(taskIndex).getTaskInfo()
        );
    }

    /**
     * Set a given task to be marked as done.
     *
     * @param taskNumber The task index number in the tasks list to be set as done.
     */
    public void setTaskToDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > totalNumberOfTasks) {
            System.out.println("Error: task not found.");
            return;
        }
        int taskIndex = taskNumber - 1;
        tasksList.get(taskIndex).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        printTask(taskIndex);
    }

    /**
     * Delete the task given by its index in the taskList.
     *
     * @param taskNumber Specified task number by list command. Its taskIndex will be taskNumber - 1.
     */
    public void deleteTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > totalNumberOfTasks) {
            System.out.println("Error: task not found.");
            return;
        }
        int taskIndex = taskNumber - 1;
        String taskDescription =
                tasksList.get(taskIndex).getStatusIcon() + " " + tasksList.get(taskIndex).getTaskInfo();
        tasksList.remove(taskIndex);
        totalNumberOfTasks -= 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskDescription);
        System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), totalNumberOfTasks);

    }


    @Override
    public String toString() {
        String data = "";
        String separator = " | ";
        for (int i = 0; i < totalNumberOfTasks; i++) {
            data += tasksList.get(i) + System.lineSeparator();
        }
        return data;
    }

    /**
     * Add given string content from file input into the tasks list.
     *
     * @param contents A task information given by a file input.
     */
    public void addTaskFromFile(String contents) {
        String[] contentArray = contents.split("\\|");
        PrintStream originalStream = System.out;
        PrintStream noOutputStream = new PrintStream(new OutputStream() {
            public void write(int b) {
                // NO-OP
            }
        });
        boolean hasError = false;
        System.setOut(noOutputStream);
        switch (contentArray[0].trim()) {
        case "[T]":
            if (contentArray.length < 3) {
                hasError = true;
            }
            createToDoTask(contentArray[2].trim());
            break;
        case "[D]":
            if (contentArray.length < 4) {
                hasError = true;
            }
            createDeadlineTask(contentArray[2].trim(), contentArray[3].trim());
            break;
        case "[E]":
            if (contentArray.length < 4) {
                hasError = true;
            }
            createEventTask(contentArray[2].trim(), contentArray[3].trim());
            break;
        default:
            hasError = true;
            break;
        }
        System.setOut(originalStream);
        if (hasError) {
            printInvalidFileInput(contents);
        } else {
            boolean isDone = false;
            if (contentArray[1].trim().equals("1")) {
                isDone = true;
            }
            tasksList.get(totalNumberOfTasks - 1).setDone(isDone);
        }
    }

    /**
     * Method to print the content in which causes the invalid error when inputing data from text file.
     *
     * @param s The input that trigger the error.
     */
    private void printInvalidFileInput(String s) {
        System.out.printf("Error: Invalid input \"%s\"\n", s);
    }

}

