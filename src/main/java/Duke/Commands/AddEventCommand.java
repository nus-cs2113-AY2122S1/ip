package Duke.Commands;

import Duke.BackEnd.DukeParser;
import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.Task.TaskList;
import Duke.TaskTypes.Task;
import java.util.ArrayList;
import static Duke.UI.DukeConstants.EVENT_KEYWORD;

public class AddEventCommand {

    /**
     * Method checks if the user "Event" instruction is valid
     *
     * @param inWord the user instruction to be checked
     * @return true if user instruction is of a correct format, false otherwise
     */
    public static boolean isValidEvent(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseEventInstruction(inWord);
        if (commands.length != 2 || !inWord.contains(EVENT_KEYWORD)) {
            return false;
        }

        String[] description = commands[1].split(EVENT_KEYWORD, 2);
        if (description.length != 2) {
            return false;
        }

        String descriptionDetails = description[0].trim();
        String descriptionAt = description[1].trim();
        boolean isNonEmptyDetails = !descriptionDetails.isEmpty();
        boolean isNonEmptyAt = !descriptionAt.isEmpty();
        if (isNonEmptyDetails && isNonEmptyAt) {
            return true;
        } else {
            return true;
        }
    }

    /**
     * Method to add Event from instruction to task list
     * and to the save file
     *
     * @param inWord The user instruction
     * @param taskList the current list of user's tasks
     */
    public static void manageEvent(String inWord, ArrayList<Task> taskList) {
        try {
            TaskList.addEvent(inWord);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidEventException) {
            DukeException.invalidEventException();
        }
    }
}
