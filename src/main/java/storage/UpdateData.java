package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import ui.MessagePrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UpdateData {
    private static final String FILEPATH = "data/friday.txt";

    /**
     * Function to append new line to file in storage
     * @param textToAppend String in correct format to append to storage file
     * @throws IOException If file doesn't exist in FILEPATH
     *
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Function that appends task details in correct format into storage file
     * @param currTask: Task object of current task being added
     */
    public static void updateList(Task currTask) {
        if (currTask == null) {
            return;
        }
        String taskName = currTask.getTaskName();
        String isDoneSymbol = currTask.isDone() ? "X" : "O";
        // check prefix to format task accordingly based on task user added
        if (currTask.getPrefix().equals("[T]")) {
            try {
                appendToFile("todo | " + isDoneSymbol + " | " + taskName + System.lineSeparator());
            } catch (IOException e) {
                MessagePrinter.ioexception(e.getMessage());
            }
        } else if (currTask.getPrefix().equals("[D]")) {
            try {
                Deadline deadlineTask = (Deadline) currTask;
                String deadline = deadlineTask.getTaskDate().toString();
                appendToFile("deadline | " + isDoneSymbol + " | " + taskName + " | " + deadline + System.lineSeparator());
            } catch (IOException e) {
                MessagePrinter.ioexception(e.getMessage());
            }
        } else {
            try {
                Event eventTask = (Event) currTask;
                String eventDate = eventTask.getTaskDate().toString();
                appendToFile("event | " + isDoneSymbol + " | " + taskName + " | " + eventDate + System.lineSeparator());
            } catch (IOException e) {
                MessagePrinter.ioexception(e.getMessage());
            }
        }
    }

    /**
     * Deletes content previously in storage and updates based on current Task array
     */
    public static void rewriteList(ArrayList<Task> tasks) {
        // Clear list
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            MessagePrinter.ioexception(e.getMessage());
        }

        // go through current task list and update
        for (Task task : tasks) {
            updateList(task);
        }
    }
}
