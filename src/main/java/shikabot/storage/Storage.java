package shikabot.storage;

import shikabot.task.Task;
import shikabot.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {

    private final String path;
    private final File file;
    private final TaskList taskList = new TaskList();

    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * This function attempts to create the save file at the given path if it does not already exist.
     * @return true if file is created, false otherwise
     */
    public boolean setupSave() throws FileErrorException {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            throw new FileErrorException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
        return false;
    }

    /**
     * Function that loads tasks from the given filepath.
     * @throws FileNotFoundException if filepath is invalid
     */
    public TaskList loadTasks() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            loadTask(s.nextLine());
        }
        return taskList;
    }

    /**
     * Function that loads tasks by parsing the String inputted, then calling addSavedTask to add it to
     * taskList.
     * @param s String to be parsed.
     */
    private void loadTask(String s) {
        int firstDiv = s.indexOf("|") + 1;
        int secondDiv = s.indexOf("|", firstDiv) + 1;
        int thirdDiv = s.indexOf("|", secondDiv) + 1;
        char type = s.charAt(0);
        String date = s.substring(firstDiv, secondDiv - 1).trim();
        LocalDate atBy;
        try {
            atBy = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            atBy = null;
        }
        String name = s.substring(secondDiv, thirdDiv - 1).trim();
        String done = s.substring(thirdDiv).trim();
        taskList.addSavedTask(type, atBy, name, done);
    }

    /**
     * Function that saves tasks to data/ShikaTasks.txt. It rewrites the txt from scratch.
     * @throws IOException if saving operation is interrupted.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.close();
        for (int i = 0; i < taskList.getSize(); i++) {
            try {
                saveTask(taskList.getTask(i));
            } catch (IOException e) {
                throw new IOException();
            }
        }
    }

    /**
     * Function that saves the individual task by appending it to the end of the current save file.
     * @param task task to be saved.
     * @throws IOException if saving operation is interrupted.
     */
    public void saveTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(encodeTask(task));
        fw.close();
    }

    /**
     * Function that returns the String for a task to be encoded into the save file.
     * @param task task to be encoded.
     * @return String to be written into the save file.
     */
    public String encodeTask(Task task) {
        String divider = " | ";
        return task.getType()
                + divider
                + task.getAtBy()
                + divider
                + task.getName()
                + divider
                + (task.isDone() ? "true" : "false")
                + "\n";
    }

    public static class FileErrorException extends Exception {
    }
}
