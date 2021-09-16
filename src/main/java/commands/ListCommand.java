package commands;

import tasks.TaskList;

public class ListCommand extends Command{
    TaskList tlist;

    public ListCommand(TaskList tlist){
        this.tlist = tlist;
    }

    public void run(){
        System.out.println("____________________________________________________________");
        System.out.println("List so far: ");
        tlist.print();
        System.out.println("____________________________________________________________");
    }

}
