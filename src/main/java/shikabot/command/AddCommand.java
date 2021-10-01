package shikabot.command;

import shikabot.task.Deadline;
import shikabot.task.Event;
import shikabot.task.Todo;
import shikabot.ui.TextUi;

import java.time.LocalDate;

public class AddCommand extends Command {

    private final char type;
    private final String name;
    private final LocalDate atBy;

    public AddCommand(char type, String name, LocalDate atBy) {
        this.type = type;
        this.name = name;
        this.atBy = atBy;
    }

    /**
     * Function that adds a new task to the tasklist.
     */
    public void execute() {
        switch (type) {
        case 'T':
            taskList.addTask(new Todo(name));
            break;
        case 'D':
            taskList.addTask(new Deadline(name, atBy));
            break;
        case 'E':
            taskList.addTask(new Event(name, atBy));
            break;
        default:
            break;
        }
        TextUi.printAddTaskMessage(taskList, taskList.getSize() - 1);
        TextUi.printTaskCount(taskList.getSize());
    }

}
