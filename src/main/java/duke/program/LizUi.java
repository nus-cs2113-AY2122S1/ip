package duke.program;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class LizUi {

    private static final int LINE_LENGTH = 40;

    private static final String TASK_TYPE_ICON_TODO = "T";
    private static final String TASK_TYPE_ICON_DEADLINE = "D";
    private static final String TASK_TYPE_ICON_EVENT = "E";

    private static final String COMMAND_DEADLINE = "deadline";
    private static final DateTimeFormatter DATE_TIME_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private static final String LIZTEXT = "      ___                   ___           ___           ___     \n" +
            "     /\\__\\      ___        /\\  \\         /\\  \\         |\\__\\    \n" +
            "    /:/  /     /\\  \\       \\:\\  \\        \\:\\  \\        |:|  |   \n" +
            "   /:/  /      \\:\\  \\       \\:\\  \\        \\:\\  \\       |:|  |   \n" +
            "  /:/  /       /::\\__\\       \\:\\  \\        \\:\\  \\      |:|__|__ \n" +
            " /:/__/     __/:/\\/__/ _______\\:\\__\\ _______\\:\\__\\     /::::\\__\\\n" +
            " \\:\\  \\    /\\/:/  /    \\::::::::/__/ \\::::::::/__/    /:/~~/~   \n" +
            "  \\:\\  \\   \\::/__/      \\:\\~~\\~~      \\:\\~~\\~~       /:/  /     \n" +
            "   \\:\\  \\   \\:\\__\\       \\:\\  \\        \\:\\  \\        \\/__/      \n" +
            "    \\:\\__\\   \\/__/        \\:\\__\\        \\:\\__\\                  \n" +
            "     \\/__/                 \\/__/         \\/__/                  \n";

    private static final String LIZLOGO = "                      ____...---...___\n" +
            "___.....---\"\"\"                .                   \"\"--..____\n" +
            "     .                  .            .\n" +
            " .             _.--._       /|\n" +
            "        .    .'()..()`.    / /\n" +
            "            ( `-.__.-' )  ( (    .\n" +
            "   .         \\        /    \\ \\\n" +
            "       .      \\      /      ) )        .\n" +
            "            .' -.__.- `.-.-'_.'\n" +
            " .        .'  /-____-\\  `.-'       .\n" +
            "          \\  /-.____.-\\  /-.\n" +
            "           \\ \\`-.__.-'/ /\\|\\|           .\n" +
            "          .'  `.    .'  `.\n" +
            "          |/\\/\\|    |/\\/\\|";

    public static void printLine() {
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void printGreetingMessage() {
        System.out.println("Howdy! It's\n" + LIZTEXT + LIZLOGO);
        printLine();
        System.out.println("Hey! I'm Lizzy the Lizard!");
        System.out.println("What can I do for you?");
        System.out.println("");
    }

    public static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String readCommand(Scanner in) {
        return in.nextLine();
    }

    public static void printTaskList(int taskCount, ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {

            String taskType = tasks.get(i).getType();
            String taskStatus = tasks.get(i).getStatusIcon();
            String taskDescription = tasks.get(i).getDescription();

            System.out.printf("%d.", i+1);
            printIndividualTask(tasks.get(i), taskType, taskStatus, taskDescription);
        }
    }

    private static void printIndividualTask(Task task, String taskType, String taskStatus, String taskDescription) {
        if (taskType.equals(TASK_TYPE_ICON_TODO)) {
            System.out.printf("[%s][%s] %s%n", taskType, taskStatus, taskDescription);
        } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
            LocalDateTime taskByTime = task.getByDateTime();
            String taskByTimeString = DATE_TIME_FORMAT_OUTPUT.format(taskByTime);
            System.out.printf("[%s][%s] %s (by: %s)%n", taskType, taskStatus, taskDescription, taskByTimeString);
        } else if (taskType.equals(TASK_TYPE_ICON_EVENT)) {
            LocalDateTime taskStartTime = task.getStartTime();
            LocalDateTime taskEndTime = task.getEndTime();
            String taskStartTimeString = DATE_TIME_FORMAT_OUTPUT.format(taskStartTime);
            String taskEndTimeString = DATE_TIME_FORMAT_OUTPUT.format(taskEndTime);
            System.out.printf("[%s][%s] %s (at: %s to %s)%n", taskType, taskStatus,
                    taskDescription, taskStartTimeString, taskEndTimeString);
        } else {
            printGenericErrorMessage();
        }
    }

    public void printMarkAsDoneMessage(Task task) {
        String taskType = task.getType();
        String taskStatus = task.getStatusIcon();
        String taskDescription = task.getDescription();

        System.out.println("Nice! I've marked this task as done:");
        printIndividualTask(task, taskType, taskStatus, taskDescription);
    }

    public void printDeletedTaskMessage(Task task, int taskCount) {
        String taskType = task.getType();
        String taskStatus = task.getStatusIcon();
        String taskDescription = task.getDescription();

        System.out.println("Noted. I've removed this task:");
        printIndividualTask(task, taskType, taskStatus, taskDescription);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void printAddedTaskMessage(Task task, int taskCount) {
        String taskType = task.getType();
        String taskDescription = task.getDescription();

        System.out.println("Got it. I've added this task:");
        if (taskType.equals(TASK_TYPE_ICON_TODO)) {
            System.out.printf("[%s][ ] %s%n", taskType, taskDescription);
        } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
            LocalDateTime byTime = task.getByDateTime();
            String byTimeString = DATE_TIME_FORMAT_OUTPUT.format(byTime);
            System.out.printf("[%s][ ] %s (by: %s)%n", taskType, taskDescription, byTimeString);
        } else if (taskType.equals((TASK_TYPE_ICON_EVENT))) {
            LocalDateTime startTime = task.getStartTime();
            LocalDateTime endTime = task.getEndTime();
            String startTimeString = DATE_TIME_FORMAT_OUTPUT.format(startTime);
            String endTimeString = DATE_TIME_FORMAT_OUTPUT.format(endTime);
            System.out.printf("[%s][ ] %s (at: %s to %s)%n", taskType, taskDescription,
                    startTimeString, endTimeString);
        } else {
            printGenericErrorMessage();
        }

        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    ///////////////////////////////
    //ALL ERROR MESSAGES GO BELOW//
    ///////////////////////////////

    public static void printFileErrorMessage() {
        System.out.println("Hey bud, something went wrong with the file :/");
    }

    public static void printIndexOutOfBoundsMessage() {
        System.out.println("Sorry bud, you can't check off/delete what is not yet there :/");
    }

    public static void printInvalidDoneOrDeleteMessage() {
        System.out.println("Sorry bud, that's not a valid task number to check off/delete!");
    }

    public static void printAlreadyDoneMessage() {
        System.out.println("Slow down there bud! You've already completed this task!");
    }

    public static void printEmptyIndexAfterDoneMessage() {
        System.out.println("Hey bud, the format for marking off a task is :done [index]");
    }

    public static void printEmptyIndexAfterDeleteMessage() {
        System.out.println("Hey bud, the format for marking off a task is :delete [index]");
    }

    public static void printEmptyDescriptionMessage(String command) {
        System.out.println("Sorry bud! The description after " + command + " can't be blank!");
    }

    public static void printInvalidCommandMessage() {
        System.out.println("Sorry bud, but that command is gibberish to me. I can only read 7 words!");
        System.out.println("The seven words are:");
        System.out.printf("list%ndone%ndelete%ntodo%ndeadline%nevent%nbye%n");
    }

    public static void printInvalidFormatMessage(String command) {
        String timeIndicatorCommand = (command.equals(COMMAND_DEADLINE)) ? "by" : "at";
        System.out.println("Sorry bud, but your formatting is gibberish to me. Here is how it should be formatted: ");
        System.out.println(command + " {description} /" + timeIndicatorCommand + " {timing}");
    }

    public static void printGenericErrorMessage() {
        System.out.println("Oops! Something went wrong :(");
    }

    public void printInvalidDateTimeFormatMessage() {
        System.out.println("Sorry bud, but that's an invalid date-time format! It should be as follows: " +
                System.lineSeparator() + "/by dd/mm/yyyy HHmm (for deadlines)" + System.lineSeparator() +
                "/at dd/mm/yyyy HHmm to dd/mm/yyyy HHmm (for events)");
    }
}
