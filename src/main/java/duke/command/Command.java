package duke.command;

public abstract class Command {
    protected String keyword;
    public Command(String commandName) {
        this.keyword = commandName;
    }
    public void printDone() {
        System.out.println("finished ");
    }
}
