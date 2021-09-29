package duke;

import duke.processes.Parser;
import duke.processes.Storage;
import duke.processes.TaskList;
import duke.processes.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException {
        TaskList tasks = new TaskList();
        Storage.loadTasks(tasks);
        Ui.printWelcomeMessage();
        activeChat(tasks);
        Storage.saveTasks(tasks);
        Ui.printGoodbyeMessage();
    }

    private static void activeChat(TaskList tasks) {
        boolean isBye = false;
        String input;
        Scanner in = new Scanner(System.in);
        while(!isBye){
            //store input into String
            input = in.nextLine();
            //process input
            isBye = Parser.processInput(tasks, input);
        }
    }

}
