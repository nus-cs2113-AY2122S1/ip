package duke.task;

import java.io.OutputStream;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class that handles any Task related objects and maintain a task list.
 */
public class TaskManager {

    private ArrayList<Task> taskList = new ArrayList<>();

    private int totalNumberOfTasks = 0;

    public TaskManager() {

    }

    /**
     * Create a ToDo task and add into tasks list.
     *
     * @param description The description of the task.
     */
    public void createToDoTask(String description) {
        Task newTask = new ToDo(description);
        addTask(newTask);
    }

    /**
     * Create an Event task and add into tasks list.
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
        taskList.add(task);
        totalNumberOfTasks++;
        System.out.println("Got it. I've added this task:");
        printTask(totalNumberOfTasks - 1);
        System.out.printf("Now you have %d tasks in the list" + System.lineSeparator(), totalNumberOfTasks);
    }


    /**
     * Print all task information in the tasks list.
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
     * Print an individual task information in the tasks list, with reference to its index number.
     *
     * @param taskIndex The task index number in the tasks list to be printed out
     */
    public void printTask(int taskIndex) {
        System.out.printf("%s %s" + System.lineSeparator(),
                taskList.get(taskIndex).getStatusIcon(),
                taskList.get(taskIndex).getTaskInfo()
        );
    }

    /**
     * Set a given task to be marked as done.
     *
     * @param taskNumber The task index number in the tasks list to be set as done
     */
    public void setTaskToDone(int taskNumber) {
        if (taskNumber < 1 || taskNumber > totalNumberOfTasks) {
            System.out.println("Error: task not found.");
            return;
        }
        int taskIndex = taskNumber - 1;
        taskList.get(taskIndex).setDone(true);
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
                taskList.get(taskIndex).getStatusIcon() + " " + taskList.get(taskIndex).getTaskInfo();
        taskList.remove(taskIndex);
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
            data += taskList.get(i) + System.lineSeparator();
        }
        return data;
    }

    /**
     * Method to gather contents from input file that is handled by a file handler and save into the task list. This
     * method is usually run at the setup phase of duke. Any task information that do not comply with the existing
     * format guidelines will be discarded and ignored.
     *
     * @param contents Contents from a text file that contains a previous saved task list.
     */
    public void processContentsFromFile(ArrayList<String> contents) {
        PrintStream originalStream = System.out;
        PrintStream noOutputStream = new PrintStream(new OutputStream() {
            public void write(int b) {
                // NO-OP
            }
        });
        System.setOut(noOutputStream);
        for (String s : contents) {
            System.out.println(s);
            try {
                addTaskFromContent(s);
            } catch (DateTimeParseException e) {
                System.setOut(originalStream);
                System.out.printf("Error: Invalid date detected.\n%s\n", s);
                System.setOut(noOutputStream);
            } catch (TaskManagerException e) {
                System.setOut(originalStream);
                System.out.println(e);
                System.setOut(noOutputStream);
            }
        }
        System.setOut(originalStream);
    }

    /**
     * Add given string content from file input into the tasks list.
     *
     * @param contents A task information given by a file input.
     */
    private void addTaskFromContent(String contents) throws DateTimeParseException, TaskManagerException {
        String[] contentArray = contents.split("\\|");
        switch (contentArray[0].trim()) {
        case "[T]":
            if (contentArray.length < 3) {
                throw new TaskManagerException(getInvalidFileInputMessage(contents));
            }
            createToDoTask(contentArray[2].trim());
            break;
        case "[D]":
            if (contentArray.length < 4) {
                throw new TaskManagerException(getInvalidFileInputMessage(contents));
            }
            createDeadlineTask(contentArray[2].trim(), contentArray[3].trim());
            break;
        case "[E]":
            if (contentArray.length < 4) {
                throw new TaskManagerException(getInvalidFileInputMessage(contents));
            }
            createEventTask(contentArray[2].trim(), contentArray[3].trim());
            break;
        default:
            throw new TaskManagerException(getInvalidFileInputMessage(contents));
        }

        boolean isDone = false;
        if (contentArray[1].trim().equals("1")) {
            isDone = true;
        }
        taskList.get(totalNumberOfTasks - 1).setDone(isDone);

    }

    /**
     * Method to get the content in which causes the invalid error when placing data from text file.
     *
     * @param s The input that trigger the error.
     * @return The full error message of invalid format.
     */
    private String getInvalidFileInputMessage(String s) {
        return String.format("Error: Invalid input format. \"%s\"\n", s);
    }

    public void printTaskOnDate(String date) {
        for (int i = 0; i < totalNumberOfTasks; i++) {
            if (doesTaskHasDate(taskList.get(i))) {
                if (taskList.get(i).getDate().equals(date)) {
                    printTask(i);
                }
            }
        }
    }

    /**
     * Method that ensure the Task has a date argument tag in it. For example Deadline and Event are the current only
     * ones.
     *
     * @param t Given a task object to be tested on.
     * @return Whether the Task has the date attribute argument.
     */
    private boolean doesTaskHasDate(Task t) {
        return t instanceof Event || t instanceof Deadline;
    }

    /**
     * Method to find all task that contains the given keyword.
     *
     * @param keyword User given keyword to filter out Tasks in task list.
     */
    public void findTask(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            System.out.println("Error: keyword is non existent.");
            return;
        }
        for (int i = 0; i < totalNumberOfTasks; i++) {
            if (isKeywordInside(taskList.get(i).getDescription(), keyword)) {
                printTask(i);
            }
        }
    }

    private boolean isKeywordInside(String description, String keyword) {
        return description.contains(keyword);
    }

}

