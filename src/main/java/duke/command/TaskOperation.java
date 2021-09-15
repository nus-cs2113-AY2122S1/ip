package duke.command;

import duke.exception.DukeEmptyParaException;
import duke.exception.DukeException;
import duke.exception.DukeOutOfRangeException;
import duke.task.Deadline;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.util.ArrayList;

public class TaskOperation {
    // Constants
    private static final String MESSAGE_BOUNDARY = "____________________________________________________________";

    // Attributes
    private final ArrayList<Task> tasks; // using Java Collections classes

    public TaskOperation(){
        tasks = new ArrayList<>();
    }

    public void doneTask(String userInput) throws DukeException {
        int i = userInput.indexOf(" ");
        if(i == -1){
            throw new DukeEmptyParaException("There should be an index of task in the done :-(");
        }

        int taskIndex = Integer.parseInt(userInput.substring(i + 1));

        if(taskIndex > tasks.size()){
            throw new DukeOutOfRangeException("Please enter a valid index of task :-(");
        }

        tasks.get(taskIndex - 1).markAsDone();
        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex - 1));
        System.out.println(MESSAGE_BOUNDARY);

    }

    public void addTask(String command, String userInput) throws DukeException {
        int i = userInput.indexOf(" ");
        String taskInfo = " ";
        if(i != -1){
            taskInfo = userInput.substring(i + 1);
        }

        switch (command) {
            case "todo":
                tasks.add(ToDos.parse(taskInfo));
                break;
            case "deadline":
                tasks.add(Deadline.parse(taskInfo));
                break;
            case "event":
                tasks.add(Events.parse(taskInfo));
                break;
        }

        System.out.println(MESSAGE_BOUNDARY + "\n" + "Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.\n" + MESSAGE_BOUNDARY);
    }

    public void deleteTask(String userInput) throws DukeException{
        int i = userInput.indexOf(" ");
        if(i == -1){
            throw new DukeEmptyParaException("There should be an index of task to delete :-(");
        }

        int deleteIndex = Integer.parseInt(userInput.substring(i + 1));

        if(deleteIndex > tasks.size()){
            throw new DukeOutOfRangeException("Please enter a valid index of task :-(");
        }

        System.out.println(MESSAGE_BOUNDARY);
        System.out.println("Noted. I've removed this task:\n" + tasks.get(deleteIndex - 1));
        tasks.remove(deleteIndex - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(MESSAGE_BOUNDARY);

    }

    public void printList(){
        if(tasks.size() == 0){
            System.out.println("No task has added yet!");
        }else{
            System.out.println(MESSAGE_BOUNDARY + System.lineSeparator() + "Here are the tasks in your list:");
            for(int i=0; i< tasks.size(); i++){
                System.out.println((i + 1) + "." + tasks.get(i));
            }
            System.out.println(MESSAGE_BOUNDARY);
        }
    }
}
