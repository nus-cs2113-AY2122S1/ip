package duke;

public class Parser {
    protected String command;
    protected String description;
    protected String time;
    protected int count = 0;

    public Parser(String input, TaskList tasklist) {
        parseCommand(input);
    }

    public void errorCatch(DukeException e) {
        switch (e.error) {
        case "unknownCommand":
            Ui.unknownCommandError();
            break;
        case "nullTodo":
            Ui.emptyDescriptionError();
            break;
        case "nullTime":
            Ui.emptyTimeError();
            break;
        default:
            Ui.unknownError();
            break;
        }
    }

    /** Checks if any of the arguments are left blank */
    public void errorCheck(boolean includeTime) throws DukeException{
        if (description==null) {
            throw new DukeException("nullTodo");
        } else if (time==null && includeTime) {
            throw new DukeException("nullTime");
        }
    }

    /** Converts the string into its individual arguments */
    public void parseCommand(String input) {
        String temp;
        if (input.contains(" ")) {
            this.command = input.substring(0, input.indexOf(" "));
            temp = input.substring(input.indexOf(" ") + 1);
            if (temp.contains("/")) {
                description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                time = input.substring(input.indexOf("/") + 4);
            } else {
                description = temp;
            }
        } else {
            this.command = input;
        }
    }

    public int executeCommand(TaskList tasklist) {
        try {
            return (runCommand(tasklist));
        } catch (DukeException e){
            errorCatch(e);
        } catch (NullPointerException e) {
            Ui.emptyListError();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.noItemDoneError();
        } catch (NumberFormatException e) {
            Ui.noIndexError();
        } catch (IndexOutOfBoundsException e) {
            Ui.noItemError();
        }
        return 0;
    }

    public int runCommand(TaskList tasklist) throws DukeException {
        switch(command){
        case "list":
            tasklist.list();
            break;
        case "done":
            errorCheck(false);
            tasklist.done(description);
            break;
        case "todo":
            errorCheck(false);
            count++;
            tasklist.addTodo(description, count);
            break;
        case "deadline":
            errorCheck(true);
            count++;
            tasklist.addDeadline(description, time, count);
            break;
        case "event":
            errorCheck(true);
            count++;
            tasklist.addEvent(description, time, count);
            break;
        case "delete":
            errorCheck(false);
            count--;
            tasklist.delete(description, count);
            break;
        case "bye":
            Ui.end();
            return -1;
        default:
            throw new DukeException("unknownCommand");
        }
        return 0;
    }
}
