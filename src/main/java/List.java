public class List {
    public static int MAX_LIST_ITEMS = 100;
    private Task[] items;
    private int numOfListItems;

    public List() {
        items = new Task[MAX_LIST_ITEMS];
        numOfListItems = 0;
    }

    public void addItems(String[] items) {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < items.length; i++) {
            addItem(items[i], false);
        }
        System.out.println("    ____________________________________________________________");
    }

    public void addItem(String item) {
        addItem(item, true);
    }

    public void addItem(String item, boolean divLine) {
        items[numOfListItems] = new Task(item);
        numOfListItems++;

        if (divLine) System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + item);
        if (divLine) System.out.println("    ____________________________________________________________");
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
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < numOfListItems; i++) {
            System.out.printf("     %d:[%s] %s\n", i + 1, items[i].getStatusIcon(), items[i].description);
        }
        System.out.println("    ____________________________________________________________");
    }
}
