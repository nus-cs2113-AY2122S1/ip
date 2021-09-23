public class CommandBye extends Command{
    public static final String BYE_MESSAGE = "     Bye. Hope to see you again soon!";


    /**
     * Prints goodbye message upon exit
     */
    public void run(){
        Ui.printLineOnConsole();
        System.out.println(BYE_MESSAGE);
        Ui.printLineOnConsole();
    }
}
