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
}
