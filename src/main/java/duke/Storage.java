package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) throws Exception{
        this.file = new File(filePath);
        if (!this.file.exists()) {
            File dir = new File("data");
            dir.mkdir();
            this.file = new File("data/duke.txt");
            file.createNewFile();
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNext()){
            String[] taskInfo = s.nextLine().split(",");
            if (taskInfo[0].equals("duke.Task")) {
                Task todo = new Task(Boolean.parseBoolean(taskInfo[1]), taskInfo[2]);
                tasks.add(todo);
            } else if (taskInfo[0].equals("duke.Event")) {
                Task event = new Event(Boolean.parseBoolean(taskInfo[1]),taskInfo[2], taskInfo[3]);
                tasks.add(event);
            } else {
                Task deadline = new Deadline(Boolean.parseBoolean(taskInfo[1]), taskInfo[2], taskInfo[3]);
                tasks.add(deadline);
            }
        }
        return tasks;
    }

    public void store(TaskList list) throws IOException {
        FileWriter writer = new FileWriter(this.file);
        for(int i = 0; i < list.size(); i++) {
            writer.write(list.saveTask(i) + "\n");
        }
        writer.close();
    }
}
