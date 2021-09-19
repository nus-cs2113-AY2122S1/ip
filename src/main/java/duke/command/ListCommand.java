package duke.command;

import duke.tasks.TaskList;

import static duke.logic.Logic.listIndex;

public class ListCommand extends Command{

    @Override
    public String execute(TaskList tasks) {
        if (listIndex == 0) {
            returnString = "No tasks added yet! :)";
        } else {
            //when there are tasks in list
            returnString = tasks.listTaskList(tasks);
        }
        return  returnString;
    }
}
