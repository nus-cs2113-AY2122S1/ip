public class Deadline extends Task {
    protected String ddl;
    public Deadline(String content, String ddl) {
        super(content);
        this.ddl = ddl;
    }
    @Override
    public String toString() {
        return "[D]" + "[" + this.TaskStatus() + "] " + this.content
                + "(by: " + this.ddl + ")";
    }
}