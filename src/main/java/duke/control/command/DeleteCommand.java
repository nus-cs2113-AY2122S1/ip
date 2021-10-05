package duke.control.command;

import duke.control.Storage;
import duke.control.TaskList;

public class DeleteCommand extends Command {
    private static final int DELETE_NUMBER_INDEX = 7;

    @Override
    public void executeCommand(TaskList list, String input, Storage storage) {
        try {
            int entryNumber = Integer.parseInt(input.substring(DELETE_NUMBER_INDEX));
            list.deleteEntry(entryNumber);
        } catch (NumberFormatException e) {
            //entry number is an invalid character
            System.out.println("the delete command is of the form \"delete x\" where x is an entry number");
        } catch (StringIndexOutOfBoundsException e) {
            //entry number is missing
            System.out.println("The delete command must have an entry number, " +
                    "enter it in the form \"delete x\" where x is an entry number");
        } catch (IndexOutOfBoundsException e) {
            //entry number does not exist in the list
            System.out.println("That entry number does not exist in your list");
        }
    }
}
