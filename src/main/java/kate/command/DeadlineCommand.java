package kate.command;

import kate.common.Message;
import kate.exception.EmptyFieldException;
import kate.exception.InvalidDateTimeException;
import kate.parser.Parser;
import kate.storage.Storage;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

import java.time.LocalDate;
import java.time.LocalTime;

public class DeadlineCommand extends Command {

    private static final String FAILURE_MESSAGE_ADD_DEADLINE = Message.TEXT_INDENTATION
            + "Please specify a task with:\n"
            + Message.TEXT_INDENTATION + "\"" + Message.COMMAND_DEADLINE + "\"\n";

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(KateUI ui, Storage storage, TaskList tasks) {
        try {
            String[] infoArr = Parser.extractDeadlineInput(userInput);
            String taskDescription = infoArr[0];
            LocalDate date = LocalDate.parse(infoArr[1]);
            LocalTime time = LocalTime.parse(infoArr[2]);

            tasks.addDeadline(taskDescription, date, time);
            ui.printAddedTask(tasks.getMostRecentAddedTask(), tasks.getTaskSize());
            storage.appendTaskToFile(ui, tasks.getMostRecentAddedTask());
        } catch (EmptyFieldException e) {
            ui.printMessage(FAILURE_MESSAGE_ADD_DEADLINE);
        } catch (InvalidDateTimeException e) {
            ui.printMessage(Message.FAILURE_PARSE_DATE);
        }
    }
}
