package duke.task;

public class Task {
    private String description;
    private boolean isDone = false;
    private boolean isToDo = false;
    private boolean isEvent = false;
    private int howLong = 0;
    private String deadline;
    private String eventDescription;

    public Task(String description) {
        if (!description.equals(null)) {
            this.description = description;
        }
    }

    public Task(String deadline, String type) {
        type = type.toLowerCase();
        if (type.equals("todo")) {
            String[] descDeadline = deadline.split("/", 2);
            this.description = descDeadline[0].trim();
            if (descDeadline.length == 2) {
                this.deadline = descDeadline[1].trim();
                this.isToDo = true;
            }
        }
        else if (type.equals("event")) {
            String[] descEvent = deadline.split("/", 2);
            this.description = descEvent[0].trim();
            if (descEvent.length == 2) {
                this.eventDescription = descEvent[1].trim();
                this.isEvent = true;
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getHowLong() {
        return howLong;
    }

    public void setHowLong(int howLong) {
        this.howLong = howLong;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
        this.isToDo = true;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setToDo(boolean hasDeadline) {
        this.isToDo = hasDeadline;
    }

    public boolean isToDo() {
        return isToDo;
    }

    public boolean isEvent() {
        return isEvent;
    }

    public char getType() {
        if (this.isToDo && !this.getDeadline().equals("")) {
            return 'D'; //deadline
        }
        else if (this.isEvent) {
            return 'E';
        }
        else return 'T';    //todo or task by mutual exclusion
    }
}
