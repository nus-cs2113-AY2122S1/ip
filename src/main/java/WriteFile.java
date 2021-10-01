import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Class To write text on files
 */
public class WriteFile {
   private String path;
   private boolean toAppend;

    /**
     *
     * @param file_path Path to the file
     * @param append boolean to decide whether to append or overwrite
     */
   public WriteFile(String file_path, boolean append) {
       path = file_path;
       toAppend = append;
   }

    /**
     *
     * @param textLine The line to write on file
     * @throws IOException
     */
   public void writeToFile(String textLine) throws IOException{
       FileWriter write = new FileWriter(path, toAppend);
       PrintWriter print_line = new PrintWriter(write);
       print_line.printf("%s" + "%n", textLine);
       print_line.close();
   }
}
