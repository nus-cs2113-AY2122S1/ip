package commands;

import tasks.Task;
import tasks.TaskList;

public class DeleteCommand extends Command{
    TaskList tlist;
    Task t;
    public DeleteCommand(TaskList tlist, int index) {
        this.tlist = tlist;
        this.t = tlist.get(index);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've deleted this task: ");
        System.out.println(t);
        System.out.println("Now you have " + (tlist.size() - 1)+ " tasks in the list");
        System.out.println("____________________________________________________________");
    }

    public void run(){
        tlist.remove(t);
    }
}
