public class Task {

    private boolean isDone;
    private String name;

    private static int totalTasks = 0;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        Task.totalTasks++;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        if (done) {
            System.out.println("Nice! I've marked this task as done:");
        }
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printStatus() {
        String isDoneFlag = " ";
        if (this.isDone) {
            isDoneFlag = "X";
        }
        System.out.printf("[%s] %s\n", isDoneFlag, this.getName());
    }
}
