package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class reads from and writes to a specified text file in the project to store tasks locally
 */
public class Storage {

    protected static final String FILE_PATH = "data/duke.txt";
    protected static final String GAP = " / ";

    /**
     * Loads tasks stored in a specific text file and add it to the task list
     * If errors are encountered, prints the error message and terminates the program
     *
     * @param tasks the task list where all loaded tasks will be added to
     */
    public void loadTasks(TaskList tasks) {
        try {
            addTasksIntoList(tasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads file with a specific file path, and create the file if the specified file path does not exist
     *
     * @return the file that is loaded from the file path
     * @throws IOException if the specified file path does not exist
     */
    public File loadFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            File dir = new File("data");
            dir.mkdir();
            File newFile = new File("data/duke.txt");
            newFile.createNewFile();
            return newFile;
        }
        return file;
    }

    /**
     * Add tasks read from a specified text file into the task list
     *
     * @param tasks the task list where all loaded tasks will be added to
     * @throws IOException when errors are encountered when reading from the file at the specified file path
     */
    public void addTasksIntoList(TaskList tasks) throws IOException {
        File file = loadFile();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String entry = s.nextLine();
            String[] entryComponents = entry.split(GAP);
            String description = entryComponents[0] + " " + entryComponents[2];
            switch (entryComponents[0]) {
            case "T":
                tasks.addTodo(description);
                break;
            case "D":
                String by = "by " + entryComponents[3];
                tasks.addDeadline(description, by);
                break;
            case "E":
                String at = "at " + entryComponents[3];
                tasks.addEvent(description, at);
                break;
            default:
                break;
            }
            if (entryComponents[1].equals("X")) {
                tasks.completeTask(tasks.getSize() - 1);
            }
        }
    }

    /**
     * Tries to write content from the task list to the text file at the specified file path
     * If errors are encountered, prints the error message and terminates the program
     *
     * @param tasks the task list where all the content are read from
     */
    public void saveTasks(TaskList tasks) {
        try {
            writeTasksToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Goes through the task list and add them into the text file at the specified file path
     *
     * @param tasks the task list that is being read
     * @throws IOException when errors are encountered when writing into the file at the specified file path
     */
    public void writeTasksToFile(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            String details = task.getDescription().substring(task.getDescription().indexOf(" ") + 1);
            String date = "";
            if (task instanceof Deadline) {
                date = ((Deadline) task).getBy().split(" ", 2)[1].trim();
            } else if (task instanceof Event) {
                date = ((Event) task).getAt().split(" ", 2)[1].trim();
            }
            String taskLabel = task.getType() + GAP + task.getIsDone() + GAP;
            String taskBody = details.trim() + GAP + date + System.lineSeparator();
            fileWriter.write(taskLabel + taskBody);
        }
        fileWriter.close();
    }


}
