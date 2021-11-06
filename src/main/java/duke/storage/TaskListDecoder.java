package duke.storage;

import duke.data.TaskList;
import duke.exception.IllegalValueException;
import duke.exception.StorageOperationException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.util.ArrayList;
import java.util.List;

/**
 * Decodes the storage data file into an {@code TaskList} object.
 */
public class TaskListDecoder {
    /**
     * Decodes {@code encodedTaskList} into an {@code TaskList} containing the decoded tasks.
     *
     * @throws IllegalValueException if any of the fields in any encoded task string is invalid.
     */
    public static TaskList decodeTaskList(List<String> encodedTaskList) throws IllegalValueException {
        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);

    }

    /**
     * Decodes {@code encodedTask} into a {@code Task}.
     *
     * @throws IllegalValueException if any field in the {@code encodedTask} is invalid.
     */
    public static Task decodeTaskFromString(String encodedTask) throws IllegalValueException {

        try {
            String[] taskComponent = encodedTask.split(" \\| ");
            char taskType = taskComponent[0].charAt(0);
            int isDone = Integer.parseInt(taskComponent[1].trim());
            String taskDetails = taskComponent[2].trim();
            Task newTask;

            switch (taskType) {
                case 'T':
                    newTask = new ToDos(taskDetails);
                    break;
                case 'D':
                    newTask = new Deadline(taskDetails, taskComponent[3].trim());
                    break;
                case 'E':
                    newTask = new Events(taskDetails, taskComponent[3].trim());
                    break;
                default:
                    newTask = null;

            }

            if (isDone == 1) {
                assert newTask != null;
                newTask.markAsDone();
            }
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalValueException();
        }
    }
}
