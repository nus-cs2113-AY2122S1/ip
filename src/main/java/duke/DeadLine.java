package duke;

public class DeadLine extends Task {
    public static final String MARK_HAS_DEADLINE = "[D]";
    public DeadLine(String deadline) {
        super(deadline);
    }

    @Override
    public String getTask() {
        int indexDivider = task.indexOf("/");
        String taskContent = task.substring(0,indexDivider).trim();
        String deadlineContent = task.substring(indexDivider + 1).trim();
        String[] deadlineSplit = deadlineContent.split(" ", 2);
        String deadline = deadlineSplit[1].trim();
        if(isDone) {
            return MARK_HAS_DEADLINE + Task.MARK_AS_DONE + " " + taskContent +  " (by: " + deadline + ")";
        } else {
            return MARK_HAS_DEADLINE + Task.MARK_AS_NOT_DONE + " " + taskContent +  " (by: " + deadline + ")";
        }
    }

    @Override
    public String toString() {
        return "deadline " + task;
    }

}

