import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

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
    //not for adding the tasks when loaded into the system
    public void addTaskPlusException(TaskEnum taskType, String userInput) {
        try {
            String userInputWithoutTaskCommand = removeTaskCommand(taskType, userInput);
            addTask(taskType, userInputWithoutTaskCommand, false);

        } catch (BlankDescriptionException e) {
            //TODO differentiate tasks
            Ui.printlnTab("☹ OOPS!!! The description of a task cannot be empty.");
            Ui.printDivider();
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
            Storage.writeToFile();

        } catch (IncompleteInformationException e) {
            Ui.printlnTab("☹ OOPS!!! Please enter the right format for the task");
            //TODO Deadline and event formats
            Ui.printDivider();
        } catch (IOException e) {
            Ui.printlnTab("☹ OOPS!!! Error writing to data file");

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
        Ui.printlnTab("Got it. I've added this task:");
        Ui.printlnTab(" " + tasks.get(tasks.size() - 1)); //latest item
        printNumberOfTasksMessage();
        Ui.printDivider();
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

        String taskToStringOutput = String.format(" %s", tasks.get(taskIndex));

        tasks.remove(taskIndex);

        Ui.printlnTab("Noted. I've removed this task:");
        Ui.printlnTab(taskToStringOutput);

        printNumberOfTasksMessage();
        Ui.printDivider();
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
        Ui.printlnTab("Nice! I've marked this task as done:");
        Ui.printlnTab(String.format("%s", tasks.get(taskIndex)));
        Ui.printDivider();
    }

    public void doneOrDeleteTaskPlusException(String userInput, String COMMAND) {
        try {
            if (COMMAND.equals(Parser.COMMAND_DONE)) {
                doneTask(userInput);
            } else {
                deleteTask(userInput);
            }
            Storage.writeToFile();

        } catch (BlankDescriptionException e) {
            Ui.printlnTab("☹ OOPS!!! Please enter a task number to complete");
            Ui.printDivider();

        } catch (NumberFormatException e) {
            Ui.printlnTab("☹ OOPS!!! Task number is not an integer.");
            Ui.printDivider();

        } catch (ExceedTotalTasksException e) {
            Ui.printlnTab("☹ OOPS!!! You only have " + tasks.size() + " tasks");
            Ui.printlnTab("Please enter a number smaller or equal to " + tasks.size());
            Ui.printDivider();

        } catch (IndexOutOfBoundsException e) {
            Ui.printlnTab("☹ OOPS!!! Please enter a task number greater than or equal to 1.");
            Ui.printDivider();

        } catch (IOException e) {
            //TODO File errors?
            Ui.printlnTab("IOException Error");
            Ui.printDivider();
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            Ui.printlnTab("Your task list is empty!");

        } else {
            Ui.printlnTab("Here are the tasks in your list:");

            //listing out tasks if userInput == "list"
            //tasks[] start with index 0
            //but printing out tasks starts with index 1
            for (int i = 0; i < tasks.size(); i++) {
                Ui.printlnTab(String.format("%d.%s", (i + 1), tasks.get(i)));
            }
        }
        Ui.printDivider();
    }

    private void printNumberOfTasksMessage() {
        if (tasks.size() == 1) {
            Ui.printlnTab("Now you have 1 task in the list.");
        } else {
            Ui.printlnTab(String.format("Now you have %d tasks in the list.", tasks.size()));
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

