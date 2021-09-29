package duke;

import Tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String FILE_DIR = "./data";
    private static final String FILE_NAME = "duke.txt";


    public static void initDataStore() throws IOException {
        File data_dir = new File(FILE_DIR);
        if (data_dir.mkdir()) {
            System.out.println("Directory " + FILE_DIR + " created");
        }
        File f = new File(FILE_DIR + "/" + FILE_NAME);
        if (f.createNewFile()) {
            System.out.println("File " + FILE_NAME + " created");
        }
    }

    // Loads tasks that are stored in ./data/duke.txt
    public static void loadTasks() throws FileNotFoundException, DukeException {
        File file = new File(FILE_DIR + "/" + FILE_NAME);
        Scanner inputFile = new Scanner(file);
        int pendingTasksCounter=0;
        while (inputFile.hasNext()) {
            Tasks pendingTasks;
            String tasksFromFile = inputFile.nextLine();
            String[] taskTypeSplit = tasksFromFile.split("_");
            switch (taskTypeSplit[0]) {
                case "T":
                    pendingTasks = new Todo(taskTypeSplit[2]);
                    TaskList.List[pendingTasksCounter]=pendingTasks;
                    if (taskTypeSplit[1].equals("X")) {
                        TaskList.List[pendingTasksCounter].setDone("X");
                    }
                        pendingTasksCounter++;
                    break;
                case "D":
                    pendingTasks = new Deadline(taskTypeSplit[2], taskTypeSplit[3]);
                    TaskList.List[pendingTasksCounter]=pendingTasks;
                    if (taskTypeSplit[1].equals("X")) {
                        TaskList.List[pendingTasksCounter].setDone("X");
                    }
                    pendingTasksCounter++;
                    break;
                case "E":
                    pendingTasks = new Event(taskTypeSplit[2], taskTypeSplit[3]);
                    TaskList.List[pendingTasksCounter]=pendingTasks;
                    if (taskTypeSplit[1].equals("X")) {
                        TaskList.List[pendingTasksCounter].setDone("X");
                    }
                        pendingTasksCounter++;
                    break;
                default:
                    throw new DukeException("not valid Task");
            }
        }
        TaskList.listSize=pendingTasksCounter;
    }

    // Saves all tasks to ./data/duke.txt
    public static void saveTasks() throws IOException{
        FileWriter file_writer = new FileWriter(FILE_DIR + "/" + FILE_NAME);
        StringBuilder outputString = new StringBuilder();
        for (int j = 0; j < TaskList.listSize; j++) {
            outputString.append(TaskList.List[j].toOutput());
            outputString.append(System.lineSeparator());
        }
        file_writer.write(outputString.toString());
        file_writer.close();
    }
}
