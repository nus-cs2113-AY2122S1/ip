public class Parser {

    /**
     * Handles the command given from the console.
     * @param curCommand The raw command.
     */
    public static void handleCommand(String curCommand) {
        String[] commands = curCommand.split(" ", 2);
        if (commands.length > 1) {
            handleMultiCommands(commands);
        } else {
            handleSingleCommand(curCommand);
        }
    }

    /**
     * Handles the case if there are multiple command parameters.
     * @param commands The commands array.
     */
    public static void handleMultiCommands(String[] commands) {
        switch (commands[0]) {
        case "done":
            int doneIndex = getPositiveNumeric(commands[1]);
            if (doneIndex > 0 && doneIndex <= TaskList.getTasksInstance().size()) {
                TaskList.getTasksInstance().get(doneIndex - 1).setDone(true);
            }
            if (doneIndex > 0 && doneIndex <= TaskList.getTasksInstance().size()) {
                System.out.println("Nice! I've marked this task as done:\n " + Ui.taskToString(TaskList.getTasksInstance().get(doneIndex - 1)));
            }
            else {
                new DukeException(0);
            }
            Storage.writeIntoFile();
            break;
        case "todo":
            TaskList.getTasksInstance().add(new Task(commands[1], "", 'T', false));
            Ui.printNewTask(TaskList.getTasksInstance().get(TaskList.getTasksInstance().size() - 1), TaskList.getTasksInstance().size());
            Storage.writeIntoFile();
            break;
        case "deadline":
            String[] deadin = commands[1].split(" /by ", 2);
            if (deadin.length > 1) {
                TaskList.getTasksInstance().add(new Task(deadin[0], deadin[1], 'D', false));
                Ui.printNewTask(TaskList.getTasksInstance().get(TaskList.getTasksInstance().size() - 1), TaskList.getTasksInstance().size());
            }
            else {
                new DukeException(0);
            }
            Storage.writeIntoFile();
            break;
        case "event":
            String[] evin = commands[1].split(" /at ", 2);
            if (evin.length > 1) {
                TaskList.getTasksInstance().add(new Task(evin[0], evin[1], 'E', false));
                Ui.printNewTask(TaskList.getTasksInstance().get(TaskList.getTasksInstance().size() - 1), TaskList.getTasksInstance().size());
            }
            else {
                new DukeException(0);
            }
            Storage.writeIntoFile();
            break;
        case "help":
            Ui.printHelp(commands[1]);
            break;
        case "find":
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < TaskList.getTasksInstance().size(); i++) {
                if (TaskList.getTasksInstance().get(i).getTask().contains(commands[1])) {
                    System.out.println((i + 1) + "." + Ui.taskToString(TaskList.getTasksInstance().get(i)));
                }
            }
            break;
        case "delete":
            int deleteIndex = getPositiveNumeric(commands[1]);
            if (deleteIndex > 0 && deleteIndex <= TaskList.getTasksInstance().size()) {
                System.out.println("Noted. I've removed this task:\n " + Ui.taskToString(TaskList.getTasksInstance().get(deleteIndex - 1)) + "\nNow you have " + (TaskList.getTasksInstance().size() - 1) + " tasks in the list.\n");
            }
            else {
                new DukeException(0);
            }
            TaskList.getTasksInstance().remove(deleteIndex - 1);
            Storage.writeIntoFile();
            break;
        default:
            new DukeException(1);
        }
    }

    /**
     * Handles the case if there is one command parameter.
     * @param curCommand The command.
     */
    public static void handleSingleCommand(String curCommand) {
        switch (curCommand) {
        case "list":
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TaskList.getTasksInstance().size(); i++) {
                System.out.println((i + 1) + "." + Ui.taskToString(TaskList.getTasksInstance().get(i)));
            }
            break;
        case "bye":
            System.out.println("Bye. Hope to see you again soon!");
            break;
        case "help":
            Ui.printHelp("help");
            break;
        default:
            new DukeException(1);
        }
    }

    /**
     * Converts string into positive numeric if possible.
     * @param str The string.
     * @return The string converted to positive integer (if possible) or -1 if not possible.
     */
    public static int getPositiveNumeric(String str) {
        try {
            return Integer.parseInt(str);
        } catch(NumberFormatException e){
            return -1;
        }
    }
}
