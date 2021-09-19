package Type.task;

public abstract class Task implements Serializable{
    protected String description;
    protected boolean isDone = false;

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
//    private String description;
//    private boolean isDone = false;
//    private boolean isToDo = false;
//    private boolean isEvent = false;
//    private int howLong = 0;
//    private String deadline;
//    private String eventDescription;
//
//    public Task(String description) {
//        if (!description.equals(null)) {
//            this.description = description;
//        }
//    }
//
//    public Task(String deadline, String type) {
//        type = type.toLowerCase();
//        if (type.equals("todo")) {
//            String[] descDeadline = deadline.split("/", 2);
//            this.description = descDeadline[0].trim();
//            if (descDeadline.length == 2) {
//                this.deadline = descDeadline[1].trim();
//                this.isToDo = true;
//            }
//        }
//        else if (type.equals("event")) {
//            String[] descEvent = deadline.split("/", 2);
//            this.description = descEvent[0].trim();
//            if (descEvent.length == 2) {
//                this.eventDescription = descEvent[1].trim();
//                this.isEvent = true;
//            }
//        }
//    }
//    public Task(char type, String deadline, String description, Boolean isDone, String eventDescription) {
//        //1 set type
//        if (type == 'D') {
//            this.isToDo = true;
//            this.setDeadline(deadline);
//        } else if (type == 'E') {
//            this.isEvent = true;
//            this.eventDescription = eventDescription;
//        }
//        this.setDescription(description);
//        this.isDone = isDone;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public boolean isDone() {
//        return isDone;
//    }
//
//    public void setDone(boolean done) {
//        isDone = done;
//    }
//
//    public String getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(String deadline) {
//        this.deadline = deadline;
//        this.isToDo = true;
//    }
//
//    public String getEventDescription() {
//        return eventDescription;
//    }
//
//    public void setToDo(boolean hasDeadline) {
//        this.isToDo = hasDeadline;
//    }
//
//    public boolean isToDo() {
//        return isToDo;
//    }
//
//    public boolean isEvent() {
//        return isEvent;
//    }
//
//    public char getType() {
//        if (this.isToDo && !this.getDeadline().equals("")) {
//            return 'D';
//        } else if (this.isEvent) {
//            return 'E';
//        } else {
//            return 'T';      //todo or task by mutual exclusion
//        }
//    }
//
//    //print format: TYPE : DEADLINE : DESC
//    @Override
//    public String toString() {
//        return Character.toString(getType()) + '|' + getDeadline()
//                + '|' + getDescription()
//                + '|' + isDone() + '|' + ((this.isEvent)? this.getEventDescription() : "" + '|');
//    }
}
