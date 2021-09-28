import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Storage {
    /**
     * Writes content into the file myFile.txt.
     * @param content the content to be written into the file
     * @throws IOException
     */
    public static void writeToFile(String content) throws IOException {
        FileWriter fw = new FileWriter("./myDirectory/myFile.txt", true);
        fw.write(content);
        fw.close();
        copyToFile();
    }

    /**
     * Copies all the contents of myFile.txt into myFileCopy.txt.
     * @throws IOException
     */
    private static void copyToFile() throws IOException {
        Files.copy(Paths.get("./myDirectory/myFile.txt"),
                Paths.get("./myDirectory/myFileCopy.txt"), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Deletes the storage content of the task at index index.
     * @param index the line in the file myFile.txt to be deleted
     * @throws IOException
     */
    public static void deleteEntry(int index) throws IOException {
        File f1 = new File("./myDirectory/myFileCopy.txt");
        FileWriter fwClear = new FileWriter("./myDirectory/myFile.txt");
        fwClear.write("");
        fwClear.close();
        FileWriter fw = new FileWriter("./myDirectory/myFile.txt", true);
        Scanner s = new Scanner(f1);
        int i = 0;
        while (s.hasNextLine()) {
            if (i != index) {
                String word = s.nextLine() + System.lineSeparator();
                fw.write(word);
            } else {
                String skip = s.nextLine();
                fw.write("");
            }
            i += 1;
        }
        fw.close();
        s.close();
        copyToFile();
    }

    /**
     * Changes the content of the file myFile.txt at line index to show the task as done
     * @param index the line to be overwritten as a completed task
     * @throws IOException
     */
    public static void markEntryDone(int index) throws IOException {
        File f1 = new File("./myDirectory/myFileCopy.txt");
        FileWriter fwClear = new FileWriter("./myDirectory/myFile.txt");
        fwClear.write("");
        fwClear.close();
        FileWriter fw = new FileWriter("./myDirectory/myFile.txt", true);
        Scanner s = new Scanner(f1);
        int i = 0;
        while (s.hasNextLine()) {
            if (i != index) {
                String word = s.nextLine() + System.lineSeparator();
                fw.write(word);
            } else {
                String skip = s.nextLine();
                String[] words = skip.split("\\|");
                words[1] = "1";
                String together = words[0].trim() + " | " + words[1].trim() + " | " + words[2].trim();
                if (words.length > 3) {
                    together = together + " | " + words[3].trim();
                }
                fw.write(together + System.lineSeparator());
            }
            i += 1;
        }
        fw.close();
        s.close();
        copyToFile();
    }

    /**
     * Creates the directory myDirectory and the files myFile.txt and myFileCopy.txt if
     * they have not been created yet.
     */
    public static void initialiseFiles() {
        try {
            File dir = new File("./myDirectory");
            if (dir.mkdir()) {
                System.out.println("Directory created: " + dir.getName());
            }
            File f = new File("./myDirectory/myFile.txt");
            File f1 = new File("./myDirectory/myFileCopy.txt");
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            }
            if (f1.createNewFile()) {
                System.out.println("File created: " + f1.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

    /**
     * Reads in the input from the file myFile.txt and sends each line to the task processor.
     * @throws IOException
     */
    public static void startupScanFileContents() throws IOException {
        File f = new File("./myDirectory/myFile.txt");
        Scanner s = new Scanner(f);
        int index = 0;
        while (s.hasNext()) {
            TaskProcessor.callTaskMethod(s.nextLine(), index);
            index += 1;
        }
    }
}
