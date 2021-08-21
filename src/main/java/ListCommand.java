public class ListCommand extends UserInput {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute() {
        for (int i = 0; i < UserInput.tasksNum; i++) {
            System.out.println("     " + (i + 1) + ". " + UserInput.tasks[i].toString());
        }
    }
}
