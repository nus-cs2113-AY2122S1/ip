package commands;

public class ByeCommand extends Command{

    public boolean exit(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        return false;
    }

}
