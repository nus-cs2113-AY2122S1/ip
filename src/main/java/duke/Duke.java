package duke;

import duke.command.Command;
import duke.command.CommandHandler;
import duke.command.CommandParser;
import duke.fileio.TaskListFileEditor;
import duke.output.OutputHandler;
import duke.task.Task;

import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final int COMMAND_INDEX = 0;

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        TaskListFileEditor fileEditor = new TaskListFileEditor();

        CommandParser commandParser = new CommandParser();
        CommandHandler commandHandler = new CommandHandler();
        OutputHandler outputHandler = new OutputHandler();

        outputHandler.printWelcomeMessage();

        //initialize the command to default
        Command command = Command.DEFAULT;

        try {
            fileEditor.getTasksFromFile(tasks);
        } catch (FileNotFoundException e) {
            outputHandler.printFileNotFoundMessage();
        }

        while (!command.equals(Command.EXIT)) {
            String input = in.nextLine();
            String[] inputTokens = input.split(" ");
            command = commandParser.parseCommand(inputTokens[COMMAND_INDEX]);
            commandHandler.handleCommand(command, input, tasks);
        }
    }
}
