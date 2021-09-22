import DukeUtility.OwlException;

/**
 * A Parsing system that break down commands and execute them.
 */
public class Parser {
    private TaskList tasks; 
    public static final String INVALID_MESSAGE = "The command doesnt exist.....";
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads a given input from user and execute it 
     * 
     * @param command Given full user input.
     * @throws OwlException If command is invalid length or format.
     */
    public void execute(String command) throws OwlException {
        String[] inputs = splitToTwo(command);
        int commandLength = inputs.length;
        if(Verifier.isInvalidOnePartCmd(inputs, commandLength)) {
            OwlException.checkException(inputs);
        } else if (Verifier.isInvalidTwoPartCmd(inputs, commandLength)) {
            OwlException.checkException(inputs);
        } else if (Verifier.isDone(inputs[0])) {
            tasks.markCompletionOfTask(tasks, inputs[1]);
        } else if (Verifier.isList(inputs[0])) {
            tasks.listTask(tasks);
        } else if (Verifier.isTodo(inputs[0])) {
            tasks.addTodo(tasks, inputs);
        } else if (Verifier.isDeadline(inputs[0])) {
            tasks.addDeadline(tasks, inputs);
        } else if (Verifier.isEvent(inputs[0])) {
            tasks.addEvent(tasks, inputs);
        } else if (Verifier.isDelete(inputs[0])) {
            tasks.deleteTask(tasks, inputs[1]);
        } else if (Verifier.isFind(inputs[0])) {
            tasks.findTask(inputs[1],tasks);
        } else {
            System.out.println(INVALID_MESSAGE);
        }
    }

    private String[] splitToTwo(String command) {
        return command.split(" ", 2);
    }
}
