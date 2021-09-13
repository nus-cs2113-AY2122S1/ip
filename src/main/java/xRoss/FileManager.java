package xRoss;

import xRoss.exception.EmptyStringException;
import xRoss.task.Deadline;
import xRoss.task.Event;
import xRoss.task.Task;
import xRoss.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private String filepath = "./data/xRoss.txt";

    public void writeToFile(String newLine){
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            fileWriter.write(newLine);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("\tSomething went wrong...");
            e.printStackTrace();
        }
    }

    public void readFromFile(TaskManager taskManager){
        try {
            File file = new File(filepath);

            Scanner s = new Scanner(file);
            while (s.hasNext()){
                taskManager.addTask(convertScannedLineToTask(s.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tSomething went wrong...");
            e.printStackTrace();
        }
    }

    private Task convertScannedLineToTask(String s){
        String[] scannedTask = s.split(" | ");
        Task task = null;

        try {
            switch (scannedTask[0]){
            case "T":
                task = new Todo(scannedTask[2]);
                break;
            case "D":
                task = new Deadline(scannedTask[2], scannedTask[3]);
                break;
            case "E":
                task = new Event(scannedTask[2], scannedTask[3]);
            default:
            }
        } catch (EmptyStringException e){ // should not occur
            System.out.println("Empty string detected in task list...");
        }

        return task;
    }
}
