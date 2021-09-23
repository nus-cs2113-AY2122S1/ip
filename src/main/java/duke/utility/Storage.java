package duke.utility;

import duke.functions.Deadline;
import duke.functions.Event;
import duke.functions.Task;
import duke.functions.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage extends Ui {
    private final String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public void readSavedList(ArrayList<Task> items) throws IOException {
        File f = new File(filepath);
        if (f.createNewFile()) {
            drawLine();
            System.out.println("\tNo saved task list, new file created");
            drawLine();
        } else {
            Scanner fs = new Scanner(f);
            while (fs.hasNext()) {
                String[] feed = fs.nextLine().split("--");

                switch (feed[0]) {
                case "T":
                    items.add(new Todo(feed[2]));
                    break;
                case "D":
                    items.add(new Deadline(feed[2], feed[3]));
                    break;
                case "E":
                    items.add(new Event(feed[2], feed[3]));
                    break;
                default:
                    break;
                }

                if (feed[1].equals("X")) {
                    items.get(items.size() - 1).markAsDone();
                }
            }
            printTaskList(items);
        }
    }

    public void writeToFile(ArrayList<Task> items) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task i : items) {
            if (i instanceof Todo) {
                fw.write("T" + "--" + i.getStatusIcon() + "--" + i.getDescription());
                fw.write(System.lineSeparator());
            } else if (i instanceof Deadline) {
                fw.write("D" + "--" + i.getStatusIcon() + "--" + i.getDescription() + "--" + ((Deadline) i).getBy());
                fw.write(System.lineSeparator());

            } else if (i instanceof Event) {
                fw.write("E" + "--" + i.getStatusIcon() + "--" + i.getDescription() + "--" + ((Event) i).getAt());
                fw.write(System.lineSeparator());
            }
        }
        if (items.isEmpty()) {
            fw.write("");
        }
        fw.close();
    }

}
