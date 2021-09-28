package parser;
import commands.*;
import exception.DukeException;
import task.type.TaskList;
import ui.UI;

public class Parser {

    public static Command parse(TaskList tasksList, String input) throws DukeException {

        if (input.trim().equals("bye")) {
            return new ExitCommand();
        }

        if (input.trim().equals("list")) {
            return new ExecuteList();
        }

        if (input.startsWith("done")) {
            int index = -1;
            index = Integer.parseInt(input.split("done")[1].trim());
            if(index < 1 || index > tasksList.getTasks().size()){
                throw new DukeException();
            }
            return new ExecuteDoneCommand(index);
        }

        if (input.startsWith("delete")) {
            int index = -1;
            index = Integer.parseInt(input.split("delete")[1].trim());
            if(index < 1 || index > tasksList.getTasks().size()){
                throw new DukeException();
            }
            return new DeleteCommand(index);
        }

        if (input.startsWith("todo")) {
            String description;
            try {
                description = input.trim().split("todo")[1].trim();
            } catch (IndexOutOfBoundsException e) {
                UI.printDescriptionNotFoundForTodo();
                throw new DukeException();
            }
            return new ExecuteTodo(description);
        }

        if (input.startsWith("deadline")) {
            final String TYPE = "deadline";
            String deadlineDetails;
            try {
                deadlineDetails = input.trim().split("deadline")[1];
            } catch (IndexOutOfBoundsException e) {
                UI.printDescriptionNotFoundForDeadline();
                throw new DukeException();
            }
            String description, by;
            description = deadlineDetails.split("/by")[0].trim();
            by = deadlineDetails.split("/by")[1].trim();
            return new ExecuteEventAndDeadline(TYPE, description, by);
        }

        if (input.startsWith("event")) {
            final String TYPE = "event";
            String eventDetails;
            try {
                eventDetails = input.trim().split("event")[1];
            } catch (IndexOutOfBoundsException e) {
                UI.printDescriptionNotFoundForEvent();
                throw new DukeException();
            }
            String description, at;
            description = eventDetails.split("/at")[0].trim();
            at = eventDetails.split("/at")[1].trim();
            return new ExecuteEventAndDeadline(TYPE, description, at);
        }

        return new ErrorUnknownCommand(input);
    }
}
