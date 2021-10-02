package duke.util;

import duke.exception.IncompleteTaskInput;
import duke.exception.NoKeywordException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.PrintBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import static duke.ui.ErrorReport.alarm;
import static duke.util.ActionBot.DESCRIPTION;
import static duke.util.ActionBot.TIME;

public class Storage {

    protected String filePath;
    protected File taskData;

    //the escape character of stored data
    static final String STORAGE_ESCAPE = "\\|";

    /*
     * The storage constructor. Creates a new doc and its parent files if
     * java can't find it in the specified path.
     * If the file already exits, reference to the file.
     *
     * @param filePath a path to the file, with respect to the project root.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        taskData = new File(filePath);
        if (!taskData.exists()) {
            boolean has_mkdir = taskData.getParentFile().mkdirs();
            boolean has_newFile = taskData.createNewFile();
            if (has_mkdir && has_newFile) {
                PrintBot.print("New File created at" + filePath);
            }  else {
                PrintBot.print("Trouble creating new file at " + filePath);
            }
        }
    }

    /*
     * Read data from the duke file.
     * Abort and alarm if file is empty. If not, data from the file
     * will be read into the ArrayList<Task> tasks.
     *
     * @param tasks an object of taskList class that manipulates the
     * task ArrayList.
     */
    public void loadData(TaskList tasks) {
        try {
            PrintBot.loadingData();
            Scanner s = new Scanner(taskData);
            if (!s.hasNext()) {
                PrintBot.print(" => File is empty.");
            }
            while (s.hasNext()) {
                String data = s.nextLine();
                addLoadData(data, tasks);
                PrintBot.loadData(data);
            }
            PrintBot.print("Finish loading Data.");
        } catch (FileNotFoundException e) {
            //file not found
        }
    }


    /*
     * Overwrites all data in the duke.txt file with
     * current list.
     */
    public void saveData(TaskList tasks) {
        ArrayList<Task> list = tasks.tasks;
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : list) {
                fw.write(t.getStorageFormat() + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            alarm(Alarm.SAVE_ERROR);
        }
    }

    /*
     * Read one line from the file and add one task into ArrayList tasks.
     *
     * @param data single line unprocessed task description.
     * @param tasks taskList object containing the Task list and its manipulations.
     */
    public void addLoadData(String data, TaskList tasks) {
        String[] details = data.split(STORAGE_ESCAPE,3);

        String type = details[0].trim();
        String status = details[1].trim();
        boolean isDone;
        isDone = status.equals("1");
        String taskInput = details[2].trim();

        switch (type) {
        case "T":
            Todo t = tasks.addTodo(taskInput);
            t.setDone(isDone);
            break;
        case "D":
            try {
                String[] txt = ActionBot.getDetails(taskInput, STORAGE_ESCAPE);
                Deadline d = tasks.addDeadline(txt[DESCRIPTION],txt[TIME]);
                d.setDone(isDone);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DEADLINE);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_DDL_KEYWORD);
            } catch (IncompleteTaskInput incompleteTaskInput) {
                alarm(Alarm.INCOMPLETE_TASK);
            }
            break;
        case "E":
            try {
                String[] txt = ActionBot.getDetails(taskInput, STORAGE_ESCAPE);
                Event e = tasks.addEvent(txt[DESCRIPTION],txt[TIME]);
                e.setDone(isDone);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_EVENT);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_EVENT_KEYWORD);
            } catch (IncompleteTaskInput incompleteTaskInput) {
                alarm(Alarm.INCOMPLETE_TASK);
            }
            break;
        default:
            PrintBot.print("Error reading file. Presence of invalid command. ");
            break;
        }
    }
}
