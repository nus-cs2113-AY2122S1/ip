package tan;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataManager {
    protected static BufferedReader csvReader;
    protected static FileWriter csvWriter;
    private static final String homePath = System.getProperty("user.home");

    public DataManager() {
        FileReader fileReader = getReader(homePath);
        csvReader = new BufferedReader(fileReader);
        System.out.println("End of test for Files.");
    }

    private static FileReader getReader(String homePath) {
        File file = null;
        FileReader reader;
        try {
            file = getFile(homePath);
            csvWriter = new FileWriter(file);
            reader = new FileReader(file);
        } catch (Exception exp) {
            reader = null;
        }

        if (reader == null) {
            System.out.println("Error Accessing data file. Please contact admin.");
            System.exit(-1);
        }

        return reader;
    }

    private static File getFile(String homePath) throws IOException {
        File file;
        Path dataPath = Paths.get(homePath,"taskData.csv");
        String pathString = dataPath.toString();
        file = new File(pathString);
        boolean isCreated = file.createNewFile();
        if (isCreated) {
            System.out.println("New data file has been created in your home Dir!");
        } else {
            System.out.println("Found data file!");
        }
        return file;
    }

    public static void Write(String x) {
        try {
            csvWriter.append(x);
        } catch (Exception e) {
            System.out.println("Error in writing");
        }

    }
}
