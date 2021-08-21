public class List {
    public static int MAX_LIST_ITEMS = 100;
    private String[] items = new String[MAX_LIST_ITEMS];
    private int numOfListItems;

    public List(){
        items = new String[MAX_LIST_ITEMS];
        numOfListItems = 0;
    }

    public List(String[] args) {
        System.arraycopy(args, 0, this.items, 0, args.length);
        numOfListItems = args.length;
    }

    public void addItem(String item) {
        this.items[this.numOfListItems++] = item;

        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + item);
        System.out.println("    ____________________________________________________________");
    }

    public void printList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < this.numOfListItems; i++) {
            System.out.printf("     %d: %s%n", i+1, items[i]);
        }
        System.out.println("    ____________________________________________________________");
    }
}
