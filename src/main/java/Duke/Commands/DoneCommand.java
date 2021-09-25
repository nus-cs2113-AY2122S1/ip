package Duke.Commands;

import Duke.BackEnd.DukeBackEnd;
import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.Task.TaskList;
import Duke.TaskTypes.Task;

import java.util.ArrayList;

public class DoneCommand {

    public static boolean isValidDoneInstruction(String inWord, ArrayList<Task> taskList) {
        if (!inWord.contains(" ")) {
            return false;
        }

        String[] commands = DukeParser.parseDoneInstruction(inWord);
        if (commands.length != 2) {
            return false;
        }

        if(DukeBackEnd.isNumeric(commands[1])) {
            int taskDoneIndex = Integer.parseInt(commands[1]);
            return taskDoneIndex > 0 && taskDoneIndex <= taskList.size();
        }

        return false;
    }

    public static void manageDoneInstruction(String inWord) {
        try {
            TaskList.printTaskDone(inWord);
        } catch (DukeException invalidDoneException){
            DukeException.invalidDoneException();
        }
    }
}
