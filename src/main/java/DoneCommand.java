public class DoneCommand extends Command {

    protected DoneCommand() {
        super("done");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        try {
            int taskNo = Integer.parseInt(data);
            if (taskNo < 1 || taskNo > unker.getTotalTasks()) {
                ui.printSection("Aiyo, you give me a task number that I don't have.");
            } else  {
                Task task = unker.getTask(taskNo - 1);
                if (!task.isDone()) {
                    task.setDone(true);
                    ui.printSection("Good job, this task finish already:",
                            "\t" + task, "");
                } else {
                    ui.printSection("You finish this task already leh:", "\t" + task, "");
                }
            }
        } catch (NumberFormatException nfe) {
            ui.printSection("Unker don't think that is a valid number leh.");
        }
    }
}
