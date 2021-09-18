package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String rootDirectory = System.getProperty("user.dir");
    private static final String dataLocation = "data";
    private static final String dataFilename = "tasks.txt";
    private Path taskDataFilePath;

    public Storage() throws IOException{
        taskDataFilePath = checkAndSetData();
    }

    private static Path checkAndSetData() throws IOException {
        Path dataFolderPath = Paths.get(rootDirectory, dataLocation);
        boolean directoryExists = Files.exists(dataFolderPath);
        if(!directoryExists) {
            Files.createDirectory(dataFolderPath);
        }

        Path dataFilePath = Paths.get(dataFolderPath.toString(), dataFilename);
        boolean dataFileExists = Files.exists(dataFilePath);
        if(!dataFileExists) {
            Files.createFile(dataFilePath);
        }

        return dataFilePath;
    }

    public String[] readTaskData() throws FileNotFoundException {
        Scanner sc;
        ArrayList<String> rawTasks = new ArrayList<>();

        try {
            File dataFile = new File(taskDataFilePath.toString());
            sc = new Scanner(dataFile);
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("Cannot find the task data file");
        }

        while(sc.hasNext()) {
            rawTasks.add(sc.nextLine().strip());
        }

        String[] rawTasksString = new String[rawTasks.size()];

        return rawTasks.toArray(rawTasksString);
    }

    public void updateTaskFile(String fileDataToWrite) throws IOException{
        try {
            checkAndSetData();

            FileWriter fw = new FileWriter(taskDataFilePath.toString());
            fw.write(fileDataToWrite);
            fw.close();
        } catch(IOException e) {
            throw new IOException("Cannot write to task data file.");
        }
    }

}
