public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIndicator() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        MessageBubble msg = new MessageBubble();
        if (!isDone) {
            isDone = true;
            msg.addMessage("Nice! I've marked this task as done:");
        } else {
            msg.addMessage("Warning! The task is already done:");
        }
        msg.addMessage("[X] " + description);
        msg.printMessageBubble();
    }

    public void markAsNotDone() {
        MessageBubble newMessage = new MessageBubble();
        if (isDone) {
            isDone = false;
            newMessage.addMessage("Ok! I've marked this task as not done:");
        } else {
            newMessage.addMessage("Warning! The task is not done yet:");
        }

        newMessage.addMessage("[ ] " + description);
        newMessage.printMessageBubble();
    }

    public String getDescription() {
        return description;
    }

    public String getClassIndicator() {
        if (Task.class.equals(this.getClass())) {
            return "";
        } else if (ToDos.class.equals(this.getClass())) {
            return "[T]";
        } else {
            return "";
        }
    }
}
