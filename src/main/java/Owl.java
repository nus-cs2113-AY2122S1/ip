import DukeUtility.FileWriter;
import DukeUtility.OwlException;
import DukeUtility.Ui;

public class Owl {
    public static final String FULL_RELATIVE_MEMORY_PATH = "data/owlmemory";
    
    private TaskList tasks;
    private FileWriter fileWriter;
    private Ui ui;
    private Parser parser;
    
    public Owl(String filePath) {
        ui = new Ui();
        fileWriter = new FileWriter(filePath);
        tasks = new TaskList(fileWriter.readFile());
        parser = new Parser(tasks);
    }
    
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
