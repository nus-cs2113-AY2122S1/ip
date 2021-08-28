public class List {
    public static int MAX_LIST_ITEMS = 100;
    private Task[] items;
    private int numOfListItems;

    public List() {
        items = new Task[MAX_LIST_ITEMS];
        numOfListItems = 0;
    }

    public void addItem(String item) {
        items[numOfListItems] = new Task(item);
        numOfListItems++;

        PrintFormats.printMessageBubble("added: " + item);
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
        PrintFormats msg = new PrintFormats();
        msg.addMessage("Here are the tasks in your list:");
        for (int i = 0; i < numOfListItems; i++) {
            msg.addMessage(String.format("     %d:[%s] %s\n", i + 1, items[i].getStatusIcon(), items[i].description));
        }
        msg.printMessageBubble();
    }
}
