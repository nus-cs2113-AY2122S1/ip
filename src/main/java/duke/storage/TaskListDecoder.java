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

public class TaskListDecoder {
    public static TaskList decodeTaskList(List<String> encodedTaskList) throws IllegalValueException {
        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new TaskList(decodedTasks);

    }

    public static Task decodeTaskFromString(String s) throws IllegalValueException {

        try{
            String[] taskComponent = s.split(" \\| ");
            char taskType = taskComponent[0].charAt(0);
            int isDone = Integer.parseInt(taskComponent[1].trim());
            String taskDetails = taskComponent[2].trim();
            Task newTask;

            switch (taskType){
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

            if(isDone == 1){
                assert newTask != null;
                newTask.markAsDone();
            }

            return newTask;
        } catch (ArrayIndexOutOfBoundsException e){
            throw new IllegalValueException();
        }


    }
}
