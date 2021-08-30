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
            taskList.add(new Todo(name));
            break;
        case 'D':
            taskList.add(new Deadline(name, atBy));
            break;
        case 'E':
            taskList.add(new Event(name, atBy));
            break;
        default:
            break;
        }
        ui.printAddTaskMessage(taskList, taskList.size() - 1);
        ui.printTaskCount(taskList.size());
    }

}
