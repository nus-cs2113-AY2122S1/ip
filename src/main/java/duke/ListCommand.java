package duke;

import duke.tasks.Task;
import java.util.ArrayList;

import static duke.Logic.listIndex;

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
