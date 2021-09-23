package duke.templar;

import duke.exception.*;
import java.util.ArrayList;


public class Parser {
    boolean valid;
    public final ArrayList<Task> tasks;
    int[] taskDone = new int[100]; //this array stores 1 or 0 - task done or not
    String[] validCommands = new String[] {
        "print commands",
        "todo" ,
        "deadline",
        "event" ,
        "list" ,
        "done" ,
        "delete",
        "bye"
    };


    public Parser(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /*
    * method processes the input to determine which class of task it is and then create the correct object accordingly
    * as well as print the appropriate output to accompany task
    *
    * @params line the input string
    * @params tasks the array of type Task to store all the tasks
    * @params taskCount the number of tasks currently in the list
    *
    * @throws todoInvalidFormatException for invalid todo
    * @throws eventInvalidFormatException for invalid event
    * @throws deadlineInvalidFormatException for invalid deadline
    * @throws taskNumberInvalidException for alphabetical done input
    * @throws noSuchTaskException for done input of task not in list
    */
    public void processInput(String line, ArrayList<Task> tasks) throws CommandInvalidException, DeadlineInvalidFormatException, TodoInvalidFormatException, EventInvalidFormatException, TaskNumberInvalidException, NoSuchTaskException
    {
        valid = false; //default case
        try {
            for (int i = 0; i < validCommands.length; i++) {
                if (line.startsWith(validCommands[i])) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                throw new CommandInvalidException();
            }

            if (line.startsWith("deadline")) {
                String[] firstSplit = line.split(" ", 2);
                if (firstSplit.length != 2) {
                    throw new DeadlineInvalidFormatException();
                }
                String[] secondSplit = firstSplit[1].split(" /by ", 2);
                if (secondSplit.length != 2) {
                    throw new DeadlineInvalidFormatException();
                }
                String deadlineDescription = secondSplit[0];
                String deadlineDate = secondSplit[1];
                Deadline newDeadline = new Deadline(deadlineDescription, deadlineDate);
                TaskList.addTask(newDeadline,tasks);
                Ui.printTaskAcquired(newDeadline, tasks);

            } else if (line.startsWith("todo")) {
                String[] todoSplit = line.split(" ", 2);
                if (todoSplit.length != 2) {
                    throw new TodoInvalidFormatException();
                }
                String todoDescription = todoSplit[1];
                ToDo newToDo = new ToDo(todoDescription);
                TaskList.addTask(newToDo,tasks);
                Ui.printTaskAcquired(newToDo, tasks);

            } else if (line.startsWith("event")) {
                String[] firstSplit = line.split(" ", 2);
                if (firstSplit.length != 2) {
                    throw new EventInvalidFormatException();
                }
                String[] secondSplit = firstSplit[1].split(" /at ", 2);
                if (secondSplit.length != 2) {
                    throw new EventInvalidFormatException();
                }
                String eventDescription = secondSplit[0];
                String eventDate = secondSplit[1];
                Event newEvent = new Event(eventDescription, eventDate);
                TaskList.addTask(newEvent,tasks);
                Ui.printTaskAcquired(newEvent, tasks);
            }

            if (line.startsWith("print commands")) {
                Ui.printCommands(validCommands);
            }

            if (line.contains("list")) {
                Ui.printList(tasks); //print the current list

            } else if (line.startsWith("done")) {
                String[] number = line.split(" ");
                if (number.length != 2 || !isNumerical(number[1])) {
                    throw new TaskNumberInvalidException();
                }
                int taskNumber = Integer.parseInt(number[1]);
                if (taskNumber <= tasks.size()) {
                    taskDone[TaskList.doneTask(taskNumber, tasks)] = 1; //mark task as done in memory
                } else {
                    throw new NoSuchTaskException();
                }
            } else if (line.startsWith("delete")) {
                String[] number = line.split(" ");
                if (number.length != 2 || !isNumerical(number[1])) {
                    throw new TaskNumberInvalidException();
                }
                int taskNumber = Integer.parseInt(number[1]);
                if (taskNumber <= tasks.size()) {
                    TaskList.deleteTask(taskNumber, tasks); //mark task as done in memory
                } else {
                    throw new NoSuchTaskException();
                }
            } if (!line.startsWith("bye")) {
                Ui.printService();
            }

        }
        catch (CommandInvalidException commandInvalidException) {
            commandInvalidException.printCommandInvalidException();
        }
        catch (DeadlineInvalidFormatException deadlineInvalidFormatException) {
            deadlineInvalidFormatException.printDeadlineInvalidFormatException();
        }
        catch (EventInvalidFormatException eventInvalidFormatException) {
            eventInvalidFormatException.printEventInvalidFormatException();
        }
        catch (TodoInvalidFormatException todoInvalidFormatException) {
            todoInvalidFormatException.printTodoInvalidFormatException();
        }
        catch (TaskNumberInvalidException taskNumberInvalidException) {
            taskNumberInvalidException.printTaskNumberInvalidException();
        }
        catch (NoSuchTaskException noSuchTaskException) {
            noSuchTaskException.printNoSuchTaskException();
        }
    }

    public static boolean isNumerical(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch (NumberFormatException numberFormatException) {
            return false;
        }
    }
}

