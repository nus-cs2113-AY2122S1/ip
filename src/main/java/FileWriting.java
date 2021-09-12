import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriting {

    public static void writeToFile(String content) throws IOException {
        FileWriter fw = new FileWriter("myFile.txt", true);
        fw.write(content);
        fw.close();
    }

    public static void initialise() {
        try {
            File f = new File("myFile.txt");
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

}
