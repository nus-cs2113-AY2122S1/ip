import DukeUtility.OwlException;
import DukeUtility.Ui;
import DukeUtility.Parser;
import DukeUtility.Verifier;
import DukeUtility.FileWriter;
import TypeOfTasks.TaskList;

/**
 * OwlBot a smart tasklist tracker that allows you to manipulate and track deadlines,events,todos. 
 */
public class Owl {
    public static final String FULL_RELATIVE_MEMORY_PATH = "data/owlmemory";
    
    private TaskList tasks;
    private FileWriter fileWriter;
    private Ui ui;
    private Parser parser;
    /**
     * Initialises Owl's TaskList with the saved details in the storage file and set up user interface and verification system.
     * 
     * @param filePath The storage file's pathing in the user's pc.
     */
    public Owl(String filePath) {
        ui = new Ui();
        fileWriter = new FileWriter(filePath);
        tasks = new TaskList(fileWriter.readFile());
        parser = new Parser(tasks);
    }

    /**
     * Start the OwlBot program by starting to take user inputs.
     */
    public void run() {
        ui.printWelcome();
        String command = ui.readCommand();
        
        while(Verifier.isNotBye(command)) {
            try {
                parser.execute(command);
            } catch(OwlException owlException) {
                owlException.printErrorMsg();
            } catch(NumberFormatException numberFormatException) {
                System.out.println(numberFormatException.getMessage());
            }
            fileWriter.updateFile(tasks.getTasks());
            command = ui.readCommand();
        }
        ui.printBye();
    }
    
    
    public static void main(String[] args) {
       new Owl(FULL_RELATIVE_MEMORY_PATH).run();
    }
    
}
