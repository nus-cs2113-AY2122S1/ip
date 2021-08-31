public class Events extends Task{
    protected String eventDeadline;

    public Events(String description, boolean completed, String eventDeadline) {
        super(description, completed);
        this.eventDeadline = eventDeadline;
    }

    public String eventsIcon() {
        String completedIcon = "E";
        return  completedIcon;
    }

    public void initialiseEvent(){
        System.out.println("______________________________\n");
        System.out.println("[" + eventsIcon() + "]"
                + "[ ]"
                + taskName + "(" + eventDeadline + ")"
                + "has been added!\n");
    }
}
