package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String TASK_FILE_PATH = "tasks.txt";
    private static final String TASK_INFO_SEPARATOR_ESCAPE = "\\";
    private static final String TASK_INFO_SEPARATOR = "|";
    private static final String TASK_INFO_ISDONE_INDICATOR = "1";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";

    public Storage() {
    }

    /**
     * Load task list from save file.
     *
     * @return A new task list generated from the save file.
     * @throws FileNotFoundException     Task file does not exist.
     * @throws TaskListEmptyException    Task list is empty.
     * @throws InvalidTaskIndexException Task index provided is not within task list size.
     */
    public TaskList loadTaskList() throws FileNotFoundException, TaskListEmptyException, InvalidTaskIndexException {
        TaskList taskList = new TaskList();
        File taskFile = new File(TASK_FILE_PATH);

        Scanner scanner;
        scanner = new Scanner(taskFile);

        // Read each line as a task
        for (int i = 0; scanner.hasNext(); i++) {
            String line = scanner.nextLine().trim();
            if (!line.isBlank()) {
                String[] splitLine = line.split(TASK_INFO_SEPARATOR_ESCAPE + TASK_INFO_SEPARATOR);
                String taskType = splitLine[0];
                boolean taskIsDone = splitLine[1].equals(TASK_INFO_ISDONE_INDICATOR);
                String taskDescription = splitLine[2];

                // Add task to task list
                if (splitLine.length == 3) {
                    Todo todo = new Todo(taskDescription);
                    taskList.addTask(todo);
                } else if (splitLine.length == 4) {
                    String timeDetail = splitLine[3];
                    if (taskType.equals(TASK_TYPE_DEADLINE)) {
                        Deadline deadline = new Deadline(taskDescription, timeDetail);
                        taskList.addTask(deadline);
                    } else if (taskType.equals(TASK_TYPE_EVENT)) {
                        Event event = new Event(taskDescription, timeDetail);
                        taskList.addTask(event);
                    }
                }

                if (taskIsDone) {
                    taskList.markTaskAsDone(i);
                }
            }
        }

        return taskList;
    }

    /**
     * Save task list to file.
     *
     * @param taskList The current tracked task list.
     * @throws IOException               Unable to write to save file.
     * @throws TaskListEmptyException    Task list is empty.
     * @throws InvalidTaskIndexException Task index provided is not within task list size.
     */
    public void saveTaskList(TaskList taskList) throws TaskListEmptyException, InvalidTaskIndexException, IOException {
        FileWriter fw;
        fw = new FileWriter(TASK_FILE_PATH);

        String[] encodedString = new String[taskList.getTotalTasks()];
        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            Task task;
            task = taskList.getTask(i);
            String taskType = task.getType();
            int isDone = task.getStatusIcon().equals("X") ? 1 : 0;
            String taskDescription = task.getRawDescription();
            encodedString[i] = taskType + TASK_INFO_SEPARATOR + isDone + TASK_INFO_SEPARATOR + taskDescription;

            Deadline deadline;
            Event event;
            switch (taskType) {
            case "D":
                deadline = (Deadline) task;
                encodedString[i] += TASK_INFO_SEPARATOR + deadline.getBy();
                break;
            case "E":
                event = (Event) task;
                encodedString[i] += TASK_INFO_SEPARATOR + event.getAt();
                break;
            }
        }

        fw.write(String.join("\n", encodedString));
        fw.flush();
        fw.close();
    }
}
