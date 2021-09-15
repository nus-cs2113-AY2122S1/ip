package duke;

import duke.exception.InvalidIndexException;
import duke.exception.MissingInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.util.ArrayList;

public class Assistant {
    public static final String SEPARATOR_SLASH = "/";

    private ArrayList<Task> tasks;

    public Assistant() {
        tasks = new ArrayList<>();
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getStatus() + tasks.get(i).toString()
            );
        }
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println("added: " + newTask.getTaskName());
    }

    public void completeTask(String input) throws InvalidIndexException {
        int index = Integer.parseInt(input) - 1;
        if (index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(index).finishTask();
        System.out.println("Completed task: " + tasks.get(index).getTaskName());
    }

    public void addDeadline(String deadline) throws MissingInputException{
        String[] inputs = deadline.split(SEPARATOR_SLASH, 2);
        if(inputs.length == 1  || inputs[1].equals("")) { //no / detected
            throw new MissingInputException();
        }
        addTask(new Deadline(inputs[0], inputs[1]));
    }

    public void addEvent(String event) throws MissingInputException{
        String[] inputs = event.split(SEPARATOR_SLASH, 2);
        if(inputs.length == 1 || inputs[1].equals("")) { //no / detected or / without input
            throw new MissingInputException();
        }
        addTask(new Event(inputs[0], inputs[1]));
    }

    public void addToDo(String todo) {
        //no MissingInputException required since ArrayOutOfBounds will be thrown in main
        addTask(new ToDo(todo));
    }

    public void deleteTask(String input) {
        int index = Integer.parseInt(input) - 1;
        System.out.println("Removed task: " + tasks.get(index).getTaskName());
        tasks.remove(index);
    }
}
