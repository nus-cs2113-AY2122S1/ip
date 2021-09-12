public class TaskManager {
    private static final int MAX_TASKS = 100;

    //These indexes are the start index of the string which has
    //the unnecessary part removed
    private static final int TODO_START_INDEX = 4;
    private static final int DEADLINE_START_INDEX = 8;
    private static final int EVENT_START_INDEX = 5;
    private static final int DONE_NUMBER_INDEX = 4;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DATE_INDEX = 1;

    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int tasksIndex = 0; //index of task in tasks array

    public void addTaskPlusException(TaskEnum taskType, String userInput) {
        try {
            String userInputWithoutTaskCommand = removeTaskCommand(taskType, userInput);
            addTask(taskType, userInputWithoutTaskCommand);

        } catch (BlankDescriptionException e) {
            //TODO differnetiate tasks
            Duke.printlnTab("☹ OOPS!!! The description of a task cannot be empty.");
            Duke.printDivider();

        } catch (ExceedMaxTasksException e) {
            Duke.printlnTab("☹ OOPS!!! You have already added the maximum of 100 tasks! You can't add anymore tasks.");
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

    private void addTask(TaskEnum taskType, String userInputWithoutTaskCommand) throws ExceedMaxTasksException {
        if (getTotalTasks() == 100) {
            throw new ExceedMaxTasksException();
        }

        try {
            String[] taskDetails; //array that should be of length 2 if strippedUserInput is valid
            switch (taskType) {
            case TODO:
                addTodo(userInputWithoutTaskCommand);
                break;
            case DEADLINE:
                taskDetails = getTaskDetails(TaskEnum.DEADLINE, userInputWithoutTaskCommand);
                addDeadline(taskDetails);
                break;
            case EVENT:
                taskDetails = getTaskDetails(TaskEnum.EVENT, userInputWithoutTaskCommand);
                addEvent(taskDetails);
            }

        } catch (IncompleteInformationException e) {
            Duke.printlnTab("☹ OOPS!!! Please enter the right format for the task");
            //TODO Deadline and event formats
            Duke.printDivider();
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

    private void addTodo(String strippedUserInput) {
        tasks[tasksIndex] = new Todo(strippedUserInput);
        addTaskSuccess();
    }

    private void addDeadline(String[] taskDetails) {
        tasks[tasksIndex] = new Deadline(taskDetails[TASK_DESCRIPTION_INDEX], taskDetails[TASK_DATE_INDEX]);
        addTaskSuccess();
    }

    private void addEvent(String[] taskDetails) {
        tasks[tasksIndex] = new Event(taskDetails[TASK_DESCRIPTION_INDEX], taskDetails[TASK_DATE_INDEX]);
        addTaskSuccess();
    }

    void addTaskSuccess() {
        Duke.printlnTab("Got it. I've added this task:");
        Duke.printlnTab(" " + tasks[tasksIndex]);
        tasksIndex++;
        if (tasksIndex == 1) {
            Duke.printlnTab("Now you have 1 task");

        } else {
            Duke.printlnTab(String.format("Now you have %d tasks", tasksIndex));
        }
        Duke.printDivider();
    }

    void doneTask(String userInput) throws BlankDescriptionException, IndexOutOfBoundsException, NullPointerException {
        String taskNumberStr = userInput.substring(DONE_NUMBER_INDEX).strip();
        if (taskNumberStr.isBlank()) {
            throw new BlankDescriptionException();
        }
        //taskNumber displayed starting with 1
        //but array starts with 0

        int taskNumber = Integer.parseInt(taskNumberStr);
        int taskIndex = taskNumber - 1;

        (tasks[taskIndex]).markAsDone();
        Duke.printlnTab("Nice! I've marked this task as done:");
        Duke.printlnTab(String.format("%s", tasks[taskIndex]));
        Duke.printDivider();
    }

    void doneTaskPlusException(String userInput) {
        try {
            doneTask(userInput);

        } catch (BlankDescriptionException e) {
            Duke.printlnTab("☹ OOPS!!! Please enter a task number to complete");
            Duke.printDivider();

        } catch (NumberFormatException e) {
            Duke.printlnTab("☹ OOPS!!! Task number is not an integer. Please try again!");
            Duke.printDivider();

        } catch (IndexOutOfBoundsException e) {
            Duke.printlnTab("☹ OOPS!!! Task number is out of bounds. Please try again!");
            Duke.printDivider();

        } catch (NullPointerException e) {
            Duke.printlnTab("☹ OOPS!!! You only have " + getTotalTasks() + " tasks");
            Duke.printlnTab("Please enter a number smaller or equal to " + getTotalTasks());
            Duke.printDivider();

        }
    }

    void listTasks() {
        if (tasksIndex == 0) {
            Duke.printlnTab("Your task list is empty!");

        } else {
            Duke.printlnTab("Here are the tasks in your list:");

            //listing out tasks if userInput == "list"
            //tasks[] start with index 0
            //but printing out tasks starts with index 1
            for (int i = 0; i < tasksIndex; i++) {
                Duke.printlnTab(String.format("%d.%s", (i + 1), tasks[i]));
            }
        }
        Duke.printDivider();
    }

    public int getTotalTasks() {
        return tasksIndex;
    }


    public void addTodoFromFile(String details, boolean isDone) {
        tasks[tasksIndex] = new Todo(details);
        tasks[tasksIndex].isDone = isDone;
        tasksIndex++;
    }

    public void addDeadlineFromFile(String details, String date, boolean isDone) {
        tasks[tasksIndex] = new Deadline(details, date);
        tasks[tasksIndex].isDone = isDone;
        tasksIndex++;
    }

    public void addEventFromFile(String details, String date, boolean isDone) {
        tasks[tasksIndex] = new Event(details, date);
        tasks[tasksIndex].isDone = isDone;
        tasksIndex++;
    }

}
