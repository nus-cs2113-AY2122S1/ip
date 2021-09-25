package Duke.Commands;

import Duke.BackEnd.DukeBackEnd;
import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.Task.TaskList;
import Duke.TaskTypes.Task;

import java.util.ArrayList;

public class DoneCommand {

    /**
     * Method checks if the user "Done" instruction is valid
     *
     * @param inWord The user instruction to be checked
     * @param taskList The current list of user's task
     * @return true if user instruction is of a correct format, false otherwise
     */
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

    /**
     * Method to mark instruction as done in task list
     * and in the save file
     *
     * @param inWord The user instruction
     * @param taskList the current list of user's tasks
     */
    public static void manageDoneInstruction(String inWord, ArrayList<Task> taskList) {
        try {
            TaskList.printTaskDone(inWord);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidDoneException){
            DukeException.invalidDoneException();
        }
    }
}
