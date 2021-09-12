package duke;

import duke.task.TaskManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;

public class FileHandler {

    private String fileDirectory;

    public FileHandler(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public void writeToFile(String fileName, String contents) throws IOException {
        File directory = new File(fileDirectory);
        directory.mkdir();

        FileWriter fw = new FileWriter(new File(fileDirectory, fileName));
        fw.write(contents);
        fw.close();
    }

    /**
     * Pass inputs from file to task manager to process into the tasks list.
     *
     * @param fileName Given file name that contains the many tasks information.
     * @param taskManager TaskManager that handles any task related operation.
     */
    public void loadToTaskManager(String fileName, TaskManager taskManager) {
        File f = new File(fileDirectory, fileName);
        try{
            Scanner s = new Scanner(f);
            while(s.hasNext()){
                taskManager.addTaskFromFile(s.nextLine());
            }
        }catch(FileNotFoundException e){
            System.out.printf("Notice: File %s not found.\n",fileName);
            return;
        }
    }

}
