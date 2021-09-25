package jarvis;

import command.Command;
import storage.Storage;
import tasklist.Task;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Jarvis {
    public void run() {
        Ui.printGreetMessage();
        ArrayList<Task> taskList = new ArrayList<>();
        Storage.findTaskFile(taskList);
        Scanner scanner = new Scanner(System.in);
        String userLine = scanner.nextLine().trim();
        while(!userLine.equalsIgnoreCase(Command.COMMAND_BYE)) {
            Command.commandHandler(userLine, taskList);
            userLine = scanner.nextLine().trim();
        }
        Ui.printByeMessage();
    }

    public static void main(String[] args) {
        Jarvis jarvis = new Jarvis();
        jarvis.run();
    }
}
