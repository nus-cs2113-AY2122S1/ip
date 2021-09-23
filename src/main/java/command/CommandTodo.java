package command;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Todo;

public class CommandTodo extends Command{

    private static final int FIRST_ARRAY_PARAMETER = 0;

    private String word;
    private String[] descriptionInput;


    public CommandTodo(String word, String[] descriptionInput) {
        this.word = word;
        this.descriptionInput = descriptionInput;
    }

    public void run() throws DukeException {
        Parser.checkDescription(word, descriptionInput);
        Todo todo = new Todo(descriptionInput[FIRST_ARRAY_PARAMETER]);
        TaskList.addTask(todo);
    }
}
