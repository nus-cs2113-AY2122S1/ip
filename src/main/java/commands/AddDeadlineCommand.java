package commands;

import parser.Parser;
import storage.Storage;
import tasks.Deadline;
import ui.TextUi;

import java.io.IOException;

public class AddDeadlineCommand extends Command{

    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_DESC = "Adds a task of \"DEADLINE\" type to the list";

    private final String deadlineParams;

    public AddDeadlineCommand (String deadlineParams) {
        this.deadlineParams = deadlineParams;
    }

    @Override
    public void execute() {
        try {
            String[] deadlineDescriptionAndDeadline = Parser.splitDeadlineDescriptionAndDeadline(deadlineParams);
            String deadlineDescription = deadlineDescriptionAndDeadline[0];
            String deadline = deadlineDescriptionAndDeadline[1];
            if(deadlineDescription.equals("") || deadline.equals("")) {
                TextUi.showMissingTaskDescriptionMessage();
            } else {
                Deadline newDeadline = new Deadline(deadlineDescription, deadline);
                taskList.addTask(newDeadline);
                TextUi.showTaskAddedMessage(newDeadline, taskList.getSize());
                Storage.appendToDataFile("D / 0 / " + deadlineDescription + "|" + deadline);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            TextUi.showInvalidDeadlineMessage();
        } catch (IOException e){
            TextUi.showIOExceptionMessage(e);
        }
    }
}
