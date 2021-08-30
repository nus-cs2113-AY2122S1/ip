package shikabot.storage;

import shikabot.task.Task;
import shikabot.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;

public class Storage {

    private final String path;
    private final File file;
    private TaskList taskList = new TaskList();

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
     * This function loads tasks by parsing the String inputted, then calling addSavedTask to add it to
     * taskList.
     * @param s String to be parsed.
     */
    private void loadTask(String s) {
        int firstDiv = s.indexOf("|") + 1;
        int secondDiv = s.indexOf("|", firstDiv) + 1;
        int thirdDiv = s.indexOf("|", secondDiv) + 1;
        char type = s.charAt(0);
        String atBy = s.substring(firstDiv, secondDiv - 1).trim();
        String name = s.substring(secondDiv, thirdDiv - 1).trim();
        String done = s.substring(thirdDiv).trim();
        taskList.addSavedTask(type, atBy, name, done);
    }

    /**
     * This function saves tasks to data/ShikaTasks.txt. It rewrites the txt from scratch.
     * @throws IOException when the saving operation is interrupted.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.close();
        for (Task task : taskList.taskList) {
            try {
                task.saveTask();
            } catch (IOException e) {
                throw new IOException();
            }
        }
    }

    public static class FileErrorException extends Exception {

    }
}
