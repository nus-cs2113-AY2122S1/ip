public class TaskProcessor {

    /**
     * Takes in one line of input from the scanner and adds the task to the task list.
     * @param command the line of input from the scanner
     * @param index the index of the current task
     */
    public static void callTaskMethod(String command, int index) {
        String[] words = command.split("\\|");
        switch (words[0].trim()) {
        case "T":
            TaskList.addTodo(words[2].trim());
            break;
        case "D":
            TaskList.addDeadline(words[2].trim(),words[3].trim());
            break;
        case "E":
            TaskList.addEvent(words[2].trim(), words[3].trim());
        }

        if (words[1].trim().equals("1")) {
            TaskList.markTaskAsDone(index);
        }
    }
}
