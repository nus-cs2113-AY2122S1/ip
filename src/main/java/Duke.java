/**
 * <h1>Track Your Tasks!</h1>
 * * The Duke program keeps track of your tasks by categorising them
 * * into ToDos, Deadlines and Events and giving you the tools to
 * * organise them
 * * <p>
 * *
 * * @author  Edly Irsyad
 * * @version 0.2
 */
public class Duke {
    public static void main(String[] args) {
        Ui.welcome();
        Parser.parse();
    }
}
