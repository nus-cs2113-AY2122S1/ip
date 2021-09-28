public class DeleteCommand extends Command {
    protected int taskIndex = -100;
    protected final String ERROR_BOUNDARY = "!!".repeat(30);

    public void execute(TaskList tl) {
        if (taskIndex >= 0 & taskIndex < tl.getLength()) {
            tl.deleteTask(taskIndex);
        } else if (taskIndex != -100) {
            System.out.println("\t" + ERROR_BOUNDARY);
            System.out.println("\tLIST IS CURRENTLY EMPTY OR INPUT INDEX IS OUT OF RANGE!");
            System.out.println("\t" + ERROR_BOUNDARY + System.lineSeparator());
        }
    }

    public DeleteCommand(String deleteCommandInput) {
        try {
            taskIndex = Integer.parseInt(deleteCommandInput.replaceAll("[^0-9]", "")) - 1;
        } catch (NumberFormatException e) {
            System.out.println("\t" + ERROR_BOUNDARY);
            System.out.println("\tPLEASE INPUT INVENTORY NO. OF THE TASK, SEPARATED BY SPACE\n" +
                    "\tAFTER 'delete' OR 'remove' TO REMOVE TASK FROM LIST.");
            System.out.println("\t" + ERROR_BOUNDARY + System.lineSeparator());
        }
    }
}
