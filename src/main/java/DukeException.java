
/**
 * The DukeException program handles invalid user commands by printing relevant error messages.
 */
public class DukeException {
    protected boolean isInvalidCommand;
    protected boolean isNotDone;
    protected boolean isDoneNoNumber;
    protected boolean isTodoEmpty;
    protected boolean isDeadlineEmpty;
    protected boolean isEventEmpty;
    protected boolean isDeadlineNoDate;
    protected boolean isEventNoDate;
    protected boolean isNotDelete;
    protected boolean isDeleteNoNumber;
    protected boolean isFindNoWord;
    protected String Description;

    public DukeException(String description) {
        Description = description;
        isInvalidCommand = false;
        isNotDone = false;
        isDoneNoNumber = false;
        isTodoEmpty = false;
        isDeadlineEmpty = false;
        isEventEmpty = false;
        isDeadlineNoDate = false;
        isEventNoDate = false;
        isNotDelete = false;
        isDeleteNoNumber = false;
        isFindNoWord = false;

    }

    /**
     * Sets String description as relevant error message based on type of error by checking which error boolean is set
     * to true and returns it.
     * @return error description
     */
    @Override
    public String toString() {
        String description = "";

        if (isInvalidCommand){
            description = "Huh? I don't understand. What would you like to add?\n" + "Use Todo/ Deadline/ Event to specify task!\n";
        }
        else if (isNotDone) {
            description = "Done what??? Add the task number!\n";
        }
        else if (isDoneNoNumber) {
            description = "Done what??? Use the correct task number instead!\n" + "Use 'list' to access task number!\n";
        }
        else if (isNotDelete) {
            description = "Delete what??? Add the task number!\n";
        }
        else if (isDeleteNoNumber) {
            description = "Delete what??? Use the correct task number instead!\n" + "Use 'list' to access task number!\n";
        }
        else if (isFindNoWord) {
            description = "Find what??? Use keywords to find the task you're looking for!\n";
        }
        else if (isTodoEmpty) {
            description = "Todo what???\n";
        }

        else if (isDeadlineEmpty) {
            description = "What's due when??? You can add the date and time after '/'!\n" + "Example: (Deadline Coding Exercise /by yyyy-mm-dd HHMM)\n";
        }

        else if (isDeadlineNoDate) {
            description = "When is this due? You can add the date and time after '/'!\n" + "Example: (Deadline Coding Exercise /by yyyy-mm-dd HHMM)\n";
        }

        else if (isEventEmpty) {
            description = "What event and when is it???\n";
        }

        else if (isEventNoDate) {
            description = "When/where is this event? You can add the timing/place after '/'!\n" + "Example: (Event CS Tutorial /at Wed 11:00)\n";
        }
        return description;
    }

    public void setInvalidCommand() {
        isInvalidCommand = true;
    }

    public void setNotDone() {
        isNotDone = true;
    }

    public void setDoneNoNumber() {
        isDoneNoNumber = true;
    }

    public void setNotDelete() {
        isNotDelete = true;
    }

    public void setDeleteNoNumber() {
        isDeleteNoNumber = true;
    }

    public void setFindNoWord() {
        isFindNoWord = true;
    }

    public void setTodoEmpty() {
        isTodoEmpty = true;
    }

    public void setDeadlineEmpty() {
        isDeadlineEmpty = true;
    }

    public void setEventEmpty() {
        isEventEmpty = true;
    }

    public void setDeadlineNoDate() {
        isDeadlineNoDate = true;
    }

    public void setEventNoDate() {
        isEventNoDate = true;
    }
}
