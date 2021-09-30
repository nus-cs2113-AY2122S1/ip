import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadFromText {

    File file;
    ArrayList<String> output;
    ArrayList<String> taskName;
    ArrayList<Integer> taskStatus;
    ArrayList<String> taskType;

    public LoadFromText(File file1, ArrayList<String> Output, ArrayList<String> TaskName, ArrayList<Integer> TaskStatus
            , ArrayList<String> TaskType) {
        ArrayList<String> output = new ArrayList<>(Output);
        ArrayList<String> taskName = new ArrayList<>(TaskName);
        ArrayList<Integer> taskStatus = new ArrayList<Integer>(TaskStatus);
        ArrayList<String> taskType = new ArrayList<String>(TaskType);
        file = file1;
    }   // Constructor of LoadFromText


    public ArrayList<String> loadOutput(ArrayList<String> output) {
        try {
            String st;
            Scanner tasks = new Scanner(file);  // Create a Scanner object
            while (tasks.hasNext()) {
                st = tasks.nextLine();
                output.add(st);
            }
            return output;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred, please try again!");
            return null;
        }
    }   // Load the corresponding text from txt file to the ArrayList output

    public ArrayList<String> loadTaskName(ArrayList<String> taskName) {
        try {
            String st;
            Scanner tasks = new Scanner(file);  // Create a Scanner object
            while (tasks.hasNext()) {
                st = tasks.nextLine();
                taskName.add(st.substring(9));
            }
            return taskName;
        } catch (FileNotFoundException e) {
            System.out.println("A FileNotFound error occurred, please try again!");
            return null;
        } catch (IOException e) {
            System.out.println("An IO Exception error occurred, please try again!");
            return null;
        }
    }   // Load the corresponding text from txt file to the ArrayList taskName

    public ArrayList<Integer> loadTaskStatus(ArrayList<Integer> taskStatus) {
        try {
            String st;
            Scanner tasks = new Scanner(file);  // Create a Scanner object
            while (tasks.hasNext()) {
                st = tasks.nextLine();
                if (st.substring(6, 7).equals("X")) {
                    taskStatus.add(1);
                } else {
                    taskStatus.add(0);
                }
            }
            return taskStatus;
        } catch (FileNotFoundException e) {
            System.out.println("A FileNotFound error occurred, please try again!");
            return null;
        } catch (IOException e) {
            System.out.println("An IO Exception error occurred, please try again!");
            return null;
        }
    }   // Load the corresponding text from txt file to the ArrayList taskStatus

    public ArrayList<String> loadTaskType(ArrayList<String> taskType) {
        try {
            String st;
            Scanner tasks = new Scanner(file);  // Create a Scanner object
            while (tasks.hasNext()) {
                st = tasks.nextLine();
                taskType.add(st.substring(3, 4));
            }
            return taskType;
        } catch (FileNotFoundException e) {
            System.out.println("A FileNotFound error occurred, please try again!");
            return null;
        } catch (IOException e) {
            System.out.println("An IO Exception error occurred, please try again!");
            return null;
        }
    }   // Load the corresponding text from txt file to the ArrayList taskType
}
