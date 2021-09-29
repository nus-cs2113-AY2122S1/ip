package Duke.BackEnd;

import Duke.Commands.AddDeadlineCommand;
import Duke.Commands.AddEventCommand;
import Duke.Commands.AddTodoCommand;
import Duke.Commands.DeleteCommand;
import Duke.Commands.DoneCommand;
import Duke.Commands.FindCommand;
import Duke.UI.UserInterface;
import static Duke.RunFile.DukeProgram.taskList;
import static Duke.UI.DukeConstants.*;

public class DukeBackEnd {

    /**
     * Method checks whether a string is numeric
     * upon conversion to integer
     *
     * @param strNum the string to be checked
     * @return true if string is numeric upon conversion to integer, false otherwise
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Method extracts out the specific instruction type from user
     * input and executes the program accordingly
     *
     * @param inWord the user input
     */
    public static void executeUserInstruction(String inWord) {
        String instructionType = DukeParser.getCommandType(inWord);

        switch(instructionType) {
        case LIST_COMMAND:
            UserInterface.printList(taskList);
            break;
        case DONE_COMMAND:
            DoneCommand.manageDoneInstruction(inWord, taskList);
            break;
        case EVENT_COMMAND:
            AddEventCommand.manageEvent(inWord, taskList);
            break;
        case TODO_COMMAND:
            AddTodoCommand.manageTodo(inWord, taskList);
            break;
        case DEADLINE_COMMAND:
            AddDeadlineCommand.manageDeadline(inWord, taskList);
            break;
        case DELETE_COMMAND:
            DeleteCommand.manageDelete(inWord, taskList);
            break;
        case HELP_COMMAND:
            UserInterface.printDukeHelp();
            break;
        case FIND_COMMAND:
            FindCommand.findMatching(inWord, taskList);
            break;
        default:
            UserInterface.generalDukeException();
            break;
        }
    }
}
