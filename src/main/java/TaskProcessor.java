import java.io.IOException;

public class TaskProcessor {
    public static void callTaskMethod(TaskManager t1, String command, int index) throws IOException {
        String[] words = command.split("\\|");
        switch (words[0]) {
        case "T ":
            t1.addTodoTask(words[2]);
            break;
        case "D ":
            t1.addDeadlineTask(words[2],words[3]);
            break;
        case "E ":
            t1.addEventTask(words[2], words[3]);
        }

        if (words[1].trim().equals("1")) {
            t1.markTaskAsDone(index);
        }
    }
}
