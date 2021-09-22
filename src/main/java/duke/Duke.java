package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.*;
import duke.taskmanager.Command;
import duke.taskmanager.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        // Initialize variables for program startup
        Ui.printGreetingMessage();
        String userInput;
        ArrayList<Task> list = new ArrayList<>();
        Storage.loadSaveData(list);
        Ui.printList(list);
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine().trim();
            Command command = Parser.parseUserCommand(userInput);
            if (command.equals(Command.BYE)) {
                Ui.printGoodbyeMessage();
                Storage.saveToFile(list);
                break;
            }
            TaskList.executeCommand(userInput, list, command);
        }
    }
}
