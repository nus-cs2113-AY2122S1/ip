import java.util.Scanner;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Task parse(String taskInfo){
        int i = taskInfo.indexOf(" /by ");
        String description = taskInfo.substring(0, i);
        String by = taskInfo.substring(i + 5);
        return new Deadline(description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
