package duke;

import Command.AddCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage where tasks are loaded and saved.
 */
public class Storage {
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static final String SEPARATOR_BY = " /by ";
    private static final String SEPARATOR_AT = " /at ";
    private static final String SEPARATOR_VERTICAL_LINE = " \\| ";

    private static final String INITIAL_TODO = "T";
    private static final String INITIAL_DEADLINE = "D";
    private static final String INITIAL_EVENT = "E";

    private static final String SEPARATOR_WORD_FILE = " | ";

    private static final String TASK_IS_DONE = "1";
    private static final String TASK_IS_NOT_DONE = "0";

    private String filepath;
    private ArrayList<Task> savedTasks;

    /**
     * Instantiates a <code>Storage</code> object.
     *
     * @param filepath Relative path of the file where tasks are saved.
     */
    public Storage (String filepath) {
        this.filepath = filepath;
        this.savedTasks = loadTasks();
    }

    /**
     * Returns tasks saved.
     *
     * @return tasks saved.
     */
    public ArrayList<Task> getSavedTasks() {
        return this.savedTasks;
    }

    private ArrayList<Task> loadTasks() {
        ArrayList<String> tasks = new ArrayList<>();
        try {
             tasks = loadSavedInputAsString();
        } catch (FileNotFoundException e) {
            File file = new File(filepath);
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("     Unable to create new file");
            }
        }

        return loadSavedInputAsTasks(tasks);
    }

    private ArrayList<String> loadSavedInputAsString() throws FileNotFoundException {
        ArrayList<String> tasks = new ArrayList<>();
        File f = new File(filepath);
        Scanner s = new Scanner(f);

        while(s.hasNext()) {
            tasks.add(s.nextLine());
        }

        return tasks;
    }

    private ArrayList<Task> loadSavedInputAsTasks(ArrayList<String> savedInputs) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String input : savedInputs) {
            parseSavedInputToTask(tasks, input);
        }

        return tasks;
    }

    private void parseSavedInputToTask(ArrayList<Task> tasks, String input) {
        String[] inputs = input.split(SEPARATOR_VERTICAL_LINE);
        String taskType = inputs[0];
        String isTaskDone = inputs[1];
        String taskDescription = inputs[2];

        String fullCommand;

        if (taskType.equals(INITIAL_TODO)) {
            fullCommand = COMMAND_TODO + " " + taskDescription;
        } else if (taskType.equals(INITIAL_DEADLINE)) {
            String taskDeadline = inputs[3];
            fullCommand = COMMAND_DEADLINE + " " + taskDescription
                    + SEPARATOR_BY + taskDeadline;
        } else {
            String eventTime = inputs[3];
            fullCommand = COMMAND_EVENT + " " + taskDescription
                    + SEPARATOR_AT + eventTime;
        }

        new AddCommand(fullCommand)
                .executeForLoading(new TaskList(tasks), this);

        if (isTaskDone.equals(TASK_IS_DONE)) {
            tasks.get(tasks.size() - 1).markAsDone();
        }
    }

    /**
     * Saves tasks into the file.
     *
     * @param tasks A collection of tasks to be saved.
     */
    public void saveTasksToDiskWithException(ArrayList<Task> tasks) {
        try {
            saveTasksToDisk(tasks);
        } catch (IOException e) {
            System.out.println("Failed to save new data to the disk");
        }
    }

    private void saveTasksToDisk(ArrayList<Task> tasks)
            throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for(Task task : tasks) {
            String inputToWrite = parseTaskToString(task);
            fw.write(inputToWrite);
        }
        fw.close();
    }

    private String parseTaskToString(Task task) {
        String inputToWrite = "";
        if (task instanceof Todo) {
            inputToWrite += INITIAL_TODO;
        } else if (task instanceof Deadline) {
            inputToWrite += INITIAL_DEADLINE;
        } else {
            inputToWrite += INITIAL_EVENT;
        }

        inputToWrite += SEPARATOR_WORD_FILE
                + (task.getIsDone() ? TASK_IS_DONE : TASK_IS_NOT_DONE)
                + SEPARATOR_WORD_FILE + task.getTaskName();

        if (task instanceof Deadline) {
            inputToWrite += SEPARATOR_WORD_FILE;
            inputToWrite += ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            inputToWrite += SEPARATOR_WORD_FILE;
            inputToWrite += ((Event) task).getAt();
        }

        inputToWrite += "\n";
        return inputToWrite;
    }
}
