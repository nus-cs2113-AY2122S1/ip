public class Parser {
    static private final String COMMAND_LIST = "list";
    static private final String COMMAND_TODO = "todo";
    static private final String COMMAND_EVENT = "event";
    static private final String COMMAND_DEADLINE = "deadline";
    static private final String COMMAND_DONE = "done";
    static private final String COMMAND_EXIT = "bye";

    static private final String INVALID_COMMAND = "Yo check your typing man. I don't get it.";

    static private final String COMMAND_DEADLINE_SEPARATOR = "/by";
    static private final String COMMAND_EVENT_SEPARATOR = "/at";

    static private final String SPACE_SEPARATOR = " ";
    static private final String EMPTY_STRING = "";

    static private final int EVENT_DEADLINE_ARGUMENT_COUNT = 2;

    static private final int MAX_NUMBER = 100;

    private static Task[] userTasks;
    static private int userTaskIndex;

    public Parser() {
        userTasks = new Task[MAX_NUMBER];
        userTaskIndex = 0;
    }

    public String parse(String command) {
        String[] words = command.split(SPACE_SEPARATOR);

        if (words[0].equals(COMMAND_LIST)) {
            return parseListCommand();
        } else if (words[0].equals(COMMAND_TODO)) {
            return parseTodoCommand(command);
        } else if (words[0].equals(COMMAND_EVENT)) {
            return parseEventCommand(command);
        } else if (words[0].equals(COMMAND_DEADLINE)) {
            return parseDeadlineCommand(command);
        } else if (words[0].equals(COMMAND_DONE)) {
            return parseDoneCommand(command);
        }
        else {
            return INVALID_COMMAND;
        }
    }

    public boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }

    private static String parseListCommand() {
        String msg = "";

        if (userTaskIndex == 0) {
            msg = "Nothing.";
            return msg;
        }

        for (int i = 0; i < userTaskIndex - 1; i++) {
            msg += (i + 1) + "."
                    + userTasks[i].toString() + '\n' + '\t';
        }
        msg = msg + userTaskIndex + "."
                + userTasks[userTaskIndex - 1].toString();

        return msg;
    }

    private static String parseTodoCommand(String command) {
        String msg;
        String detail = command.substring(COMMAND_TODO.length()).trim();

        userTasks[userTaskIndex] = new ToDos(detail);

        msg = "Gotcha. Do this while you're at it:\n"
                + "\t\t" + userTasks[userTaskIndex].toString() + '\n'
                + "\tNow you have " + (userTaskIndex + 1)
                + " tasks in the list.";

        userTaskIndex++;
        return msg;
    }

    private static String parseDeadlineCommand(String command) {
        String msg;
        String detail = command.substring(COMMAND_DEADLINE.length()).trim();
        String[] contentAndDate = detail.split(COMMAND_DEADLINE_SEPARATOR);

        for (int i = 0; i < contentAndDate.length; i++) {
            if (contentAndDate.length != EVENT_DEADLINE_ARGUMENT_COUNT
                    || contentAndDate[i].equals(EMPTY_STRING)) {
                msg = "Invalid format. Enter by this format:\n"
                        + "\t\t\"deadline [description] /by [deadline]\"";
                return msg;
            }
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            contentAndDate[i] = contentAndDate[i].trim();
        }

        userTasks[userTaskIndex] = new Deadline(contentAndDate[0], contentAndDate[1]);

        msg = "Gotcha. I beg you to do this:\n"
                + "\t\t" + userTasks[userTaskIndex].toString() + '\n'
                + "\tNow you have " + (userTaskIndex + 1)
                + " tasks in the list.";

        userTaskIndex++;
        return msg;
    }

    private static String parseEventCommand(String command) {
        String msg;
        String detail = command.substring(COMMAND_EVENT.length()).trim();
        String[] contentAndDate = detail.split(COMMAND_EVENT_SEPARATOR);

        for (int i = 0; i < contentAndDate.length; i++) {
            if (contentAndDate.length != EVENT_DEADLINE_ARGUMENT_COUNT
                    || contentAndDate[i].equals(EMPTY_STRING)) {
                msg = "Invalid format. Enter by this format:\n"
                        + "\t\t\"event [description] /at [date]\"";
                return msg;
            }
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            contentAndDate[i] = contentAndDate[i].trim();
        }

        userTasks[userTaskIndex] = new Event(contentAndDate[0], contentAndDate[1]);

        msg = "Gotcha. You wanna attend this:\n"
                + "\t\t" + userTasks[userTaskIndex].toString() + '\n'
                + "\tNow you have " + (userTaskIndex + 1)
                + " tasks in the list.";

        userTaskIndex++;
        return msg;
    }

    private static String parseDoneCommand(String command) {
        String msg;
        String detail = command.substring(COMMAND_DONE.length()).trim();

        int taskDoneNumber = Integer.parseInt(detail);

        if (taskDoneNumber <= 0 || taskDoneNumber > userTaskIndex) {
            msg = "Task number not exist!";
            return msg;
        }

        userTasks[taskDoneNumber - 1].setDone();
        msg = "Good job. You may now enjoy the rest of "
                + "your suffering:\n"
                + "\t" + userTasks[taskDoneNumber - 1].toString();

        return msg;
    }
}
