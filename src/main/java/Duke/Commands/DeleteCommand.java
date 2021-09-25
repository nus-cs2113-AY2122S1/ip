package Duke.Commands;

import Duke.BackEnd.DukeBackEnd;
import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.Task.TaskList;
import Duke.TaskTypes.Task;
import java.util.ArrayList;


public class DeleteCommand {

    /**
     * Method checks if the user "Delete" instruction is valid
     *
     * @param inWord The user instruction to be checked
     * @param taskList The current list of user's task
     * @return true if user instruction is of a correct format, false otherwise
     */
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

    /**
     * Method to Delete from task list
     * and from the save file
     *
     * @param inWord The user instruction
     * @param taskList the current list of user's tasks
     */
    public static void manageDelete(String inWord, ArrayList<Task> taskList) {
        try {
            TaskList.deleteTask(inWord);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidDeleteException) {
            DukeException.invalidDeleteException();
        }
    }
}
