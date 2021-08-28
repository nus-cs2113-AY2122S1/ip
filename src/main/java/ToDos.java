public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public void markAsDone() {
        MessageBubble msg = new MessageBubble();
        if (!isDone) {
            isDone = true;
            msg.addMessage("Nice! I've marked this ToDo as done:");
        } else {
            msg.addMessage("Warning! The ToDo is already done:");
        }
        msg.addMessage("[T][X] " + description);
        msg.printMessageBubble();
    }

    @Override
    public void markAsNotDone() {
        MessageBubble newMessage = new MessageBubble();
        if (isDone) {
            isDone = false;
            newMessage.addMessage("Ok! I've marked this ToDo as not done:");
        } else {
            newMessage.addMessage("Warning! The ToDo is not done yet:");
        }

        newMessage.addMessage("[T][ ] " + description);
        newMessage.printMessageBubble();
    }
}
