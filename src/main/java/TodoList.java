public class TodoList {
    private TodoListItem[] todoList;
    private int listCount;

    public TodoList() {
        this.todoList = new TodoListItem[100];
        this.listCount = 0;
    }

    public void addItem(String item) {
         this.todoList[listCount] = new TodoListItem(item);
         this.listCount += 1;
         System.out.printf("added: %s\n", item);
    }

    public void doneItem(String request) throws Exception{
        int listNumber = Integer.parseInt(request.substring(5, request.length()));
        this.todoList[listNumber - 1].setDone();
        System.out.printf("Nice! I've marked this task as done:\n\t[X] %s\n", todoList[listNumber - 1].getItem());
    }

    public void printItems() {
        if (listCount == 0) {
            System.out.println("Take a chill pill! Your todo list is empty");
        } else {
            for (int i = 0; i < listCount; i++) {
                String done = todoList[i].status() ? "X" : " ";
                System.out.printf("%d.[%s] %s\n", i + 1,done, todoList[i].getItem());
            }
        }
    }
}
