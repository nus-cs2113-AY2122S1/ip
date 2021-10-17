package storage;

import task.type.Deadline;
import task.type.Event;
import task.type.Task;
import task.type.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String PATH = "data/savedData.txt";
    private static final String DIRECTORY = "data";

    public static void saveData(ArrayList<Task> tasks) throws IOException{
        StringBuilder line = new StringBuilder();
        for(Task task: tasks){
            line.append(serializeTask(task));
        }

        FileWriter fw = new FileWriter(PATH);
        fw.write(line.toString());
        fw.close();
    }

    public static ArrayList<Task> loadData() throws FileNotFoundException{
        File directory = new File(DIRECTORY);
        if(!directory.exists()){
            if(directory.mkdir()){
                System.out.println("Successfully created a new directory for save file");
            }
        } else {
            File f = new File(PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a scanner using the file as the source
            while(s.hasNext()){
                taskList.add(deserializeTask(s.nextLine()));
            }
        }

        return taskList;
    }

    private static String serializeTask(Task task){
        String result = "";
        result += task.getTaskType() + "|";
        result += (task.getDoneStatus() ? 1 : 0) + "|";
        result += task.getDescription();

        if (task instanceof Deadline) {
            result += "|" + ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            result += "|" + ((Event) task).getAt();
        }

        return result + "\n";
    }

    private static Task deserializeTask(String serializedTask){
        Task task = null;
        String[] splitTask = serializedTask.split("\\|");

        String taskType = splitTask[0];
        boolean isDone = splitTask[1].equals("1");
        String description = splitTask[2].trim();

        switch (taskType) {
            case "T":
            task = new Todo(description, isDone);
            break;
            case "E":
            String at = splitTask[3].trim();
            task = new Event(description, at, isDone);
            break;
            case "D":
            String by = splitTask[3].trim();
            task = new Deadline(description, by, isDone);
            break;
        }
        return task;
    }
}