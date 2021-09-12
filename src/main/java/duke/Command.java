package duke;

import duke.exception.DeadLineCommandError;
import duke.exception.DeleteListIndexError;
import duke.exception.DoneListIndexError;
import duke.exception.DukeException;
import duke.exception.EventCommandError;
import duke.exception.InvalidCommandError;
import duke.exception.ToDoCommandError;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Command takes in the parsed input from the user and splits it into three categories.
 * They are the command type, description of the task and the date. Depending on the
 * type of command, description and date may be empty. Command then returns the
 * appropriate mesage to return to Logic class to output to the user.
 *
 * @param "commandInput"  type of command.
 * @param "descriptionInput" Y description of task.
 * @param "dateInput" event date/deadline.
 * @return output message to the user.
 */
public class Command extends Logic {
    protected Boolean isBye;
    protected String command;
    protected String description;
    protected String date;
    protected String returnString;

    public Command(String commandInput, String descriptionInput, String dateInput) {
        this.command = commandInput;
        this.description = descriptionInput;
        this.date = dateInput;
        this.isBye = false;
    }

    /**
     * Executes the given command received from the Parser class.
     * Returns the appropriate message to the Logic class.
     *
     * @return output message to the user.
     */
    public String executeCommand() throws DukeException {
        switch (command) {
        case "bye":
            isBye = true;
            returnString = Message.printExit();
            break;
        case "todo":
            try {
                tasks.add(new Todo(description));
                LIST_INDEX++;
                returnString = "Got it. I've added this task:\n" + tasks.get(LIST_INDEX - 1) + "\n"
                        + "Now you have " + String.valueOf(LIST_INDEX) + " tasks in the list.";
            } catch (Exception e) {
                throw new ToDoCommandError();
            }
            break;
        case "deadline":
            try {
                tasks.add(new Deadline(description, date));
                LIST_INDEX++;
                returnString = "Got it. I've added this task:\n" + tasks.get(LIST_INDEX - 1) + "\n"
                        + "Now you have " + String.valueOf(LIST_INDEX) + " tasks in the list.";
            } catch (Exception e) {
                throw new DeadLineCommandError();
            }
            break;
        case "event":
            try {
                tasks.add(new Event(description, date));
                LIST_INDEX++;
                returnString = "Got it. I've added this task:\n" + tasks.get(LIST_INDEX - 1) + "\n"
                        + "Now you have " + String.valueOf(LIST_INDEX) + " tasks in the list.";
            } catch (Exception e) {
                throw new EventCommandError();
            }
            break;
        case "list":
            //no tasks in list
            if (LIST_INDEX == 0) {
                returnString = "No tasks added yet! :)";
            } else {
                //when there are tasks in list
                String listMessage = "Here is the list you requested!";
                for (int i = 1; i <= LIST_INDEX; i++) {
                    listMessage += "\n";
                    listMessage += String.valueOf(i) + ".";
                    //listMessage += tasks[i - 1];
                    listMessage += tasks.get(i - 1);
                }
                returnString = listMessage;
            }
            break;
        case "done":
            int index = Integer.parseInt(description);
            try {
                tasks.get(index - 1).markDone();
                returnString = "Nice! I've marked this task as done:\n"
                        + "[X] " + tasks.get(index - 1).getDescription();
            } catch (Exception e) {
                throw new DoneListIndexError();
            }
            break;
        case "delete":
            int deleteIndex = Integer.parseInt(description);
            try {
                //valid index
                returnString = "Noted. I've removed this task:\n" + tasks.get(deleteIndex - 1) + "\n";
                tasks.remove(deleteIndex - 1);
                LIST_INDEX--;
                returnString += "Now you have " + String.valueOf(LIST_INDEX) + " tasks in the list.";
            } catch (Exception e) {
                throw new DeleteListIndexError();
            }
            break;
        default:
            //returnString = "???\n" + "I do not understand what you are saying";
            throw new InvalidCommandError();
        }
        return returnString;
    }

    /**
     * Returns the boolean isBye as a way to tell Logic class when is it appropriate to exit the while loop.
     *
     * @return boolean isBye.
     */
    public boolean getExitStatus() {
        return isBye;
    }


}
