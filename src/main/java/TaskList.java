public interface TaskList {
    void printList();
    void printToDo();
    void printEvent();
    void printDeadline();
    void addTodo(String description);
    void addEvent(String description, String time);
    void addDeadline(String description, String deadline);
    void completeTask(int t);
}
