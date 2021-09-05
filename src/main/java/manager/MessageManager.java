package manager;

import task.Task;

public abstract class MessageManager {
    public static final String LINE = "    ____________________________________________________________\n";
    public static final String WELCOME_MESSAGE = " \n" +
            "                        .-\"\"\"-.\n" +
            "                       / .//\". \\\n" +
            "                       \\/ o o \\/\n" +
            "                       ( \\___/ )\n" +
            "          ________oooo__\\_____/_____________\n" +
            "         /                                  \\\n" +
            "        |         Hello! I'm Bobby :)        |\n" +
            "        |     What can I can do for you?     |\n" +
            "         \\______________________oooo________/\n" ;

    public static void printWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void printMessageTemplate(String message) {
        System.out.println(LINE + message + LINE);
    }

    public static void printTaskAddedMessage(Task task, int totalTasks) {
        String message = String.format("    Okay! I've added this task: \n       [%s][%s] %s\n" +
                "    Now you have %d tasks in your list.\n", task.getType(), task.getStatusIcon(), task.getFormattedDescription(), totalTasks);
        printMessageTemplate(message);
    }

    public static void printTaskList(int taskListIndex, Task[] taskList){
        String message = String.format("    Task List:\n");
        for (int i = 0; i < taskListIndex; i ++) {
           message = message + String.format("    %d. [%s][%s] %s\n",
                   i + 1, taskList[i].getType(), taskList[i].getStatusIcon(), taskList[i].getFormattedDescription());
        }
        printMessageTemplate(message);
    }

    public static void printTaskDoneMessage(Task task) {
        String message = String.format("    Alright! I've marked this task as done! :)\n    [%s] %s\n",
                task.getStatusIcon(), task.getFormattedDescription());
        printMessageTemplate(message);
    }

    public static void printTaskAlreadyDoneMessage() {
        String message = "    Task is already marked as done!\n";
        printMessageTemplate(message);
    }

    public static void printGoodByeMessage() {
        String message = "    Bye, my friend :( \n";
        printMessageTemplate(message);
    }

    public static void printInvalidCommandMessage() {
        String message = "    Invalid Command :( Please try again.\n";
        printMessageTemplate(message);
    }





}
