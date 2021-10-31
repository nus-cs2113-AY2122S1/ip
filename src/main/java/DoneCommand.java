public class DoneCommand extends Command{
    /** Random large negative number assigned to taskIndex, but updated with actual task's index in TaskList ArrayList */
    protected int taskIndex = -100;
    final static String ERROR_BOUNDARY = ">>".repeat(30);

    /**
     * Checks Task as completed by calling completeTask() method from
     * TaskList object.
     * Else, prints out error message if input index is wrong.
     *
     * @param tl TaskList object storing all user-created tasks.
     */
    @Override
    public void execute(TaskList tl) {
        if (taskIndex >= 0 & taskIndex < tl.getLength()) {
            tl.completeTask(taskIndex);
        } else if (taskIndex != -100) {
            System.out.println("\t" + ERROR_BOUNDARY);
            System.out.println("\tLIST IS CURRENTLY EMPTY OR INPUT INDEX IS OUT OF RANGE!");
            System.out.println("\t" + ERROR_BOUNDARY + System.lineSeparator());
        }
    }

    public DoneCommand(String doneCommandInput) {
        try {
            taskIndex = Integer.parseInt(doneCommandInput.replaceAll("[^0-9]", "")) - 1;
        } catch (NumberFormatException e) {
            System.out.println("\t" + ERROR_BOUNDARY);
            System.out.println("\tPLEASE INPUT INVENTORY NO. OF THE TASK, SEPARATED BY SPACE\n" +
                    "\tAFTER 'complete' OR 'done' TO MARK TASK AS DONE.");
            System.out.println("\t" + ERROR_BOUNDARY + System.lineSeparator());
        }
    }
}
