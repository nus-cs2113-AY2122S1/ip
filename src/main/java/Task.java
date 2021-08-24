public class Task {
    String[] taskList = new String[100];
    protected String userInput;
    protected int numberOfTasks = 0;

    public Task() {
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        taskList[numberOfTasks] = userInput;
        numberOfTasks++;
        System.out.println("added: " + userInput);
    }

    public void displayTasks() {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i+1) + ". " + taskList[i]);
        }
    }
}
