package jarvis;

import input.Input;
import storage.Storage;
import tasklist.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Jarvis {

    /**
     * This method runs the Jarvis bot by first greeting the user. It will then create
     * the list of task in an ArrayList followed by finding any saved task file that
     * has been created from prior usage. Then, it will loop to take in inputs from
     * the user and will send the inputs to the inputHandler method from Input class.
     * If the user inputs "bye", the loop will exit and the bot will print a bye message.
     */
    public void run() {
        Ui.printGreetMessage();
        ArrayList<Task> taskList = new ArrayList<>();
        Storage.findTaskFile(taskList);
        Scanner scanner = new Scanner(System.in);
        String userLine = scanner.nextLine().trim();
        while(!userLine.equalsIgnoreCase(Input.COMMAND_BYE)) {
            Input.inputHandler(userLine, taskList);
            userLine = scanner.nextLine().trim();
        }
        Ui.printByeMessage();
    }


    public static void main(String[] args) {
        Jarvis jarvis = new Jarvis();
        jarvis.run();
    }
}
