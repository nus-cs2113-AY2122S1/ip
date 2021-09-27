package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Storage.loadTaskFile(tasks);
        Ui.greetUser();
        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isBye = false;

        while (!isBye) {
            userInput = in.nextLine();
            String command = Parser.getCommand(userInput);
            if (command.equals("bye")) {
                isBye = true;
                Ui.printFarewellMessage();
                Storage.saveTaskFile(tasks);
            } else {
                Parser.parseCommand(userInput, tasks);
            }
        }
    }
}