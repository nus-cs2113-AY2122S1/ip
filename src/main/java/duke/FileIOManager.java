package duke;
import java.io.*;
import java.util.ArrayList;
import duke.task.Todo;

import java.util.Scanner;

public class FileIOManager {
    public static void write(ArrayList<Todo> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
        //int count = 1;
        for (Todo task : tasks) {
            //writer.write(count+"."+task.toString());
            writer.write(task.toString());
            writer.newLine();
            //count++;
        }
        writer.flush();
        writer.close();
    }
    public static ArrayList<String> read() throws FileNotFoundException {
        ArrayList<String> tasksString = new ArrayList<String>();
        File tasksFile = new File("tasks.txt");
        Scanner reader = new Scanner(tasksFile);
        while (reader.hasNextLine()){
            String taskString = reader.nextLine();
            tasksString.add(taskString);
        }
        return tasksString;
    }
}
