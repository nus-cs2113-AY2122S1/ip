package Duke.Commands;

import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.Task.TaskList;
import Duke.TaskTypes.Task;

import java.util.ArrayList;

public class AddTodoCommand {
    /**
     * Method checks if the user "Todo" instruction is valid
     *
     * @param inWord the user instruction to be checked
     * @return true if user instruction is of a correct format, false otherwise
     */
    public static boolean checkValidTodo(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseTodoInstruction(inWord);
        String details = commands[1];
        boolean isNonEmptyDetails = !details.isEmpty();
        return commands.length == 2 && isNonEmptyDetails;
    }

    /**
     * Method to add Todo from instruction to task list
     * and to the save file
     *
     * @param inWord The user instruction
     * @param taskList the current list of user's tasks
     */
    public static void manageTodo(String inWord, ArrayList<Task> taskList) {
        try {
            TaskList.addTodo(inWord);
            DataSaver.manageSave(taskList);
        } catch (DukeException emptyTodoException) {
            DukeException.emptyTodoException();
        }
    }
}
