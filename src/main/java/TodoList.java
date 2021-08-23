public class TodoList {
    private String[] todoList;
    private int listCount;

    public TodoList() {
        this.todoList = new String[100];
        this.listCount = 0;
    }

    public void addItem(String item) {
         this.todoList[listCount] = item;
         this.listCount += 1;
    }

    public void printItems() {
        for(int i = 0; i < listCount; i++) {
            System.out.printf("%d. %s\n", i + 1, todoList[i]);
        }
    }
}
