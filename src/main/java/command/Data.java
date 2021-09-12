package command;

import java.util.ArrayList;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Data {
    public static void write(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            for (Task currentTask : tasks) {
                fw.write(getTaskData(currentTask) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("  (!) FATAL: IO Error");
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> read() throws IOException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        /*
        check for whether ./data directory exists
        if not, create ./data directory
        */
        File dir = new File("data");
        if(!dir.exists()) {
            dir.mkdir();
        }
        /*
        Check for whether ./data/data.txt exists
        if so, process list of tasks contained within
        if not, create new, empty data.txt file
        */
        File data = new File("data/data.txt");
        if (data.exists()) {
            System.out.println("  (+) Data file found: " + data.getAbsolutePath());
            
            System.out.println("  (+) Loaded " + tasks.size() + " entries");
        } else {
            System.out.println("  (!) Data file not found");
            System.out.println(data.getAbsolutePath());
            System.out.println("  (+) Empty data file created: " + data.getAbsolutePath());
        }
        return tasks;
    }

    private static String getTaskData(Task current) {
        String output = "";
        if (current instanceof ToDo) {
            output += "T | " + convertStatus(current.getStatus()) + " | ";
            output += current.getDescription();
        } else if (current instanceof Deadline) {
            output += "D | " + convertStatus(current.getStatus()) + " | ";
            output += current.getDescription() + " | ";
            output += ((Deadline) current).getTime();
        } else if (current instanceof Event) {
            output += "D | " + convertStatus(current.getStatus()) + " | ";
            output += current.getDescription() + " | ";
            output += ((Event) current).getStart() + " | ";
            output += ((Event) current).getEnd() + " | ";
        }
        System.out.println(output);
        return output;
    }

    private static int convertStatus(String status) {
        if (status.equals("X")) {
            return 1;
        } else {
            return 0;
        }
    }
}
