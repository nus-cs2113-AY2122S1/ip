public class Parser {

    public static String parseInput(String command) {
        return command.toLowerCase().trim();
    }

    public static String[] parseTask(String command, Action taskType) {
        String[] parameters = new String[2];
        switch (taskType) {
        case TO_DO:
            parameters[0] = command.substring(command.indexOf("todo") + 5);
            parameters[1] = "N/A";
            break;
        case EVENT:
            parameters[0] = command.substring(command.indexOf("event") + 6, command.indexOf("/at"));
            parameters[1] = command.substring(command.indexOf("/at") + 3);
            break;
        case DEADLINE:
            parameters[0] = command.substring(command.indexOf("deadline") + 9, command.indexOf("/by"));
            parameters[1] = command.substring(command.indexOf("/by") + 3);
            break;
        default:
            parameters[0] = "N/A";
            parameters[1] = "N/A";

        }
        parameters[0] = parameters[0].trim();
        parameters[1] = parameters[1].trim();
        return parameters;
    }

    public static Action translateAction(String command) {
        String normalizedCommand = parseInput(command).split(" ")[0];
        if (normalizedCommand.equals("list")) {
            return Action.LIST;
        } else if (normalizedCommand.equals("bye")) {
            return Action.QUIT;
        } else if (normalizedCommand.equals("done")) {
            return Action.MARK_DONE;
        } else if (normalizedCommand.equals("todo")) {
            return Action.TO_DO;
        } else if (normalizedCommand.equals("deadline")) {
            return Action.DEADLINE;
        } else if (normalizedCommand.equals("event")) {
            return Action.EVENT;
        } else {
            return Action.UNDEFINED;
        }
    }
}
