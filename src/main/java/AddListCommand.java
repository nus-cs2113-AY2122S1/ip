class AddListCommand extends UserCommand {
    private String addCommand;

    AddListCommand (String addCommand, TaskList tasks) {
        super(tasks);
        this.addCommand = addCommand;
    }

    @Override
    public void execute () {
        System.out.println("    Got it. I've added this task:");
        int totalTasksNum = tasks.addList(this.addCommand);
<<<<<<< HEAD
        System.out.println("     Now you have " + totalTasksNum +
                ((totalTasksNum > 1) ? " tasks" : " task") + " in the list.");
=======
        System.out.println("    Now you have " + totalTasksNum + " tasks in the list.");
>>>>>>> parent of 2fcb7ff (textUiTesting)
    }
}
