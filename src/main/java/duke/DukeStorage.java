package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DukeStorage {
    private static final String filePath = "data/data.txt";
    private File file = new File(filePath);

    public DukeStorage() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Welcome back!");
        }
    }

    public void saveData(Task[] tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                if (task != null){
                    fw.write(task.toStorageString() + System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {

        }
    }

    public int loadData(Task[] tasks) {
        int taskCount = 0;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks[taskCount] = readTaskData(scanner.nextLine());
                if(tasks[taskCount] != null){
                    taskCount++;
                }
            }
        } catch (Exception e){

        }
        return taskCount;
    }

    public Task readTaskData(String savedTask) {
        Task task = null;
        try {
            String[] words = savedTask.split("//");
            switch (words[0]) {
            case "T":
                task = new ToDo(words[1]);
                break;
            case "D":
                task = new Deadline(words[1],words[2] );
                break;
            case "E":
                task = new Event(words[1],words[2] );
            }
            System.out.println(words[3]);
            if (words[3].equals("X")) {
                task.setDone();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return task;
    }
}
