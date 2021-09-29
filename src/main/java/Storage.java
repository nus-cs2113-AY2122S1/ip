import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;



public class Storage {

    public static final String FILE_PATH = "/Users/cheyen_air/Desktop/GitHub/ip/data/dukedata.txt";
    public static final String FILE_DIREC = "/Users/cheyen_air/Desktop/GitHub/ip/data";



    public Storage() {}

    public void checkFile() {
        try {
            loadFile();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadFile() throws IOException {
        File tempFile = new File(FILE_PATH);
        if (!tempFile.exists()) {
            File dir = new File(FILE_DIREC);
            dir.mkdir();
            File newFile = new File("/Users/cheyen_air/Desktop/GitHub/ip/data/dukedata.txt");
            newFile.createNewFile();
        }
    }

    public void saveFile(ArrayList<Task> schedule, int totalTasks) {
        try {
            writeFile(schedule, totalTasks);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeFile(ArrayList<Task> schedule, int totalTasks) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);

        for (int i = 0; i < totalTasks; i++) {
            fw.write(schedule.get(i).fileForm());
        }
        fw.close();
    }

}
