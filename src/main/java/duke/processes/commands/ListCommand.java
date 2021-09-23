package duke.processes.commands;

import duke.processes.Interface;

public class ListCommand extends Command {
    public ListCommand() {
    }

    public CommandResult execute() {
        Interface.printList();
        return new CommandResult("--------END OF LIST-----------");
    }
}
