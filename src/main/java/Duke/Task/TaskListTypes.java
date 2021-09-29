package Duke.Task;

import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static Duke.BackEnd.DukeParser.parseDateTime;
import static Duke.SaveFile.DataSaver.addDoneStatus;
import static Duke.UI.DukeConstants.FORMAT_DATE_TIME_INPUT;

public class TaskListTypes {

    /**
     * Load a todo-type task from the text file to the task list
     *
     * @param taskList the array list which stores all user tasks
     * @param taskDetails the details of the todo task to be stored
     */
    public static void addTodoToTaskList (ArrayList<Task> taskList, String[] taskDetails) {
        String todoDescription = taskDetails[2].trim();
        Todo addedTodo = new Todo(todoDescription);
        addDoneStatus(addedTodo, taskDetails);
        taskList.add(addedTodo);
    }

    /**
     * Load a deadline-type task from the text file to the task list
     *
     * @param taskList the array list which stores all user tasks
     * @param taskDetails the details of the deadline task to be stored
     */
    public static void addDeadlineToTaskList (ArrayList<Task> taskList, String[] taskDetails) {
        String deadlineDescription = taskDetails[2].trim();
        String by = taskDetails[3].trim();
        //remove T in index 10 of task in save file before formatting
        char[] byCharArray = by.toCharArray();
        if (byCharArray[10] == 'T') {
            byCharArray[10] = ' ';
        }
        by = String.valueOf(byCharArray);
        //create new deadline and add to task list
        LocalDateTime deadlineBy = parseDateTime(by, FORMAT_DATE_TIME_INPUT);
        Deadline addedDeadline = new Deadline(deadlineDescription, deadlineBy);
        addDoneStatus(addedDeadline, taskDetails);
        taskList.add(addedDeadline);
    }

    /**
     * Load an event-type task from the text file to the task list
     *
     * @param taskList the array list which stores all user tasks
     * @param taskDetails the details of the event task to be stored
     */
    public static void addEventToTaskList (ArrayList<Task> taskList, String[] taskDetails) {
        String eventDescription = taskDetails[2].trim();
        String at = taskDetails[3].trim();
        //remove T in index 10 of task in save file before formatting
        char[] atCharArray = at.toCharArray();
        if (atCharArray[10] == 'T') {
            atCharArray[10] = ' ';
        }
        at = String.valueOf(atCharArray);
        //create new event and add to task list
        LocalDateTime eventAt = parseDateTime(at, FORMAT_DATE_TIME_INPUT);
        Event addedEvent = new Event(eventDescription, eventAt);
        addDoneStatus(addedEvent, taskDetails);
        taskList.add(addedEvent);
    }

}
