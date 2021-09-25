package karlett.storage;

import karlett.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StorageFile {
    public static String filePath = "karlett.txt";
    public static File file = new File(filePath);

    public static void loadDataFromStorageFile() throws IOException {
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String task = s.nextLine();
                TaskListDecoder.processFileData(task);
            }
            TextUi.printFinishLoadingDataMessage();
        } catch (FileNotFoundException e) {
            TextUi.printFileNotFoundMessage();
        }
    }
}
