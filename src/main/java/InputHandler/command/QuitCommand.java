package InputHandler.command;

public class QuitCommand extends UserCommand{
    @Override
    public void execute () {
        System.out.print("     Bye. Hope to see you again soon!\n");
    }
}
