public class CommandBye extends Command{
    public static final String BYE_MESSAGE = "     Bye. Hope to see you again soon!";


    /**
     * Prints goodbye message upon exit
     */
    public void run(){
        System.out.println(Ui.DASH_LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(Ui.DASH_LINE);
    }
}
