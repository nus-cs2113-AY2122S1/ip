package duke.commandHandler;

public class SaveCommandHandling {
    private String commandString;

    public SaveCommandHandling(String commandString) {
        this.commandString = commandString;
    }

    public boolean isToDo() {
        if (commandString.split("-/-")[0].equals("t")) {
            return true;
        }
        return false;
    }

    public boolean isDeadline() {
        if (commandString.split("-/-")[0].equals("d")) {
            return true;
        }
        return false;
    }

    public boolean isEvent() {
        if (commandString.split("-/-")[0].equals("e")) {
            return true;
        }
        return false;
    }
}
