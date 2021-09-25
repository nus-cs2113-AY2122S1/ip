package Duke.Commands;

import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.Task.TaskList;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

import java.util.ArrayList;

public class AddTodoCommand {
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

    public static void manageTodo(String inWord, ArrayList<Task> taskList) {
        try {
            TaskList.addTodo(inWord);
            DataSaver.manageSave(taskList);
        } catch (DukeException emptyTodoException) {
            DukeException.emptyTodoException();
        }
    }
}
