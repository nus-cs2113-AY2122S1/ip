package Commands;

import Tasks.TaskList;
import Tasks.Deadline;

import java.time.LocalDateTime;

/**
 * Adds a deadline task to the task list
 */
public class DeadlineCommand extends Command {

    public String description;
    public LocalDateTime dueDate;
    public static final String SUCCESS_MESSAGE = "The following deadline task has been added\n";

    public DeadlineCommand(String input){
        String[] inputParts = input.split("/by");
        description = inputParts[0].trim();
        dueDate = LocalDateTime.parse(inputParts[1].trim());
    }

    @Override
    public String execute(TaskList taskList){
        taskList.addTask(new Deadline(description, false, dueDate));
        return SUCCESS_MESSAGE + taskList.printNewestTask() + "\n";
    }
}
