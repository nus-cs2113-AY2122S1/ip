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
     * @param textToAppend
     * @throws IOException
     * function to append new line when user adds in a new line
     * Called in updateList to get live update as user enters
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * @param currTask: current task being added
     * Function that appends task details in correct format in file
     */
    public static void updateList(Task currTask) {
        String taskName = currTask.getTaskName();
        String isDoneSymbol = currTask.isDone() ? "X" : "O";
        // check prefix
        if (currTask.getPrefix().equals("[T]")) {
            try {
                appendToFile("todo | " + isDoneSymbol + " | " + taskName + System.lineSeparator());
            } catch (IOException e) {
                MessagePrinter.ioexception(e.getMessage());
            }
        } else if (currTask.getPrefix().equals("[D]")) {
            try {
                Deadline deadlineTask = (Deadline) currTask;
                String deadline = deadlineTask.getDeadline();
                appendToFile("deadline | " + isDoneSymbol + " | " + taskName + " | " + deadline + System.lineSeparator());
            } catch (IOException e) {
                MessagePrinter.ioexception(e.getMessage());
            }
        } else {
            try {
                Event eventTask = (Event) currTask;
                String eventDate = eventTask.getEventDate();
                appendToFile("event | " + isDoneSymbol + " | " + taskName + " | " + eventDate + System.lineSeparator());
            } catch (IOException e) {
                MessagePrinter.ioexception(e.getMessage());
            }
        }
    }

    /**
     * Rewrite list; Delete old list and update based on current Task array
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
