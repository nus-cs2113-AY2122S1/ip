package duke.datasaver;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static duke.constants.DukeDataStorageConstants.*;
import static duke.constants.DukeDataStorageConstants.ENCODER_ATTRIBUTE_SEPARATOR;

public class TaskListEncoder {

    public static void encodeTask(ArrayList<Task> taskList, FileWriter fileWriter, StringBuilder formattedTask) throws IOException {
        for (Task task : taskList) {
            appendTaskType(formattedTask, task.getTaskType());
            appendDoneStatus(formattedTask, task.isDone());
            appendTaskDescription(formattedTask, task);
            fileWriter.write(formattedTask + System.lineSeparator());
            formattedTask = new StringBuilder();
        }
    }

    public static void appendTaskType(StringBuilder formattedTask, String taskType) {
        formattedTask.append(taskType).append(ENCODER_ATTRIBUTE_SEPARATOR);
    }

    public static void appendDoneStatus(StringBuilder formattedTask, boolean isDone) {
        if (isDone) {
            formattedTask.append(DONE).append(ENCODER_ATTRIBUTE_SEPARATOR);
        } else {
            formattedTask.append(NOT_DONE).append(ENCODER_ATTRIBUTE_SEPARATOR);
        }
    }

    public static void appendTaskDescription(StringBuilder formattedTask, Task task) {
        if (task instanceof Todo) {
            formattedTask.append(task.getDescription());
        } else if (task instanceof Deadline) {
            formattedTask.append(task.getDescription()).append(ENCODER_ATTRIBUTE_SEPARATOR);
            formattedTask.append(((Deadline) task).getBy());
        } else if (task instanceof Event) {
            formattedTask.append(task.getDescription()).append(ENCODER_ATTRIBUTE_SEPARATOR);
            formattedTask.append(((Event) task).getWhen());
        }
    }
}
