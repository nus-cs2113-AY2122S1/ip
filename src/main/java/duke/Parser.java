package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static duke.Storage.writeData;
import static duke.TaskList.printTaskList;
import static duke.TaskList.printCompletedTask;
import static duke.TaskList.getToDoTask;
import static duke.TaskList.getDeadlineTask;
import static duke.TaskList.getEventTask;
import static duke.TaskList.printTask;
import static duke.TaskList.deleteTask;

public class Parser {

    public static void parse(String line, TaskList taskList, ArrayList<String> stringList,
                             ArrayList<String> dueDateList) {
        try {
            if (line.startsWith("done")) {
                try {
                    printCompletedTask(line);
                    writeData(taskList, stringList, dueDateList);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Sorry, please enter a correct task number");
                    Ui.printLine();
                }

            } else if (line.equals("list")) {
                printTaskList();

            } else if (line.startsWith("todo")) {
                try {
                    Todo todoTask = getToDoTask(line);
                    printTask(todoTask);
                    writeData(taskList, stringList, dueDateList);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("Please format your input as 'todo <task>'");
                    Ui.printLine();
                }

            } else if (line.startsWith("deadline")) {
                try {
                    Deadline deadlineTask = getDeadlineTask(line);
                    printTask(deadlineTask);
                    writeData(taskList, stringList, dueDateList);
                } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                    System.out.println("☹ OOPS!!! The format of deadline is wrong");
                    System.out.println("Please format your input as 'deadline <task>/<due date in dd-MM-yyyy " +
                            "and time in HH:mm>'");
                    Ui.printLine();
                }

            } else if (line.startsWith("event")) {
                try {
                    Event eventTask = getEventTask(line);
                    printTask(eventTask);
                    writeData(taskList, stringList, dueDateList);
                } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
                    System.out.println("☹ OOPS!!! The format of event is wrong");
                    System.out.println("Please format your input as 'event <task>/<event date in dd-MM-yyyy " +
                            "and time in HH:mm>'");
                    Ui.printLine();
                }

            } else if (line.startsWith("delete")) {
                try {
                    deleteTask(line);
                    if (taskList.getListCount() != 0) {
                        writeData(taskList, stringList, dueDateList);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The task number is not valid");
                    System.out.println("Please check again and format your input as 'delete <task number>'");
                    Ui.printLine();
                }

            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            Ui.printLine();
        }
    }
}
