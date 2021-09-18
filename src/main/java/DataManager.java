import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataManager {
    private final String path;

    public DataManager(String path) {
        this.path = path;
    }

    /*examples of file content
   T | 1 | read book
   E | 0 | project meeting | Aug 6th 2-4pm
   params[0] - Type, param[1] - status, param[2] - description, param[3] - time(if applicable) */
    private void readFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] params = line.split("\\s\\|\\s");
            switch (params[0]) {
            case "T":
                TaskManager.addTodo(params[2]);
                break;
            case "D":
                TaskManager.addDeadline(params[2], params[3]);
                break;
            case "E":
                TaskManager.addEvent(params[2], params[3]);
                break;
            }
            if (params[1].equals("1")) {
                TaskManager.taskDoneLatest();
            }
        }
    }

    private void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void writeFileContents(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.createNewFile()) {
            Ui.printFileCreatedMessage();
        }
        //clear contents of file
        String textToAppend;
        FileWriter fw = new FileWriter(filePath, false);// create a FileWriter in override mode
        fw.write("");
        for (int i = 0; i < TaskManager.tasks.size(); i++) {
            Task task = TaskManager.tasks.get(i);
            textToAppend = task.getType() + " | " + task.getStatus() + " | " + task.getDescription() + " | " + task.getTime() + System.lineSeparator();
            appendToFile(filePath, textToAppend);
        }
    }

    public void loadData() {
        try {
            readFileContents(this.path);
        } catch (FileNotFoundException e) {
            Ui.printNoDataFoundMessage();
        }
    }

    public void saveData() {
        try {
            Path path = Paths.get(this.path);
            Files.createDirectories(path.getParent()); //make directory
            writeFileContents(this.path);//write to file, create if haven't
            Ui.printDataSavedMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
