public class Events extends Task{
    protected String eventDeadline;

    public Events(String taskName, boolean completed, String eventDeadline) {
        super(taskName, completed);
        this.eventDeadline = eventDeadline;
    }

    /**
     * Contains events Icon [E].
     *
     * @return Icon in String format.
     */
    public String eventsIcon() {
        String eventsIcon = "[E] ";
        return  eventsIcon;
    }

    /**
     * Output message when the event task is added.
     */
    public void initialiseEvent() {
        System.out.println("______________________________\n");
        System.out.println(eventsIcon() + "[ ]"
                + taskName + " " + eventDeadline + " "
                + " has been added!\n");
    }

    /**
     * Override the toString method of Events class.
     *
     * @return String message in the right format.
     */
    @Override
    public String toString() {
        String s = eventsIcon() + completedTaskIcon()
                 + taskName + eventDeadline; //super.taskName super.completedTaskIcon()
        return s;
    }
}