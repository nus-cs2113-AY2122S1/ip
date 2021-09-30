import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class Duke {

    private UI ui;
    public String filePath = "./data/duke.txt";
    File file = new File(filePath);

    public Duke(String filePath) {
        UI ui = new UI();
        File file = new File(filePath);
    }   //Constructor of Duke object


    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }   //Main method


    public void run() {
        ui.printWelcome();
        ArrayList<String> taskName = new ArrayList<>();
        ArrayList<Integer> taskStatus = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> taskType = new ArrayList<>();
        LoadFromText loadFromText = new LoadFromText(file, output, taskName, taskStatus, taskType);
        output = loadFromText.loadOutput(output);
        taskName = loadFromText.loadTaskName(taskName);
        taskStatus = loadFromText.loadTaskStatus(taskStatus);
        taskType = loadFromText.loadTaskType(taskType);
        Parser parser = new Parser();
        parser.checkCommand(output, taskStatus, taskName, taskType, file);
    }   //The actual run process that user can input and get the corresponding output from Duke

}
