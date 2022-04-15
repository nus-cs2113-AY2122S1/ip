package main.java.duke.command;

import main.java.duke.task.ToDo;

/** Includes the operations needed to execute {@code todo} user command. */
public class ToDoCommand extends TaskList {

    /**
     * Executes the command {@code todo} which adds todo tasks to the task list
     * where "description" is the description of the task.
     *
     * @param userInput The input given by the user to carry out on tasks list
     * @throws IndexOutOfBoundsException If specified index is out of the range of the size of the tasks list
     */
    public static void executeUserCommand(String userInput) throws IndexOutOfBoundsException {
        description = userInput.split("todo ");
        if (description[1].trim().isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        addTask(new ToDo(description[1]));
    }

}
