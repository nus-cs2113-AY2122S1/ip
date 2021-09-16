package commands;
import tasks.DukeException;
import tasks.Task;
import java.util.ArrayList;
import tasks.DukeException;
import tasks.TaskList;

public class AddTaskCommand extends Command{

    private  Task t;
    private TaskList tlist;

    public AddTaskCommand(TaskList tlist, Task t){
        this.tlist = tlist;
        this.t = t;
    }

    public  void run(){

        tlist.add(t);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t);
        System.out.println("Now you have " + tlist.size() + " tasks in the list");
        System.out.println("____________________________________________________________");
    }

}
