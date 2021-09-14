import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReading {
    public static void startupScanFileContents(TaskManager t1) throws IOException {
        File f = new File("./myDirectory/myFile.txt");
        Scanner s = new Scanner(f);
        int index = 0;
        while (s.hasNext()) {
            TaskProcessor.callTaskMethod(t1, s.nextLine(), index);
            index += 1;
        }

    }


}
