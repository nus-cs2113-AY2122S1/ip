package duke;

import duke.Ui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Storage.loadData(tasks);
        Ui.printHello();
        String userInput;
        Scanner s = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye) {
            userInput = s.nextLine();
            String command = Parser.getCommand(userInput);
            if (command.equals("bye")) {
                isBye = true;
                try {
                    Storage.saveData(tasks);
                } catch (IOException e) {
                    System.out.println("cannot save to file");
                }
            } else {
                Parser.task(userInput, tasks);
            }
        }
        Ui.printBye();
    }
}

