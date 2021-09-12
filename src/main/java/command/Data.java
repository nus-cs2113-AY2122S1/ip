package command;

import java.util.ArrayList;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

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
        ArrayList<Task> tasks = new ArrayList<>();
        /*
        Check for whether ./data directory exists
        if not, create ./data directory
        */
        File dir = new File("data");
        if (!dir.exists()) {
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
            /*
            Read data.txt line by line and populate tasks list accordingly
             */
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                String[] line = s.nextLine().split(" \\| ");
                Boolean status = false;
                if (line[1].equals("1")) {
                    status = true;
                }
                switch(line[0]) {
                case "T":
                    ToDo newToDo = new ToDo(line[2], status);
                    tasks.add(newToDo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(line[2], line[3], status);
                    tasks.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(line[2], line[3], line[4], status);
                    tasks.add(newEvent);
                    break;
                }
            }
            System.out.println("  (+) Loaded " + tasks.size() + " entries");
        } else {
            System.out.println("  (!) Data file not found");
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
            output += "E | " + convertStatus(current.getStatus()) + " | ";
            output += current.getDescription() + " | ";
            output += ((Event) current).getStart() + " | ";
            output += ((Event) current).getEnd() + " | ";
        }
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
