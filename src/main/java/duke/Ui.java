package duke;
import duke.tasks.Task;
import duke.tasks.TaskList;
import java.util.Scanner;
/**
 * Prints all the outputs to the terminal.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    protected String readCommand() {
        return scanner.nextLine();
    }

    protected void hello(){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void printList(TaskList taskList){
        System.out.println("____________________________________________________________");
        System.out.println("List so far: ");
        taskList.print();
        System.out.println("____________________________________________________________");
    }

    public void bye(){
        scanner.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void delete(Task t, TaskList taskList){
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've deleted this task: ");
        System.out.println(t);
        System.out.println("Now you have " + (taskList.size() - 1)+ " tasks in the list");
        System.out.println("____________________________________________________________");
    }

    public void done(Task t, TaskList taskList){
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've marked this task as done: ");
        System.out.println(t);
        System.out.println("Now you have " + (taskList.size())+ " tasks in the list");
        System.out.println("____________________________________________________________");
    }

    public void add(Task t, TaskList taskList){
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
        System.out.println("____________________________________________________________");
    }

    public  void find(TaskList possibleTasks, String description){
        System.out.println("____________________________________________________________");
        System.out.println("Tasks with keyword: " + description);
        possibleTasks.print();
        System.out.println("____________________________________________________________");
    }
}
