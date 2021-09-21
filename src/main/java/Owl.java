import DukeUtility.FileWriter;
import DukeUtility.OwlException;
import DukeUtility.Ui;

public class Owl {
    public static final String INVALID_MESSAGE = "The command doesnt exist.....";
    public static final String FULL_RELATIVE_MEMORY_PATH = "data/owlmemory";
    
    private TaskList tasks;
    private FileWriter fileWriter;
    private Ui ui;
    private Verifier verifier;
    
    public Owl(String filePath) {
        ui = new Ui();
        verifier = new Verifier();
        fileWriter = new FileWriter(filePath);
        tasks = new TaskList(fileWriter.readFile());
    }
    
    public void run() {
        ui.printWelcome();
        String command = ui.readCommand();
        
        while(verifier.isNotBye(command)) {
            try {
                String[] inputs = command.split(" ", 2);
                int commandLength = inputs.length;
                if(verifier.isInvalidOnePartCmd(inputs, commandLength)) {
                    OwlException.checkException(inputs);
                } else if (verifier.isInvalidTwoPartCmd(inputs, commandLength)) {
                    OwlException.checkException(inputs);
                } else if (verifier.isDone(inputs[0])) {
                    tasks.markCompletionOfTask(tasks, inputs[1]);
                } else if (verifier.isList(inputs[0])) {
                    tasks.listTask(tasks);
                } else if (verifier.isTodo(inputs[0])) {
                    tasks.addTodo(tasks, inputs);
                } else if (verifier.isDeadline(inputs[0])) {
                    tasks.addDeadline(tasks, inputs);
                } else if (verifier.isEvent(inputs[0])) {
                    tasks.addEvent(tasks, inputs);
                } else if (verifier.isDelete(inputs[0])) {
                    tasks.deleteTask(tasks, inputs[1]);
                } else {
                    System.out.println(INVALID_MESSAGE);
                }
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
