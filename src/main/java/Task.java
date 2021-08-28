public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        PrintFormats msg = new PrintFormats();
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
        PrintFormats newMessage = new PrintFormats();
        if (isDone) {
            isDone = false;
            newMessage.addMessage("Ok! I've marked this task as not done:");
        } else {
            newMessage.addMessage("Warning! The task is not done yet:");
        }

        newMessage.addMessage("[ ] " + description);
        newMessage.printMessageBubble();
    }

    public String printTaskWithStatus() {
        return getStatusIcon() + " " + description;
    }
}
