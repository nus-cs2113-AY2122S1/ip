package kitty.io;

import kitty.Kitty;
import kitty.KittyException;
import kitty.task.Deadline;
import kitty.task.Event;
import kitty.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class IO {

    public static final String DATA_PATH = "src/main/java/kitty/userinterface/data.txt";

    public static void initData() throws KittyException {
        try {
            File f = new File(DATA_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String rawData = s.nextLine();
                String type = rawData.substring(0, rawData.indexOf("|"));
                String status = rawData.substring(rawData.indexOf("|") + 1, rawData.indexOf("|") + 2);
                String task = rawData.substring(rawData.indexOf("|") + 3);

                // Add Tasks
                switch (type) {
                case "T":
                    Kitty.tasks.add(new Todo(task));
                    if (status.equals("1")) {
                        Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
                    }
                    break;
                case "D":
                    String deadlineName = task.substring(0, task.indexOf("|"));
                    String deadlineDate = task.substring(task.indexOf("|") + 1);
                    Kitty.tasks.add(new Deadline(deadlineName, deadlineDate));
                    if (status.equals("1")) {
                        Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
                    }
                    break;
                case "E":
                    String eventName = task.substring(0, task.indexOf("|"));
                    String eventDate = task.substring(task.indexOf("|") + 1);
                    Kitty.tasks.add(new Event(eventName, eventDate));
                    if (status.equals("1")) {
                        Kitty.tasks.get(Kitty.tasks.size() - 1).setDone();
                    }
                    break;
                default:
                    throw new KittyException("Invalid Raw Data!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new KittyException("File not found!");
        }
    }

    public static void writeNewLine(String text) throws KittyException{
        try {
            FileWriter fw = new FileWriter(DATA_PATH, true);
            fw.write(text);
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new KittyException("Invalid Input to Raw Data!");
        }

    }
}
