import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static TaskList readFileContents(String filePath) {
        File f = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String line = s.nextLine();
                    if (line.startsWith("E")) {
                        int startDate = line.indexOf('|');
                        String description = line.substring(4, startDate - 1);
                        String date = line.substring(startDate + 2);
                        Event newTask = new Event(description, date);
                        taskList.add(newTask);
                        if (line.substring(2).startsWith("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        }
                    } else if (line.startsWith("D")) {
                        int startDate = line.indexOf('|');
                        String description = line.substring(4, startDate - 1);
                        String date = line.substring(startDate + 2);
                        Deadline newTask = new Deadline(description, date);
                        taskList.add(newTask);
                        if (line.substring(2).startsWith("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        }
                    } else {
                        String description = line.substring(4);
                        Todo newTask = new Todo(description);
                        taskList.add(newTask);
                        if (line.substring(2).startsWith("1")) {
                            taskList.get(taskList.size() - 1).setDone();
                        }
                    }
                }
            } return new TaskList(taskList);
        } catch (Exception e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void writeToFile(String filePath, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int i = 0;
        while(i < taskList.size()) {
            fw.write(taskList.get(i).saveToFile() + System.lineSeparator());
            i += 1;
        }
        fw.close();
    }
}
