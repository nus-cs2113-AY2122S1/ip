package duke.storage;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static final String FILE_PATH = "data/dukeTask.txt";

    public static void createFile() throws IOException {
        Files.createDirectories(Path.of("data"));
        Files.createFile(Path.of(FILE_PATH));
    }

    public static void saveFile(ArrayList<Task> TaskList) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);

            String textToAdd = "";

            for (Task t: TaskList) {
                textToAdd = textToAdd.concat(t.storageText() + "\n");
            }
            fw.write(textToAdd);
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Saving error");
        }
    }

    public static void openFile(ArrayList<Task> taskList) {
        try {
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String task = s.nextLine();
                String[] splitTaskString = task.split("\\|");
                switch (splitTaskString[0]) {
                case "T":
                    taskList.add(new ToDos(splitTaskString[2]));
                    break;
                case "D":
                    taskList.add(new Deadlines(splitTaskString[2], splitTaskString[3]));
                    break;
                case "E":
                    taskList.add(new Events(splitTaskString[2], splitTaskString[3]));
                    break;
                }
                if (splitTaskString[1].equals("1")) {
                    taskList.get(taskList.size() - 1).markDone();
                }
            }
        }
        catch (FileNotFoundException e) {
            try {
                createFile();
            }
            catch (IOException ioe) {
                System.out.println("Error creating file, please try again!");
            }
        }
    }
}
