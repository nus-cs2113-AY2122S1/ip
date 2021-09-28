package duke.TaskList;

import duke.ErrorHandling.CommandException;
import java.time.LocalDate;

public interface TaskListInterface {
    void printList() throws CommandException;
    void printDate(LocalDate dateSearched) throws CommandException;
    void printWord(String wordSearch) throws CommandException;
    void printToDo() throws CommandException;
    void printEvent() throws CommandException;
    void printDeadline() throws CommandException;
    void addTodo(String description, boolean isFromFile);
    void addEvent(String description, String eventDateTime, boolean isFromFile);
    void addDeadline(String description, String deadlineDateTime, boolean isFromFile);
    void completeTask(int t, boolean isFromFile);
    void deleteTask(int t);
    void clearTask();
    int getListSize();
}
