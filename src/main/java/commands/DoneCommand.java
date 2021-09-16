package commands;
import tasks.Task;
import tasks.TaskList;

public class DoneCommand extends Command{

    private TaskList tlist;
    private Task t;

    public DoneCommand(TaskList tlist, int index){
        this.tlist = tlist;
        tlist.get(index).taskDone();
        this.t = tlist.get(index);
    }

    public void run(){
        System.out.println("____________________________________________________________");
        System.out.println("List so far: ");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        System.out.println("____________________________________________________________");
    }
}
