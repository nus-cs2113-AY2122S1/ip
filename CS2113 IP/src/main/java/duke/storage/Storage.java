package duke.storage;

import duke.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    final private static String DIRECTORY_CREATED = "data directory created successfully >>::))";
    final private static String DIRECTORY_EXISTS = "Duke-data directory exists >>::))";
    final private static String DATABASE_CREATED = "Duke database created >>::))";
    final private static String DATABASE_EXISTS = "Duke database up-to-date! >>::))";
    final private static String filePath = getFilePath();

    public Storage() {
    }

    public void createDirectory() {
        File f = load();
        boolean isCreated = f.mkdir();
        if (isCreated) {
            System.out.println(DIRECTORY_CREATED);
        } else {
            System.out.println(DIRECTORY_EXISTS);
        }
    }

    public void createFile() throws IOException {
        File f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println(DATABASE_CREATED);
        } else {
            System.out.println(DATABASE_EXISTS);
        }
        Ui.showHorizontalLine();
    }

    public File load() {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        File f = new File(currentPath + "/data");
        return f;
    }

    public static String getFilePath() {
        Path currentRelativePath = Paths.get("");
        Path currentPath = currentRelativePath.toAbsolutePath();
        String filePath = currentPath + "/data/duke.txt";
        return filePath;
    }

    public void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (int i = 0; i < TaskList.getTaskCount(); i++) {
            boolean isEvent = tasks.get(i).taskType.equals("E");
            boolean isDeadline = tasks.get(i).taskType.equals("D");
            String formatDescription;
            if (isEvent) {
                formatDescription = String.format("%s /at %s",
                        tasks.get(i).specificDescription, tasks.get(i).date);
            } else if (isDeadline) {
                formatDescription = String.format("%s /by %s",
                        tasks.get(i).specificDescription, tasks.get(i).deadline);
            } else {
                formatDescription = tasks.get(i).description;
            }

            String formatToWrite = String.format("%s | %s | %s\n",
                    tasks.get(i).taskType, tasks.get(i).getStatusIcon(), formatDescription);
            fw.write(formatToWrite);
        }
        fw.close();
    }
}
