public class Event extends Task{

    protected String deadline;

    public Event(String description) {
        super(description.substring(description.indexOf("event") + 5, description.indexOf("/")).trim());
        deadline = description.substring(description.indexOf("/")).replace("/at", "").trim();
    }
    @Override
    public String getTaskType() {
        return ("E");
    }

    @Override
    public String getDeadline(){
        return (deadline);
    }

    @Override
    public void printMarkAsDoneMessage(int taskNumber){
        System.out.println("Nice! I've marked this task as done:\n" + (taskNumber + 1) + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description + " (at:" + getDeadline() + ")");
    }

    @Override
    public void printTaskList(int listIndex){
        System.out.println(listIndex + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description + " (at:" + getDeadline() + ")");
    }
}
