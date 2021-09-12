import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReading {
    public static void startupScanFileContents(TaskManager t1) throws IOException {
        File f = new File("myFile.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            TaskProcessor.callTaskMethod(t1, s.nextLine());
        }

    }


}
