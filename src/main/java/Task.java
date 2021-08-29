public class Task {
    protected String description;
    protected boolean isComplete;

    Task(String inputTask) {
        description = inputTask;
        isComplete = false;
    }

    void markComplete() {
        isComplete = true;
    }
}
