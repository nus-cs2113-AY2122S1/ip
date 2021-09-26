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

    public static void main(String[] args) {
        Interface.introductoryMessage();
        ProcessFiles.LoadTasks();
        runIkaros();
        ProcessFiles.SaveTasks();
        Interface.goodbyeMessage();
    }

    /**
     * Runs Ikaros, manages the instructions of ikaros
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