public interface TaskList {
    void printList() throws CommandException;
    void printToDo() throws CommandException;
    void printEvent() throws CommandException;
    void printDeadline() throws CommandException;
    void addTodo(String description);
    void addEvent(String description, String time);
    void addDeadline(String description, String deadline);
    void completeTask(int t) throws CommandException;
    int getListSize();
}
