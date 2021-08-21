abstract public class UserInput {
    String userInput;
    static Task[] tasks = new Task[100];
    static int tasksNum = 0;

    public UserInput (String userInput) {
        this.userInput = userInput;
    }

    abstract public void execute ();
}
