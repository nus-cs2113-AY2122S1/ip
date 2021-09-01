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
    public String executeCommand() {
        switch (command) {
        case "bye":
            isBye = true;
            returnString = Message.printExit();
            break;
        case "todo":
            if (lIST_INDEX < LIST_LENGTH) {
                Tasks[lIST_INDEX] = new Todo(description);
                lIST_INDEX++;
                returnString = "Got it. I've added this task:\n" + Tasks[lIST_INDEX - 1] + "\n"
                        + "Now you have " + String.valueOf(lIST_INDEX) + " tasks in the list.";
            } else {
                //>= 100 tasks
                returnString = "List is full! :(";
            }
            break;
        case "deadline":
            if (lIST_INDEX < LIST_LENGTH) {
                Tasks[lIST_INDEX] = new Deadline(description, date);
                lIST_INDEX++;
                returnString = "Got it. I've added this task:\n" + Tasks[lIST_INDEX - 1] + "\n"
                        + "Now you have " + String.valueOf(lIST_INDEX) + " tasks in the list.";
            } else {
                //>= 100 tasks
                returnString = "List is full! :(";
            }
            break;
        case "event":
            if (lIST_INDEX < LIST_LENGTH) {
                Tasks[lIST_INDEX] = new Event(description, date);
                lIST_INDEX++;
                returnString = "Got it. I've added this task:\n" + Tasks[lIST_INDEX - 1] + "\n"
                        + "Now you have " + String.valueOf(lIST_INDEX) + " tasks in the list.";
            } else {
                //>= 100 tasks
                returnString = "List is full! :(";
            }
            break;
        case "list":
            //no tasks in list
            if (lIST_INDEX == 0) {
                returnString = "No tasks added yet! :)";
            } else {
                //when there are tasks in list
                String listMessage = "Here is the list you requested!";
                for (int i = 1; i <= lIST_INDEX; i++) {
                    listMessage += "\n";
                    listMessage += String.valueOf(i) + ".";
                    listMessage += Tasks[i - 1];
                }
                returnString = listMessage;
            }
            break;
        case "done":
            int index = Integer.parseInt(description);
            //index cannot be <= 0
            if (index <= 0) {
                returnString = "Invalid index";
            } else if (index <= lIST_INDEX) {
                //valid index
                Tasks[index - 1].markDone();
                returnString = "Nice! I've marked this task as done:\n"
                        + "[X] " + Tasks[index - 1].getDescription();
            } else {
                //not enough tasks to make it to that index
                returnString = "No task at that index";
            }
            break;
        default:
            returnString = "???\n" + "I do not understand what you are saying";
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
