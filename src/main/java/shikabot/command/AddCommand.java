package shikabot.command;

import shikabot.task.Deadline;
import shikabot.task.Event;
import shikabot.task.Todo;

public class AddCommand extends Command {

    private final char type;
    private final String name;
    private final String atBy;

    public AddCommand(char type, String name, String atBy) {
        this.type = type;
        this.name = name;
        this.atBy = atBy;
    }

    @Override
    public void execute() {
        switch (type) {
        case 'T':
            tasks.add(new Todo(name));
            break;
        case 'D':
            tasks.add(new Deadline(name, atBy));
            break;
        case 'E':
            tasks.add(new Event(name, atBy));
            break;
        default:
            break;
        }
        ui.printAddTaskMessage(tasks, tasks.size() - 1);
        ui.printTaskCount(tasks.size());
    }

}
