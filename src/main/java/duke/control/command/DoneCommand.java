package duke.control.command;

import duke.control.TaskList;

public class DoneCommand extends Command {
    private static final int DONE_NUMBER_INDEX = 5;

    @Override
    public void executeCommand(TaskList list, String input) {
        try {
            int entryNumber = Integer.parseInt(input.substring(DONE_NUMBER_INDEX));
            list.doneEntry(entryNumber);
        } catch (NumberFormatException e) {
            //entry number is an invalid character
            System.out.println("the done command is of the form \"done x\" where x is an entry number");
        } catch (StringIndexOutOfBoundsException e) {
            //entry number is missing
            System.out.println("The done command must have an entry number, " +
                    "enter it in the form \"done x\" where x is an entry number");
        } catch (IndexOutOfBoundsException e) {
            //entry number does not exist in the list
            System.out.println("That entry number does not exist in your list");
        }
    }
}
