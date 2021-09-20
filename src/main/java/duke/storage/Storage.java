package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static duke.ui.Ui.NL;

public class Storage {
    private final File dataDirectory;
    private final File dataFile;

    public Storage(File dataDirectory, File dataFile) {
        this.dataDirectory = dataDirectory;
        this.dataFile = dataFile;
    }

    public ArrayList<Task> load() throws FileNotFoundException,
            SecurityException {
        if (!dataDirectory.isDirectory() && !dataDirectory.mkdirs()) {
            throw new SecurityException();
        }
        Scanner loadingScanner = new Scanner(dataFile);
        ArrayList<Task> loadedData = new ArrayList<>();
        while (loadingScanner.hasNext()) {
            String nextLine = loadingScanner.nextLine();
            Task loadedTask = extractTask(nextLine);
            if (loadedTask != null) {
                loadedData.add(loadedTask);
            }
        }
        return loadedData;
    }
    
    public void writeToData(Task task, int numberOfTasks) throws IOException {
        FileWriter fw = new FileWriter(dataFile, true);
        fw.write( ((numberOfTasks > 1) ? NL : "") + task.toData());
        fw.close();
    }
    
    public void refreshData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(dataFile, false);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toData() + ((i >= tasks.size() - 1) ? "" : NL));
        }
        fw.close();
    }
    
    private Task extractTask(String input) {
        String[] taskDetails = input.split("\\|");
        switch (taskDetails[0].trim()) {
        case "T":
            boolean isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            return new Todo(taskDetails[2].trim(), isDone);
        case "D":
            isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            return new Deadline(taskDetails[2].trim(), taskDetails[3].trim(), isDone);
        case "E":
            isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            return new Event(taskDetails[2].trim(), taskDetails[3].trim(), isDone);
        default:
            return null;
        }
    }
}
