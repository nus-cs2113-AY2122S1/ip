public class AddList extends UserInput {
    public AddList (String userInput) {
        super(userInput);
    }

    @Override
    public void execute () {
        tasks[tasksNum] = new Task(userInput, false);
        System.out.println("     added: " + userInput);
        tasksNum++;
    }
}
