import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

// Check if the text file and the directory exists, if not, create one
public class CheckFileExistence {

    File file;

    //constructor of checkFileExistence object
    public CheckFileExistence(File file_input) {
        file = file_input;
    }

    //Check the existence of the text file and create one if the file does not exist
    public void checkExistence(File file) {
        String pathName =  "./data/";
        File directory = new File("./data/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + pathName + file.getName());
                System.out.println(UI.LINE);
            }
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            e.printStackTrace();
        }
    }
}
