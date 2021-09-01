public class TaskManager {
    private static final int MAX_TASKS = 100;
    private static final int TODO_START_INDEX = 4;
    private static final int DEADLINE_START_INDEX = 8;
    private static final int EVENT_START_INDEX = 5;
    private static final int TASK_DESCRIPTION_INDEX = 0;
    private static final int TASK_DATE_INDEX = 1;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int tasksIndex = 0; //index of task in tasks array

    void listTasks() {
        Duke.printlnTab("Here are the tasks in your list:");

        //listing out tasks if userInput == "list"
        for (int i = 0; i < tasksIndex; i++) {
            Duke.printlnTab(String.format("%d.%s", (i + 1), tasks[i]));
        }
    }


    void addTask(TaskEnum taskType, String userInput) {
        String strippedUserInput; // userInput but without the first task keyword "todo" "event" "deadline

        switch (taskType) {
        case TODO:
            strippedUserInput = userInput.substring(TODO_START_INDEX).stripLeading(); // remove "todo" from userInput
            tasks[tasksIndex] = new Todo(strippedUserInput);
            addTaskSuccess();
            return;
        case DEADLINE:
            if (userInput.contains("/b")) {
                strippedUserInput = userInput.substring(DEADLINE_START_INDEX).stripLeading(); // strip "deadline" from userInput

                // array should have length of 2
                //containing Task description (index 0) and Task date (index 1)
                String[] deadlineDetails = strippedUserInput.split("/by");

                if (deadlineDetails.length == 2) {
                    String description = deadlineDetails[TASK_DESCRIPTION_INDEX].strip();
                    String by = deadlineDetails[TASK_DATE_INDEX].strip();
                    tasks[tasksIndex] = new Deadline(description, by);
                    addTaskSuccess();
                    return;
                }
            }
            break;
        case EVENT:
            if (userInput.contains("/a")) {
                strippedUserInput = userInput.substring(EVENT_START_INDEX).stripLeading(); // strip event
                String[] eventDetails = strippedUserInput.split("/at");

                if (eventDetails.length == 2) {
                    String description = eventDetails[TASK_DESCRIPTION_INDEX].strip();
                    String at = eventDetails[TASK_DATE_INDEX].strip();
                    tasks[tasksIndex] = new Event(description, at);
                    addTaskSuccess();
                    return;
                }
            }
        }
        addTaskFail();
    }

    void addTaskSuccess() {
        Duke.printlnTab("Got it. I've added this task:");
        Duke.printlnTab(" " + tasks[tasksIndex]);
        Duke.printlnTab(String.format("Now you have %d tasks", tasksIndex + 1)); // need case for odd
        Duke.printDivider();
        tasksIndex++;
    }

    void addTaskFail() {
        Duke.printlnTab("Input is invalid. Please try again");
        //TODO help function
    }


    void doneTask(String userInput) {
        String taskNumberStr = userInput.substring(5);
        int taskNumber = Integer.parseInt(taskNumberStr);
        int taskIndex = taskNumber - 1;
        (tasks[taskIndex]).markAsDone();
        //taskNumber displayed starting with 1
        //but array starts with 0
        // Need an exception here
        // TODO taskNumber greater than the actual tasknumber currently or less than 0;
        // TODO or if fail to parse taskNumber


        Duke.printlnTab("Nice! I've marked this task as done:");
        Duke.printlnTab(String.format("%s", tasks[taskIndex]));
        Duke.printDivider();
    }


}
