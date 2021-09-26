package Duke;
import Task.Task;
import Task.Todo;
import Task.Deadline;
import Task.Event;
import Duke.Ui;
import Duke.Parser;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {



    /*private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i=0; i<taskSum; i++){
            Task now = tasks.get(i);
            fw.write(String.format("%d ",i+1));
            fw.write(now.toString());
            fw.write("\n");
        }
        fw.close();
    }*/

    public static void main(String[] args) {
        Ui.printLogo();
        Ui.greeting();
        String command;
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        String f = "data/lines.txt";
            /*try {
                writeToFile(f);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }*/
        do{
            command = in.nextLine();
            flag = Parser.parse(command);
        }while (flag);
    }
}



