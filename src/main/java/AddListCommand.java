public class AddListCommand extends UserCommand {
    private String addCommand;

    AddListCommand (String addCommand, TaskList tasks) {
        super(tasks);
        this.addCommand = addCommand;
    }

    @Override
    public void execute () {
        System.out.println("    Got it. I've added this task:");
        int totalTasksNum = tasks.addList(this.addCommand);
        System.out.println("    Now you have " + totalTasksNum + " tasks in the list.");
    }
}
