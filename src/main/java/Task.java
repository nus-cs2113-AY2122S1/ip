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
            msg.addMessage("Nice! I've marked this " + this.getClass().getName() + " as done:");
        } else {
            msg.addMessage("Warning! The " + this.getClass().getName() + " is already done:");
        }
        msg.addMessage(" " + this.getClassIndicator() + "[X] " + description);
        msg.printMessageBubble();
    }

    public void markAsNotDone() {
        MessageBubble newMessage = new MessageBubble();
        if (isDone) {
            isDone = false;
            newMessage.addMessage("Ok! I've marked this " + this.getClass().getName() + " as not done:");
        } else {
            newMessage.addMessage("Warning! The " + this.getClass().getName() + " is not done yet:");
        }

        newMessage.addMessage(" " + this.getClassIndicator() + "[ ] " + description);
        newMessage.printMessageBubble();
    }

    public String getDescription() {
        return description;
    }

    public String getClassIndicator() {
        if (Task.class.equals(this.getClass())) {
            return "";
        } else {
            return "[" + this.getClass().getName().substring(0, 1) + "]";
        }
    }
}
