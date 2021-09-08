package duke;

import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Assistant {
    public static final int MAX_TASKS = 100;
    public static final String SEPARATOR_SLASH = "/";


    private int taskIndex;
    private Task[] tasks;

    public Assistant() {
        taskIndex = 0;
        tasks = new Task[MAX_TASKS];
    }

    public void listTasks() {
        for (int i = 0; i < taskIndex; i++) {
            System.out.println((i+1) + ". " + tasks[i].getStatus() + tasks[i].toString()
            );
        }
    }

    public void addTask(Task newTask) {
        tasks[taskIndex] = newTask;
        taskIndex++;
        System.out.println("added: " + newTask.getTaskName());
    }

    public void completeTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= taskIndex) {
            throw new InvalidIndexException();
        }
        tasks[index].finishTask();
        System.out.println("Completed task: " + tasks[index].getTaskName());
    }

    public void addDeadline(String deadline) throws MissingInputException{
        String[] inputs = deadline.split(SEPARATOR_SLASH, 2);
        if(inputs.length == 1) { //no / detected
            throw new MissingInputException();
        }
        addTask(new Deadline(inputs[0], inputs[1]));
    }

    public void addEvent(String event) throws MissingInputException{
        String[] inputs = event.split(SEPARATOR_SLASH, 2);
        if(inputs.length == 1) { //no / detected
            throw new MissingInputException();
        }
        addTask(new Event(inputs[0], inputs[1]));
    }

    public void addToDo(String todo) {
        addTask(new ToDo(todo));
    }
}
