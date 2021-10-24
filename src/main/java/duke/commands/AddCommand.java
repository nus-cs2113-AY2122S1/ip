package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.TaskType;

public class AddCommand extends Command {

    public TaskType type;

    public AddCommand(TaskList taskList, String userCommand, TaskType type) {
        super(taskList, userCommand);
        this.type = type;
    }

    @Override
    public CommandOutput execute() throws Exception {
        String response = "";

        if (type == TaskType.TODO) {
            response = taskList.addTodo(userCommand);
        } else if (type == TaskType.DEADLINE) {
            response = taskList.addDeadline(userCommand);
        } else if (type == TaskType.EVENT){
            response = taskList.addEvent(userCommand);
        }

        return new CommandOutput(response, true, taskList);
    }
}
