import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private UI ui;
    private String filePath;
    private ArrayList<String> output;
    File file;

    public Storage(String FilePath, ArrayList<String> Output, File file1) {
        filePath = FilePath;
        ArrayList<String> output = new ArrayList<String>(Output);
        file = file1;
    }


    public void writeTasksToFile(File file, ArrayList<String> output) {
        File directory = new File("D:/data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("D:/data/duke.txt");
            for (int i = 0; i < output.size(); i++) {
                myWriter.write(output.get(i));
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            System.out.println(ui.LINE);
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            e.printStackTrace();
        }
    }
}
