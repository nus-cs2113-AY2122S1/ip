package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;

/**
 * The class <code>Todo</code> includes methods that involves tasks of type Todo.
 */
public class Todo extends Task{

    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Adds a task of type Todo to list of tasks at hand.
     * @param line line is the String that the user inputs.
     * @throws KittyException If line is of the wrong format for adding a task of Todo type.
     */
    public static void addTodoTask(String line) throws KittyException {
        String taskName = Parser.getTodoTaskName(line);
        Kitty.tasks.add(new Todo(taskName));
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
