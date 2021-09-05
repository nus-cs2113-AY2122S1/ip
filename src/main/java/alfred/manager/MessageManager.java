package alfred.manager;

import alfred.task.Task;

public abstract class MessageManager {
    public static final String LINE = "____________________________________________________________\n";
    private static final String LOGO =
            " **********************************\n" +
            " *     _    _  __              _  *\n" +
            " *    / \\  | |/ _|_ __ ___  __| | *\n" +
            " *   / _ \\ | | |_| '__/ _ \\/ _` | *\n" +
            " *  / ___ \\| |  _| | |  __/ (_| | *\n" +
            " * /_/   \\_\\_|_| |_|  \\___|\\__,_| *\n" +
            " **********************************\n";

    public static void invalidCommandMessage() {
        System.out.println(
                LINE +
                " Perhaps you could rephrase that in a way us civilians could comprehend.\n" +
                LINE
        );
    };

    public static void emptyDescriptionMessage() {
        System.out.println(
                LINE +
                "Master Wayne, if you do not specify your task, I'm afraid I cannot note it down.\n" +
                LINE
        );
    }

    public static void initMessage() {
        System.out.println(
                LOGO + "\n" + LINE +
                " Welcome back, Master Wayne.\n" +
                " How may I be of service to you?\n" +
                LINE
        );
    }

    public static void shutdownMessage() {
        System.out.println(
                LINE +
                " Very well sir, I shall leave you to your own devices.\n" +
                LINE
        );
    }

    public static void completeTaskMessage(int index, String taskDescription) {
        System.out.println(
                LINE +
                " Duly noted on completion of task, sir.\n" +
                "    " + index + "." + taskDescription + "\n" +
                LINE
        );
    }

    public static void addTaskMessage(Task t, int listIndex) {
        System.out.println(
                LINE +
                " I shall put this in your schedule, Master Wayne: \n    " + t.toString() + "\n" +
                " Sir, the number of Tasks you have scheduled currently amounts to " + listIndex + "." + "\n" +
                LINE
        );
    }
}
