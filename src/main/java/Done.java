public class Done extends UserInput {

    public Done (String userInput) {
        super(userInput);
    }

    @Override
    public void execute () {
        String[] inputSplit = userInput.split(" ");
        int taskIndex = Integer.parseInt(inputSplit[1]) - 1;
        System.out.println(UserInput.tasks[taskIndex].setFinished());
    }
}
