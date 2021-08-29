public class List {
    public static int MAX_LIST_ITEMS = 100;
    private Task[] items;
    private int numOfListItems;

    public List() {
        items = new Task[MAX_LIST_ITEMS];
        numOfListItems = 0;
    }

    public void addItem(Task task) {
        items[numOfListItems] = task;
        numOfListItems++;

        MessageBubble msg = new MessageBubble();
        msg.addMessage("Got it. I've added this task:");
        msg.addMessage(String.format(" %d:%s", numOfListItems, task.getDetails()));
        msg.addMessage("Now you have " + numOfListItems + " tasks in the list.");
        msg.printMessageBubble();
    }

    public void doneItem(int indexOfDoneItem) {
        int adjustedIndex = indexOfDoneItem - 1;
        items[adjustedIndex].markAsDone();
    }

    public void undoneItem(int indexOfDoneItem) {
        int adjustedIndex = indexOfDoneItem - 1;
        items[adjustedIndex].markAsNotDone();
    }

    public void printList() {
        MessageBubble msg = new MessageBubble();
        msg.addMessage("Here are the tasks in your list:");
        for (int i = 0; i < numOfListItems; i++) {
            msg.addMessage(String.format(" %d:%s", i + 1, items[i].getDetails()));
        }
        msg.printMessageBubble();
    }
}
