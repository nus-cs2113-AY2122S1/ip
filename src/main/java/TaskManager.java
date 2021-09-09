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
            String strippedUserInput = stripUserInput(taskType, userInput);
            addTask(taskType, strippedUserInput);

        } catch (BlankDescriptionException e) {
            Duke.printlnTab("☹ OOPS!!! The description of a todo cannot be empty.");
            Duke.printDivider();

        } catch (ExceedMaxTasksException e) {
            Duke.printlnTab("You have already added the maximum of 100 tasks! You can't add anymore tasks.");
            Duke.printDivider();
        }
    }

    private String stripUserInput(TaskEnum taskType, String userInput) throws BlankDescriptionException {
        String strippedUserInput = "";

        switch (taskType) {
        case TODO:
            strippedUserInput = userInput.substring(TODO_START_INDEX).stripLeading(); // remove "todo" from userInput
            break;
        case DEADLINE:
            strippedUserInput = userInput.substring(DEADLINE_START_INDEX).stripLeading(); // strip "deadline" from userInput
            break;
        case EVENT:
            strippedUserInput = userInput.substring(EVENT_START_INDEX).stripLeading(); // strip event
            break;
        }

        if (strippedUserInput.isEmpty()) {
            throw new BlankDescriptionException();
        }
        return strippedUserInput;
    }

    private void addTask(TaskEnum taskType, String strippedUserInput) throws ExceedMaxTasksException {
        if (getTotalTasks() == 100) {
            throw new ExceedMaxTasksException();
        }

        try {
            String[] taskDetails; //array that should be of length 2 if strippedUserInput is valid
            switch (taskType) {
            case TODO:
                addTodo(strippedUserInput);
                break;
            case DEADLINE:
                taskDetails = getTaskDetails(TaskEnum.DEADLINE, strippedUserInput);
                addDeadline(taskDetails);
                break;
            case EVENT:
                taskDetails = getTaskDetails(TaskEnum.EVENT, strippedUserInput);
                addEvent(taskDetails);
            }

        } catch (IncompleteInformationException e) {
            Duke.printlnTab("☹ OOPS!!! Please enter the right format for the task");
            //TODO Deadline and event formats
            Duke.printDivider();
        }
    }

    private String[] getTaskDetails(TaskEnum taskType, String strippedUserInput) throws IncompleteInformationException {
        String[] taskDetails;
        if (taskType == TaskEnum.DEADLINE) {
            taskDetails = strippedUserInput.split("/by");
        } else { //if EVENT
            taskDetails = strippedUserInput.split("/at");
        }

        // taskDetails[] should have length of 2
        //containing Task description (index 0) and Task date (index 1)
        if (taskDetails.length != 2) {
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
        if (tasksIndex == 0) {
            Duke.printlnTab("Now you have 1 task");

        } else {
            Duke.printlnTab(String.format("Now you have %d tasks", tasksIndex + 1));
        }
        Duke.printDivider();
        tasksIndex++;
    }

    void addTaskFail() {
        Duke.printlnTab("Input is invalid. Please try again");
        //TODO help function
    }


    void doneTask(String userInput) throws NumberFormatException, IndexOutOfBoundsException, NullPointerException {
        String taskNumberStr = userInput.substring(DONE_NUMBER_INDEX).strip();
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
        } catch (NumberFormatException e) {
            Duke.printlnTab("Task number is not an integer. Please try again!");
            Duke.printDivider();

        } catch (IndexOutOfBoundsException e) {
            Duke.printlnTab("Task number is out of bounds. Please try again!");
            Duke.printDivider();

        } catch (NullPointerException e) {
            Duke.printlnTab("You only have " + getTotalTasks() + " tasks");
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
        return tasksIndex + 1;
    }

}
