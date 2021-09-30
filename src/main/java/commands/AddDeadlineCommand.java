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

    /**
     * Adds a Deadline task to the TaskList and appends the task into the datafile
     */
    @Override
    public void execute() {
        try {
            String[] deadlineDescriptionAndDeadline = Parser.splitDeadlineDescriptionAndDeadline(deadlineParams);
            String deadlineDescription = deadlineDescriptionAndDeadline[0].trim();
            String deadline = deadlineDescriptionAndDeadline[1].trim();
            if(deadlineDescription.equals("") || deadline.equals("")) {
                TextUi.showMissingTaskDescriptionMessage();
            } else {
                Deadline newDeadline = new Deadline(deadlineDescription, deadline);
                taskList.addTask(newDeadline);
                TextUi.showTaskAddedMessage(newDeadline, taskList.getSize());
                Storage.appendToDataFile("D / 0 / " + deadlineDescription + " | " + deadline);
            }
        } catch (ArrayIndexOutOfBoundsException e){
            TextUi.showInvalidDeadlineMessage();
        } catch (IOException e){
            TextUi.showIOExceptionMessage(e);
        }
    }
}
