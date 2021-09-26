package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// File Manager
public class Storage {

    public static int readFile() throws FileNotFoundException { // return ArrayList?
        File dir = new File("Duke");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File data = new File("Duke/data.txt");
        if (data.exists()) {
            Scanner s = new Scanner(data);
            int currCount = 0;
            while (s.hasNext()) {
                ArrayList<Task> tasks = parseDataFromFile(s.nextLine(), currCount);
                currCount += 1;
            }
            return currCount;
        }
        else {
            System.out.println("Creating new data file....");
            try {
                data.createNewFile();
                System.out.println("New data file created");
            } catch (IOException e) {
                System.out.println("Unable to create new data file");
            }
        }
        return 0; // initial value of currCount
    }

    public static ArrayList<Task> parseDataFromFile(String nextLine, int currCount) {
        ArrayList<Task> tasks = new ArrayList<>();
        String[] input = nextLine.split("\\|"); // necessary to escape regex meta character
        String[] trimmedInput = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            trimmedInput[i] = input[i].trim();
        }
        switch (trimmedInput[0]) {
        case "T":
            tasks.add(currCount, TaskList.addTodo(trimmedInput[2]));
            if (trimmedInput[1].equals("1")) {
                tasks.get(currCount).markAsDone();
            }
            break;
        case "D":
            tasks.add(currCount, TaskList.addDeadline(trimmedInput[2], trimmedInput[3]));
            if (trimmedInput[1].equals("1")) {
                tasks.get(currCount).markAsDone();
            }
            break;
        case "E":
            tasks.add(currCount, TaskList.addEvent(trimmedInput[2], trimmedInput[3]));
            if (trimmedInput[1].equals("1")) {
                tasks.get(currCount).markAsDone();
            }
            break;
        }
        return tasks;
    }

    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("Duke/data.txt");
            for (Task t: tasks) {
                // System.out.println(t.parseDataIntoString());
                String fileOutput = t.parseDataIntoString() + System.lineSeparator();
                fw.write(fileOutput);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }
}
