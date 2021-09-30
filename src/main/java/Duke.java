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
    }


    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }


    public void run() {
        ui.printWelcome();
        ArrayList<String> arrayInput = new ArrayList<>();
        ArrayList<Integer> taskStatus = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        ArrayList<String> taskType = new ArrayList<>();
        LoadFromText loadFromText = new LoadFromText(file, output, arrayInput, taskStatus, taskType);
        output = loadFromText.loadOutput(output);
        arrayInput = loadFromText.loadArrayInput(arrayInput);
        taskStatus = loadFromText.loadTaskStatus(taskStatus);
        taskType = loadFromText.loadTaskType(taskType);
        Parser parser = new Parser();
        parser.checkCommand(output, taskStatus, arrayInput, taskType, file);
    }

}
