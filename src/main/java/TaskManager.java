public class TaskManager {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int tasksIndex = 0; //index of task in tasks array

    //add other finals, magic numbers here
    //


    void listTasks() {
        Duke.printlnTab("Here are the tasks in your list:");

        //listing out tasks if userInput == "list"
        for (int i = 0; i < tasksIndex; i++) {
            Duke.printlnTab(String.format("%d.%s", (i + 1), tasks[i]));
        }
    }


    void addTask(TaskEnum taskType, String userInput) {
        switch (taskType) {
        case TODO:
            tasks[tasksIndex] = new Todo(userInput);
            addTaskSuccess();
            return;
        // break;
        case DEADLINE:
            if (userInput.contains("/b")) {
                userInput = userInput.substring(8).stripLeading(); // strip deadline
                String[] deadlineDetails = userInput.split("/by");

                if (deadlineDetails.length == 2) {
                    String description = deadlineDetails[0].strip();
                    String by = deadlineDetails[1].strip();
                    tasks[tasksIndex] = new Deadline(description, by);
                    addTaskSuccess();
                    return;
                    // break;
                }
            }
            break;
        case EVENT:
            if (userInput.contains("/a")) {
                userInput = userInput.substring(5).stripLeading(); // strip deadline
                String[] eventDetails = userInput.split("/at");

                if (eventDetails.length == 2) {
                    String description = eventDetails[0].strip();
                    String at = eventDetails[1].strip();
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
    }


    void doneTask(String userInput) {
        String taskNumberStr = userInput.substring(5);
        int taskNumber = Integer.parseInt(taskNumberStr);
        (tasks[taskNumber - 1]).markAsDone();
        //taskNumber displayed starting with 1
        //but array starts with 0

        Duke.printlnTab("Nice! I've marked this task as done:");
        Duke.printlnTab(String.format("%s", tasks[taskNumber - 1]));
        Duke.printDivider();
    }


}
