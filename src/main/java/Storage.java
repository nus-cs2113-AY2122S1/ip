import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        if (!textToAppend.equals("bye")) {
            fw.write(textToAppend + "\n");
            fw.close();
        }
    }

}
