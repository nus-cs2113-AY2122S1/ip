package duke;

public interface TaskList {
    void printList() throws CommandException;
    void printToDo() throws CommandException;
    void printEvent() throws CommandException;
    void printDeadline() throws CommandException;
    void addTodo(String description, boolean isFromFile);
    void addEvent(String description, String time, boolean isFromFile);
    void addDeadline(String description, String deadline, boolean isFromFile);
    void completeTask(int t, boolean isFromFile) throws CommandException;
    int getListSize();
}
