package Duke.BackEnd;

import Duke.Commands.*;
import Duke.UI.UserInterface;

import static Duke.RunFile.DukeProgram.taskList;
import static Duke.UI.DukeConstants.*;

public class DukeBackEnd {
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

    public static void executeUserInstruction(String inWord) {
        String instructionType = DukeParser.getCommandType(inWord);

        switch(instructionType) {
        case LIST_COMMAND:
            UserInterface.printList(taskList);
            break;
        case DONE_COMMAND:
            DoneCommand.manageDoneInstruction(inWord);
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
