package main.java.duke.command;

import main.java.duke.exceptions.InvalidTaskNumberException;

/** Includes the operations needed to execute {@code delete} user command while managing any input errors. */
public class DeleteCommand extends TaskList {

    /**
     * Executes the command {@code delete} on the task list and deletes the specified task
     * based on the task number provided from the task list.
     *
     * @param userInput The input given by the user to carry out on tasks list.
     * @param taskNumber The Task ID which needs ot be deleted from the task list.
     * @throws InvalidTaskNumberException 1. If the task ID is larger or lower than the
     * range of the size of the task list. 2. If the task ID is missing from the user input.
     */
    public static void executeUserCommand(String userInput, int taskNumber) throws InvalidTaskNumberException {
        description = userInput.split(" ");
        if (description.length < 2) {
            throw new InvalidTaskNumberException();
        }
        taskNumber = Integer.parseInt(description[1]) - 1;
        try {
            tasks.get(taskNumber);
            deleteTask(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberException();
        }
    }

}
