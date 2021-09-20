public class TaskProcessor {
    public static void callTaskMethod(String command, int index) {
        String[] words = command.split("\\|");
        switch (words[0]) {
        case "T ":
            TaskList.addTodo(words[2]);
            break;
        case "D ":
            TaskList.addDeadline(words[2],words[3]);
            break;
        case "E ":
            TaskList.addEvent(words[2], words[3]);
        }

        if (words[1].trim().equals("1")) {
            TaskList.markTaskAsDone(index);
        }
    }
}
