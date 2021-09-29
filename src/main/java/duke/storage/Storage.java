package duke.storage;

import duke.tasklist.TaskList;
import duke.tasklist.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    protected String fileName;
    protected String fileDir;
    public final ArrayList<Task> items = new ArrayList<>();


    /**
     * @param fileDir the directory of the save file
     * @param fileName the name of the save file
     */
    public Storage(String fileName, String fileDir) {
        this.fileDir = fileDir;
        this.fileName = fileName;
    }

    /**
     * Check and create save folder and save file if it does not exist
     *
     */
    public void create() {
        File saveFile = new File(fileName);
        File saveDir = new File(fileDir);
        if (saveDir.mkdirs()) {
            System.out.println("Successfully created save dir");
        } else {
            System.out.println("Save folder already exists.");
        }
        try {
            if (saveFile.createNewFile()) {
                System.out.println("Successfully created save file");
            } else {
                System.out.println("Save file already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the current list of tasks to save file
     *
     */
    public void save() {
        Task[] saveLists = new Task[items.size()];
        items.toArray(saveLists);
        try {
            FileWriter fw = new FileWriter(fileName);
            for (Task saveList : saveLists) {
                fw.write(saveList.toString().charAt(1) + "|");
                fw.write(saveList.getStatus() + "|" + saveList.getDescription());
                if (!saveList.getDate().equals("empty")) {
                    fw.write("|" + saveList.getDate());
                }
                fw.write("\n");
            }
            System.out.println("Automatically saved to " + fileName);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the tasks list from save file
     *
     */
    public void load(TaskList task) throws FileNotFoundException {
        File saveFile = new File(fileName);
        Scanner s = new Scanner(saveFile);
        while (s.hasNext()) {
            String[] chars = s.nextLine().split("\\|");
            String type = chars[0].trim();
            String status = chars[1].trim();
            String description = chars[2].trim();
            switch (type) {
                case "E":
                    String date = chars[3].trim();
                    task.addEvent(items, description, date);
                    if (status.equals("1")) {
                        items.get(task.getTaskCount()).markDone();
                    }
                    break;
                case "D":
                    date = chars[3].trim();
                    task.addDeadline(items, description, date);
                    if (status.equals("1")) {
                        items.get(task.getTaskCount()).markDone();
                    }
                    break;
                case "T":
                    task.addTodo(items, description);
                    break;
            }
        }
        task.loadTaskCount(items);
        System.out.println("Save file successfully loaded");
    }

}
