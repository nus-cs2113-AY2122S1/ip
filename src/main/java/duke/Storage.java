package duke;

//for reading file
import java.io.File;
import java.io.FileNotFoundException;
//for writing to file
import java.io.FileWriter;
import java.io.IOException;

public class Storage {

    File directory;
    File file;

    //constructor
    public Storage() {
        //create directory if it does not exist
        directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        //create txt file if it does not exist
        file = new File("data/data.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }







}
