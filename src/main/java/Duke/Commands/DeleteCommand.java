package Duke.Commands;

import Duke.BackEnd.DukeBackEnd;
import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.Task.TaskList;
import Duke.TaskTypes.Task;

import java.util.ArrayList;

import static Duke.UI.UserInterface.deleteMessage;

public class DeleteCommand {


    public static boolean isValidDeleteInstruction(String inWord, ArrayList<Task> taskList) {
        if (!inWord.contains(" ")) {
            return false;
        }

        String[] commands = DukeParser.parseDeleteInstruction(inWord);
        if (commands.length != 2) {
            return false;
        }

        if(DukeBackEnd.isNumeric(commands[1])) {
            int taskDoneIndex = Integer.parseInt(commands[1]);
            return taskDoneIndex > 0 && taskDoneIndex <= taskList.size();
        }
        return false;
    }


    public static void manageDelete(String inWord, ArrayList<Task> taskList) {
        try {
            TaskList.deleteTask(inWord);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidDeleteException) {
            DukeException.invalidDeleteException();
        }
    }
}
