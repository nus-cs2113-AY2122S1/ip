package duke.message;

import duke.Duke;

public abstract class OutputMessage {
    public static final String LOGO = "    ____        _        \n"
            + "   |  _ \\ _   _| | _____ \n"
            + "   | | | | | | | |/ / _ \\\n"
            + "   | |_| | |_| |   <  __/\n"
            + "   |____/ \\__,_|_|\\_\\___|\n";

    public static final String BORDER_LINE = "------------------------------------------------";
    
    public static void printGreetingMessage() {
        System.out.println(LOGO + System.lineSeparator()
                + BORDER_LINE + System.lineSeparator()
                + "    Hello!, I'm Duke" + System.lineSeparator()
                + "    How can I help you?" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Bye, see you again!" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printFileNotDetectedMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    NO EXISTING FILE DETECTED" + System.lineSeparator()
                + "    EMPTY TASK LIST INITIALIZED" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidAddTaskMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK DESCRIPTION PROVIDED" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidTaskNotExistedMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID TASK NUMBER PROVIDED" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printTaskList() {
        System.out.println(BORDER_LINE);
        if (Duke.tasks.size() == 0) {
            System.out.println("    The list is currently empty!");
        } else {
            for (int i = 0; i < Duke.tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + Duke.tasks.get(i));
            }
        }
        System.out.println(BORDER_LINE);
    }

    public static void printTaskAlreadyDoneMessage(int taskNumber) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task " + (taskNumber + 1) + " has already been marked as done!" + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printMarkDoneMessage(int taskNumber) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is now marked as done:" + System.lineSeparator()
                + "      " + Duke.tasks.get(taskNumber) + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printAddTaskMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    Task added: " + System.lineSeparator()
                + "      " + Duke.tasks.get(Duke.tasks.size() - 1) + System.lineSeparator()
                + "    You have " + Duke.tasks.size() + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printDeleteTaskMessage(int taskNumber) {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    The following task is removed from the list: " + System.lineSeparator()
                + "      " + Duke.tasks.get(taskNumber) + System.lineSeparator()
                + "    You now have " + (Duke.tasks.size() - 1) + " tasks in the list." + System.lineSeparator()
                + BORDER_LINE);
    }

    public static void printInvalidCommandMessage() {
        System.out.println(BORDER_LINE + System.lineSeparator()
                + "    INVALID COMMAND" + System.lineSeparator()
                + BORDER_LINE);
    }
}
