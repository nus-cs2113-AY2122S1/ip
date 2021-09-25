package Duke.Commands;

import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.Task.TaskList;
import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Task;

import java.util.ArrayList;

import static Duke.UI.DukeConstants.DEADLINE_KEYWORD;

public class AddDeadlineCommand {
    public static boolean checkValidDeadline(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseDeadlineInstruction(inWord);
        if (commands.length != 2 || !inWord.contains(DEADLINE_KEYWORD)) {
            return false;
        }

        String[] description = commands[1].split(DEADLINE_KEYWORD, 2);
        if (description.length != 2) {
            return false;
        }

        String descriptionDetails = description[0].trim();
        String descriptionBy = description[1].trim();
        return !descriptionDetails.isEmpty() && !descriptionBy.isEmpty();
    }

    public static void manageDeadline(String inWord, ArrayList<Task> taskList) {
        try {
            TaskList.addDeadline(inWord);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidDeadlineException) {
            DukeException.invalidDeadlineException();
        }
    }
}
