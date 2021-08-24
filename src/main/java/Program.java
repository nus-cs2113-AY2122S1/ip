import java.util.Objects;

public class Program {
    //when true, the program exits
    private boolean terminateHal = false;

    public void executeTask(String string) {
        if (Objects.equals(string, "list")) {
            executeList();
        } else if (Objects.equals(string, "blah")) {
            executeBlah();
        } else if (Objects.equals(string, "bye")) {
            this.executeBye();
        } else {
            System.out.println("Sorry, I am not smart enough to understand your high level commands! Try something simpler!");

        }

    }

    public static void executeList() {
        System.out.println("____________________________________________________________");
        System.out.println("list");
        System.out.println("____________________________________________________________");
    }

    public static void executeBlah() {
        System.out.println("____________________________________________________________");
        System.out.println("blah");
        System.out.println("____________________________________________________________");
    }

    public void executeBye() {
        System.out.println("bye");
        this.setTerminateHal(true);
    }

    public Boolean getTerminateHal() {
        return terminateHal;
    }

    public void setTerminateHal(boolean terminateHal) {
        this.terminateHal = terminateHal;
    }
}
