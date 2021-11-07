package parser;

import command.*;
import exceptions.DukeInvalidArgumentException;
import task.TaskList;
import ui.UI;

/**
 * Represents class that parses user input for commands.
 */
public class Parser {

    /**
     * Parses user input and returns Command object to be executed.
     * If no command has been found, return unknownCommand object.
     *
     * @param tasksList List of tasks
     * @param input User input
     * @throws DukeInvalidArgumentException If argument is in wrong format
     * @return Respective Command object
     */
    public static Command parse(TaskList tasksList, String input) throws DukeInvalidArgumentException {

        if (input.trim().equals("bye")) {
            return new ByeCommand();
        }

        if (input.trim().equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("done")) {
            int index = -1;
            index = Integer.parseInt(input.split("done")[1].trim());
            if(index < 1 || index > tasksList.getTasks().size()){
                throw new DukeInvalidArgumentException();
            }
            return new DoneCommand(index);
        }

        if (input.startsWith("delete")) {
            int index = -1;
            try {
                index = Integer.parseInt(input.split("delete")[1].trim());
            }  catch (IndexOutOfBoundsException | NumberFormatException e) {
                UI.printTaskNotInListMessage();
            }
            if(index < 1 || index > tasksList.getTasks().size()){
                throw new DukeInvalidArgumentException();
            }
            return new DeleteCommand(index);
        }

        if (input.startsWith("todo")) {
            String description;
            try {
                description = input.trim().split("todo")[1].trim();
            } catch (IndexOutOfBoundsException e) {
                UI.printNoDescriptionFoundMessage();
                throw new DukeInvalidArgumentException();
            }
            return new ToDoCommand(description);
        }

        if (input.startsWith("deadline")) {
            final String TYPE = "deadline";
            String deadlineDetails;
            try {
                deadlineDetails = input.trim().split("deadline")[1];
            } catch (IndexOutOfBoundsException e) {
                UI.printNoDescriptionFoundMessage();
                throw new DukeInvalidArgumentException();
            }
            String description, by;
            try {
                description = deadlineDetails.split("/by")[0].trim();
                by = deadlineDetails.split("/by")[1].trim();
            } catch (IndexOutOfBoundsException e) {
                UI.printInvalidNumberOfParametersMessage();
                throw new DukeInvalidArgumentException();
            }
            return new EventAndDeadlineCommand(TYPE, description, by);
        }

        if (input.startsWith("event")) {
            final String TYPE = "event";
            String eventDetails;
            try {
                eventDetails = input.trim().split("event")[1];
            } catch (IndexOutOfBoundsException e) {
                UI.printNoDescriptionFoundMessage();
                throw new DukeInvalidArgumentException();
            }
            String description, at;
            try {
                description = eventDetails.split("/at")[0].trim();
                at = eventDetails.split("/at")[1].trim();
            } catch (IndexOutOfBoundsException e) {
                UI.printInvalidNumberOfParametersMessage();
                throw new DukeInvalidArgumentException();
            }
            return new EventAndDeadlineCommand(TYPE, description, at);
        }

        if (input.startsWith("find")) {
            String filter;
            filter = input.split("find")[1].trim();
            return new FindCommand(filter);
        }

        return new UnknownCommand(input);
    }
}