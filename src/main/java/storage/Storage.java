package storage;

import duke.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that loads tasks to and saves tasks from the duke.txt file.
 */
public class Storage {

    private static final String SEPARATOR_FOR_FILE = " / ";

    public Storage() {}

    /**
     * Loads the tasks from duke.txt file and creates a file if no file is found.
     *
     * @param tasks The ArrayList to store the tasks in
     * @throws IOException Throws an exception if something unexpected happens
     * when reading the file
     * @throws DukeException Throws an exception to tell the user the file is not
     * found and a new file is created
     */
    public void loadTextFile(TaskList tasks) throws IOException, DukeException {
        File file = new File("./duke.txt");
        if(file.createNewFile()) {
            throw new DukeException();
        }
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            addTaskFromFile(s,tasks);
        }
    }

    /**
     * Saves the tasks to the duke.txt file before the program stops
     *
     * @param tasks The ArrayList where the tasks are stored
     * @throws IOException Throws an exception if something unexpected happens
     *  when writing to the file.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException{
        FileWriter fw = new FileWriter ("./duke.txt");
        for (int i = 0; i < Task.getTotalTasks(); i++) {
            fw.write(tasks.getTask(i).getStatusIconAndDescriptionForFile() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Adds the tasks from the file and their completion status into the ArrayList
     * in different ways depending on the type of task.
     *
     * @param s the Scanner which is reading the file
     * @param tasks The ArrayList of tasks
     * @throws IndexOutOfBoundsException Throws an exception if the format of tasks
     * saved on the files are different.
     */
    private void addTaskFromFile(Scanner s, TaskList tasks) throws IndexOutOfBoundsException {
        String[] parsedOutput = s.nextLine().split(SEPARATOR_FOR_FILE);
        switch(parsedOutput[0]){
        case "T":
            tasks.addTask(new ToDo(parsedOutput[2]));
            updateTaskStatus(parsedOutput[1],tasks);
            break;
        case "E":
            tasks.addTask(new Event(parsedOutput[2], parsedOutput[3]));
            updateTaskStatus(parsedOutput[1],tasks);
            break;
        case "D":
            tasks.addTask(new Deadline(parsedOutput[2], parsedOutput[3]));
            updateTaskStatus(parsedOutput[1],tasks);
            break;
        }
    }

    private void updateTaskStatus(String done, TaskList tasks) {
        if (done.equals("1")) {
            tasks.getTask(Task.getTotalTasks()-1).markAsDone();
        }
    }
}
