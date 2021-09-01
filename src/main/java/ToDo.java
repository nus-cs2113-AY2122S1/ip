public class ToDo extends Task {
    public ToDo(String desc) {
        super(desc);
    }

    public String printStatus() {
        return "[t] [" + getStatus() + "] " + getDescription();
    }
}
