public class Event extends Task {

    private String name; //name of task only (eg return book)
    protected String at; //time

    public Event(String name, String at) {
        description = name + " (at: " + at + ")"; //name + time
        this.at = at;
        type = "E";
        this.name = name;
    }

    @Override
    public void printTaskNotif() {
        System.out.println("Got it. I've added this task:");
        System.out.println("[E] [ ] " + description);
    }

}
