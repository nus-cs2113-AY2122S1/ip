package duke.TaskList;

import duke.ErrorHandling.CommandException;

public interface TaskListInterface {
    void printList() throws CommandException;
    void printToDo() throws CommandException;
    void printEvent() throws CommandException;
    void printDeadline() throws CommandException;
    void addTodo(String description, boolean isFromFile);
    void addEvent(String description, String eventDateTime, boolean isFromFile);
    void addDeadline(String description, String deadlineDateTime, boolean isFromFile);
    void completeTask(int t, boolean isFromFile) throws CommandException;
    int getListSize();
}
