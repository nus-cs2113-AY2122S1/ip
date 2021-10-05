package duke;

import java.io.*;
import java.util.ArrayList;

import duke.task.Todo;

import java.util.Scanner;

public class Storage {

    /**
     * Writes the elements in ArrayList, tasks to an output file called "tasks.txt".
     *
     * @param tasks ArrayList which contains the most updated information about the tasks.
     * @throws IOException If the filename of the file to be written to is a directory rather than a regular file.
     */
    public static void write(ArrayList<Todo> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
        int count = 1;
        for (Todo task : tasks) {
            writer.write(count + "." + task.toString());
            writer.newLine();
            count++;
        }
        writer.flush();
        writer.close();
    }

    /**
     * Reads file which should contain descriptions of tasks.
     * Each from the file is converted into a string and added into an array.
     *
     * @return A array of strings which are descriptions of tasks.
     * @throws FileNotFoundException
     */
    public static ArrayList<String> read() throws FileNotFoundException {
        ArrayList<String> tasksString = new ArrayList<String>();
        File tasksFile = new File("tasks.txt");
        Scanner reader = new Scanner(tasksFile);
        while (reader.hasNextLine()) {
            String taskString = reader.nextLine();
            tasksString.add(taskString);
        }
        return tasksString;
    }
}


