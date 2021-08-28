public class Deadline extends Task{

    protected String deadline;

    public Deadline(String description) {
        super(description.substring(description.indexOf("deadline") + 8, description.indexOf("/")).trim());
        deadline = description.substring(description.indexOf("/")).replace("/by", "").trim();
    }

    @Override
    public String getTaskType() {
        return ("D");
    }

    @Override
    public String getDeadline(){
        return (deadline);
    }

    @Override
    public void printMarkAsDoneMessage(int taskNumber){
        System.out.println("Nice! I've marked this task as done:\n" + (taskNumber + 1) + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description + " (by:" + getDeadline() + ")");
    }

    @Override
    public void printTaskList(int listIndex){
        System.out.println(listIndex + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description + " (by:" + getDeadline() + ")");
    }
}
