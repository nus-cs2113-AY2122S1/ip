package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Ui;
import errors.InvalidFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Converts all task to a string to be written to the file Duke.txt.
     */
    public void saveData(ArrayList<Task> tasks) {
        String output = "";
        for (Task task : tasks) {
            output += task.toFile() + "\n";
        }

        try {
            FileWriter myFile = new FileWriter(filePath);
            myFile.write(output);
            myFile.close();
        } catch (IOException e) {
            new Ui().customPrint("Could not write to file!");
        }
    }

    /**
     * Loads the data to task ArrayList if Duke.txt exists.
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File myFile = new File(filePath);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] dataSplit = data.split("\\|");

                if (dataSplit.length < 3) { // Ensure that there should be at least 3 elements
                    throw new InvalidFile();
                }

                String taskType = dataSplit[0];
                Boolean taskCompleted = dataSplit[1].equals("true");
                String description = dataSplit[2];
                String date = "";

                if (dataSplit.length > 3) { // There is a date
                    date = dataSplit[3];
                }

                Task task;
                switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, date);
                    break;
                case "E":
                    task = new Event(description, date);
                    break;
                default:
                    throw new InvalidFile();
                }
                if (taskCompleted) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            new Ui().customPrint("Could not read from file!");
        } catch (InvalidFile invalidFile) {
            new Ui().customPrint("File contains invalid data!");
        }
        return tasks;
    }
}
