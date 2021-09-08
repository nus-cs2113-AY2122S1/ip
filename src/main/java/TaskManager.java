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

    public int getTotalTasks() {
        return tasksIndex + 1;
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

    private void addTask(TaskEnum taskType, String userInput) throws ExceedMaxTasksException {
        if (getTotalTasks() == 100) {
            throw new ExceedMaxTasksException();
        }

        switch (taskType) {
        case TODO:
            addTodoPlusException(userInput);
            break;
        case DEADLINE:
            addDeadlinePlusException(userInput);
            break;
        case EVENT:
            addEventPlusException(userInput);
        }
    }

    public void addTaskPlusException(TaskEnum taskType, String userInput) {
        try {
            addTask(taskType, userInput);
        } catch (ExceedMaxTasksException e) {
            Duke.printlnTab("You have already added the maximum of 100 tasks! You can't add anymore tasks.");
            Duke.printDivider();
        }
    }

    private void addTodo(String userInput) throws BlankDescriptionException {
        String strippedUserInput = userInput.substring(TODO_START_INDEX).stripLeading(); // remove "todo" from userInput
        if (strippedUserInput.isEmpty()) {
            throw new BlankDescriptionException();
        }

        tasks[tasksIndex] = new Todo(strippedUserInput);
        addTaskSuccess();
    }

    private void addDeadline(String userInput) throws BlankDescriptionException, IncompleteInformationException {
        String strippedUserInput = userInput.substring(DEADLINE_START_INDEX).stripLeading(); // strip "deadline" from userInput

        if (strippedUserInput.isEmpty()) {
            throw new BlankDescriptionException();
        }

        String[] deadlineDetails = strippedUserInput.split("/by");

        // array should have length of 2
        //containing Task description (index 0) and Task date (index 1)

        if (deadlineDetails.length != 2) {
            throw new IncompleteInformationException();
        }

        String description = deadlineDetails[TASK_DESCRIPTION_INDEX].strip();
        String by = deadlineDetails[TASK_DATE_INDEX].strip();
        tasks[tasksIndex] = new Deadline(description, by);
        addTaskSuccess();
    }


    private void addEvent(String userInput) throws BlankDescriptionException, IncompleteInformationException {

        String strippedUserInput = userInput.substring(EVENT_START_INDEX).stripLeading(); // strip event
        if (strippedUserInput.isEmpty()) {
            throw new BlankDescriptionException();
        }

        String[] eventDetails = strippedUserInput.split("/at");
        if (eventDetails.length != 2) {
            throw new IncompleteInformationException();
        }

        // array should have length of 2
        //containing Task description (index 0) and Task date (index 1)
        String description = eventDetails[TASK_DESCRIPTION_INDEX].strip();
        String at = eventDetails[TASK_DATE_INDEX].strip();
        tasks[tasksIndex] = new Event(description, at);
        addTaskSuccess();

    }

    public void addTodoPlusException(String userInput) {
        try {
            addTodo(userInput);

        } catch (BlankDescriptionException e) {
            Duke.printlnTab("☹ OOPS!!! The description of a todo cannot be empty.");
            Duke.printDivider();
        }
    }

    public void addDeadlinePlusException(String userInput) {
        try {
            addDeadline(userInput);

        } catch (BlankDescriptionException e) {
            Duke.printlnTab("☹ OOPS!!! The description of a deadline cannot be empty.");
            Duke.printDivider();

        } catch (IncompleteInformationException e) {
            Duke.printlnTab("☹ OOPS!!! Please enter in the right format for deadline");
            Duke.printDivider();

        }
    }

    public void addEventPlusException(String userInput) {
        try {
            addEvent(userInput);

        } catch (BlankDescriptionException e) {
            Duke.printlnTab("☹ OOPS!!! The description of an event cannot be empty.");
            Duke.printDivider();

        } catch (IncompleteInformationException e) {
            Duke.printlnTab("☹ OOPS!!! Please enter in the right format for event");
            Duke.printDivider();
        }

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

    void doneTaskPlusException(String userInput) throws NumberFormatException, IndexOutOfBoundsException, NullPointerException {
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
}
