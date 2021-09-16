package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;
import kitty.io.IO;

import java.util.function.ToDoubleFunction;

public class Todo extends Task{

    public Todo(String taskName) {
        super(taskName);
    }

    public static void addTodoTask(String line) throws KittyException {
        try {
            String taskName = Parser.getTodoTaskName(line);
            Kitty.tasks.add(new Todo(taskName));
        } catch (KittyException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
