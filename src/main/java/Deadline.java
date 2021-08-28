public class Deadline extends Task {
    protected String due;
    protected int keywordIdx = 0;

    public String extractDeadline(String inp) {
        this.keywordIdx = inp.indexOf("/");
        return inp.substring(keywordIdx + 4);
    }

    public String getDueString() {
        return "(by: " + this.due + ")";
    }

    public Deadline(String deadlineDescription) {
        super(deadlineDescription, "deadline");
        this.due = extractDeadline(deadlineDescription);
        super.description = deadlineDescription.substring(9, this.keywordIdx) + getDueString();
    }
}
