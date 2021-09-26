package duke;

import duke.processes.commands.ByeCommand;
import duke.processes.commands.Command;
import duke.processes.commands.CommandResult;
import duke.processes.tasks.Task;
import duke.processes.utility.Interface;
import duke.processes.utility.Parser;
import duke.processes.utility.ProcessFiles;

import java.util.ArrayList;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * main code to run the overall processes of the entire program. Uses the runIkaros
     * function to run the bulk of the task assistant ikaros
     *
     * @param args an array of sequence of characters (Strings) that are passed to the
     *             "main" function.
     */
    public static void main(String[] args) {
        Interface.introductoryMessage();
        ProcessFiles.loadFile();
        runIkaros();
        ProcessFiles.saveTasks();
        Interface.goodbyeMessage();
    }

    /**
     * Runs Ikaros and manages the input of the user and the response of the
     * program. Main code running the bulk of the program
     */
    protected static void runIkaros() {
        boolean isRunning = true;

        String response;
        Command command;
        CommandResult feedback;
        while (isRunning) {
            do {
                response = Interface.readInput();
                System.out.println(Interface.lineBreak);
                command = Parser.parseCommand(response);
                feedback = command.execute();
                System.out.println(feedback.feedbackToUser);
                System.out.println(Interface.lineBreak);
            } while (ByeCommand.isRunning);
            response = Interface.readInput();
            if (response.equalsIgnoreCase("y")) {
                isRunning = false;
            } else {
                System.out.println(Interface.lineBreak + System.lineSeparator() +
                        "No problem, what further assistance " + "do you require?");
            }
        }
    }
}