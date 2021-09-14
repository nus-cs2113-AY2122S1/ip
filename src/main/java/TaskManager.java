import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    //These indexes are the start index of the string which has
    //the unnecessary part removed
    private static final int TODO_START_INDEX = 4;
    private static final int DEADLINE_START_INDEX = 8;
    private static final int EVENT_START_INDEX = 5;
    private static final int DELETE_NUMBER_INDEX = 6;
    private static final int DONE_NUMBER_INDEX = 4;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DATE_INDEX = 1;

    private static final ArrayList<Task> tasks = new ArrayList<>();

    //only for adding tasks when program is already runnning
    //not for adding the tasks when loaded into the syste
    public void addTaskPlusException(TaskEnum taskType, String userInput) {
        try {
            String userInputWithoutTaskCommand = removeTaskCommand(taskType, userInput);
            addTask(taskType, userInputWithoutTaskCommand, false);

        } catch (BlankDescriptionException e) {
            //TODO differentiate tasks
            Duke.printlnTab("☹ OOPS!!! The description of a task cannot be empty.");
            Duke.printDivider();
        }
    }

    //command keyword removed eg. "todo clean room"  -> "clean room"
    private String removeTaskCommand(TaskEnum taskType, String userInput) throws BlankDescriptionException {
        String strippedUserInput = "";

        switch (taskType) {
        case TODO:
            strippedUserInput = userInput.substring(TODO_START_INDEX).strip(); // remove "todo" from userInput
            break;
        case DEADLINE:
            strippedUserInput = userInput.substring(DEADLINE_START_INDEX).strip(); // remove "deadline" from userInput
            break;
        case EVENT:
            strippedUserInput = userInput.substring(EVENT_START_INDEX).strip(); // remove event
            break;
        }

        if (strippedUserInput.isEmpty()) {
            throw new BlankDescriptionException();
        }
        return strippedUserInput;
    }

    private void addTask(TaskEnum taskType, String userInputWithoutTaskCommand, boolean isDone) {

        try {
            String[] taskDetails; //array that should be of length 2 if strippedUserInput is valid
            switch (taskType) {
            case TODO:
                addTodo(userInputWithoutTaskCommand, isDone);
                break;
            case DEADLINE:
                taskDetails = getTaskDetails(TaskEnum.DEADLINE, userInputWithoutTaskCommand);
                addDeadline(taskDetails, isDone);
                break;
            case EVENT:
                taskDetails = getTaskDetails(TaskEnum.EVENT, userInputWithoutTaskCommand);
                addEvent(taskDetails, isDone);
            }
            addTaskSuccess();
            FileManager.writeToFile();

        } catch (IncompleteInformationException e) {
            Duke.printlnTab("☹ OOPS!!! Please enter the right format for the task");
            //TODO Deadline and event formats
            Duke.printDivider();
        } catch (IOException e) {
            Duke.printlnTab("☹ OOPS!!! Error writing to data file");

        }
    }

    private String[] getTaskDetails(TaskEnum taskType, String userInputWithoutTaskCommand) throws IncompleteInformationException {
        String[] taskDetails;
        //strip userInputWithoutTaskCommand prevents empty description / dates
        if (taskType == TaskEnum.DEADLINE) {
            taskDetails = userInputWithoutTaskCommand.strip().split("/by");
        } else { //if EVENT
            taskDetails = userInputWithoutTaskCommand.strip().split("/at");
        }

        // taskDetails[] should have length of 2
        //containing Task description (index 0) and Task date (index 1)
        //special case of length 2 when "/by timing" which is still invalid
        //is checked by .isBlank()
        if (taskDetails.length != 2
                || taskDetails[TASK_DESCRIPTION_INDEX].isBlank()
                || taskDetails[TASK_DATE_INDEX].isBlank()) {
            throw new IncompleteInformationException();
        }

        for (int i = 0; i < 2; i++) {
            taskDetails[i] = taskDetails[i].strip();
        }
        return taskDetails;
    }

    public void addTodo(String description, boolean isDone) {
        tasks.add(new Todo(description, isDone));
    }

    public void addDeadline(String[] taskDetails, boolean isDone) {
        tasks.add(new Deadline(taskDetails[TASK_DESCRIPTION_INDEX], isDone, taskDetails[TASK_DATE_INDEX]));
    }

    public void addEvent(String[] taskDetails, boolean isDone) {
        tasks.add(new Event(taskDetails[TASK_DESCRIPTION_INDEX], isDone, taskDetails[TASK_DATE_INDEX]));
    }

    private void addTaskSuccess() {
        Duke.printlnTab("Got it. I've added this task:");
        Duke.printlnTab(" " + tasks.get(tasks.size() - 1)); //latest item
        printNumberOfTasksMessage();
        Duke.printDivider();
    }

    public void deleteTask(String userInput) throws BlankDescriptionException, ExceedTotalTasksException {
        String taskNumberStr = userInput.substring(DELETE_NUMBER_INDEX).strip();
        if (taskNumberStr.isBlank()) {
            throw new BlankDescriptionException();
        }

        //taskNumber displayed starting with 1
        //but array starts with 0
        int taskNumber = Integer.parseInt(taskNumberStr);
        if (taskNumber > tasks.size()) {
            throw new ExceedTotalTasksException();
        }

        int taskIndex = taskNumber - 1;

        Duke.printlnTab("Noted. I've removed this task:");
        Duke.printlnTab(String.format(" %s", tasks.get(taskIndex)));

        tasks.remove(taskIndex);

        printNumberOfTasksMessage();
        Duke.printDivider();
    }

    private void doneTask(String userInput) throws BlankDescriptionException, ExceedTotalTasksException {
        String taskNumberStr = userInput.substring(DONE_NUMBER_INDEX).strip();
        if (taskNumberStr.isBlank()) {
            throw new BlankDescriptionException();
        }
        //taskNumber displayed starting with 1
        //but array starts with 0

        int taskNumber = Integer.parseInt(taskNumberStr);
        if (taskNumber > tasks.size()) {
            throw new ExceedTotalTasksException();
        }
        int taskIndex = taskNumber - 1;

        tasks.get(taskIndex).markAsDone();
        Duke.printlnTab("Nice! I've marked this task as done:");
        Duke.printlnTab(String.format("%s", tasks.get(taskIndex)));
        Duke.printDivider();
    }

    public void doneOrDeleteTaskPlusException(String userInput, String COMMAND) {
        try {
            if (COMMAND.equals(CommandManager.COMMAND_DONE)) {
                doneTask(userInput);
            } else {
                deleteTask(userInput);
            }
            FileManager.writeToFile();

        } catch (BlankDescriptionException e) {
            Duke.printlnTab("☹ OOPS!!! Please enter a task number to complete");
            Duke.printDivider();

        } catch (NumberFormatException e) {
            Duke.printlnTab("☹ OOPS!!! Task number is not an integer. Please try again!");
            Duke.printDivider();

        } catch (ExceedTotalTasksException e) {
            Duke.printlnTab("☹ OOPS!!! You only have " + tasks.size() + " tasks");
            Duke.printlnTab("Please enter a number smaller or equal to " + tasks.size());
            Duke.printDivider();

        } catch (IOException e) {
            //TODO File errors?
            Duke.printlnTab("IOException Error");
            Duke.printDivider();
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            Duke.printlnTab("Your task list is empty!");

        } else {
            Duke.printlnTab("Here are the tasks in your list:");

            //listing out tasks if userInput == "list"
            //tasks[] start with index 0
            //but printing out tasks starts with index 1
            for (int i = 0; i < tasks.size(); i++) {
                Duke.printlnTab(String.format("%d.%s", (i + 1), tasks.get(i)));
            }
        }
        Duke.printDivider();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    private void printNumberOfTasksMessage() {
        if (tasks.size() == 1) {
            Duke.printlnTab("Now you have 1 task in the list.");
        } else {
            Duke.printlnTab(String.format("Now you have %d tasks in the list.", tasks.size()));
        }
    }

    public String getTasksDataStorageString() {
        String tasksDataStorageString = "";
        for (Task task : tasks) {
            tasksDataStorageString += task.getDataStorageString() + "\n";
        }
        return tasksDataStorageString;
    }
}

