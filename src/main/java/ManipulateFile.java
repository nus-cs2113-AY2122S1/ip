import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.lineSeparator;

public class ManipulateFile {
    public static void main(String[] args) throws IOException {
        String filePath = "data/duke.txt";
        String textToAdd = "data/duke.txt";
        File f = new File("data/duke.txt");
        if (!f.exists()){
            f.createNewFile();
        }
        if (!f.exists()){
            f.mkdirs();
        }
//        f.createNewFile();
//        f.mkdirs();

        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd + lineSeparator());
        fw.write(textToAdd);
        for(Task task: Greet.getList()){
            
        }
        fw.close();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
        System.out.println("full path: " + f.getAbsolutePath());
        System.out.println("file exists?: " + f.exists());
        System.out.println("is Directory?: " + f.isDirectory());
    }
}
