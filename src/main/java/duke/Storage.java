package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public Storage(String filePath) throws IOException{
        File dir = new File("data");
        dir.mkdirs();
        File tmp = new File(dir, "duke.txt");
        tmp.createNewFile();
    }

    public void load() throws IOException {
        //create duke.txt in folder named 'data'
        //idea from https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
        File dir = new File("data");
        dir.mkdirs();
        File tmp = new File(dir, "duke.txt");
        tmp.createNewFile();
        String save = "";
    }


    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
