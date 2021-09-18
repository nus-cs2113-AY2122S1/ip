package herrekt;

import herrekt.taskmanager.Deadline;
import herrekt.taskmanager.Event;
import herrekt.taskmanager.Task;
import herrekt.taskmanager.Todo;

public class Parser {
    public int parseDoneInputToInt(String phrase) {
        phrase = phrase.replace("done ", "");
        return Integer.parseInt(phrase);
    }

    public int parseDeleteInputToInt(String phrase) {
        phrase = phrase.replace("delete ", "");
        return Integer.parseInt(phrase);
    }

    public Task parsePhraseToTask(String phrase) {
        Task task = null;
        if (phrase.contains("todo")) {
            task = new Todo(phrase.replace("todo ", ""));
        } else if (phrase.contains("deadline")) {
            phrase = phrase.replace("deadline ","");
            String[] taskAndTime = phrase.split(" /by ");
            task = new Deadline(taskAndTime[0], taskAndTime[1]);
        } else if (phrase.contains("event")) {
            phrase = phrase.replace("event ","");
            String[] taskAndTime = phrase.split(" /at ");
            task = new Event(taskAndTime[0], taskAndTime[1]);
        }
        return task;
    }
}
