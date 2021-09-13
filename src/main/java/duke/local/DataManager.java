package duke.local;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataManager {

    private final String filePath;
    private final File dataFile;

    public DataManager(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
    }

    public void printFileContents() throws FileNotFoundException{
        Scanner in = new Scanner(dataFile);

        while(in.hasNext()) {
            System.out.println(in.nextLine());
        }
    }

    public void loadDataFromFile() throws FileNotFoundException {

        Scanner in = new Scanner(dataFile);

        while(in.hasNext()) {
            System.out.println(in.nextLine());
        }

    }
}
