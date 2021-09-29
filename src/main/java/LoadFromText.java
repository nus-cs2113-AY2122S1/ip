import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFromText {

    File file;
    ArrayList<String> output;
    public LoadFromText(File file1, ArrayList<String> Output) {
        ArrayList<String> output = new ArrayList<>(Output);
        file = file1;
    }


    public ArrayList<String> load(ArrayList<String> output){
        try{
            //BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            Scanner tasks = new Scanner(file);  // Create a Scanner object
            while(tasks.hasNext()){
                st = tasks.nextLine();
                output.add(st);
            }
            System.out.println("execute");
            return output;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred, please try again!");
            return null;
        } catch (IOException e) {
            System.out.println("An error occurred, please try again!");
            return null;
        }
    }
}
