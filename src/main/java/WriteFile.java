import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {
   private String path;
   private boolean toAppend;
   public WriteFile(String file_path, boolean append) {
       path = file_path;
       toAppend = append;
   }

   public void writeToFile(String textLine) throws IOException{
       FileWriter write = new FileWriter(path, toAppend);
       PrintWriter print_line = new PrintWriter(write);
       print_line.printf("%s" + "%n", textLine);
       print_line.close();
   }
}
