package tan;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;


/**
 * HOW TO USE??
 *
 * Call setWriterAndReader to set the csvReader & csvWriter.
 * Then use respective reader/writer to perform task.
 *
 * Look thru code to learn
 *
 */
public class DataManager {
    protected static BufferedReader csvReader;
    protected static BufferedWriter csvWriter;
    private static final String homePath = System.getProperty("user.home");
    private static final String[] TITLE = {"Type", "Description", "Date"};
    private static final String FILE_NAME = "taskData.csv";


    public DataManager() {
        //FileReader fileReader = getReader(homePath);
        //csvReader = new BufferedReader(fileReader);
        //System.out.println("End of test for Files.");
    }

    public static void main(String[] args) {
        try {
            setWriterAndReader(homePath);
            csvWriter.append(String.join(",", TITLE));
            csvWriter.append("\n");
            String line;
            while ((line = csvReader.readLine()) != null) {
                String output = line.replace(',',' ');
                System.out.println(output);
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {
            System.out.println("Error :");
            e.printStackTrace();
        }
    }

    private static void setWriterAndReader(String homePath) throws IOException {
        Path dataPath = Paths.get(homePath, FILE_NAME);
        boolean isExists = Files.exists(dataPath);
        //Bottom line sets writer if can find file, else creates file then sets.
        csvWriter = Files.newBufferedWriter(dataPath, CREATE);
        if (isExists) {
            System.out.println("Found data file!");
        } else {
            System.out.println("New data file has been created in your home Dir!");
        }
        csvReader = Files.newBufferedReader(dataPath);
    }

    public static void Write(String x) {
        try {
            csvWriter.append(x);
        } catch (Exception e) {
            System.out.println("Error in writing");
        }

    }
}
