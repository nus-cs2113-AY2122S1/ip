package duke.templar;

import duke.exception.*;
import java.util.ArrayList;


public class TaskManager {
    public static final String MESSAGE_DIVIDER = "____________________________________________________________";
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


    public TaskManager(ArrayList<Task> tasks) {
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
                tasks.add(newDeadline);

                System.out.println(MESSAGE_DIVIDER);
                System.out.println("[DUKE:]");
                System.out.println("...understood.");
                System.out.println("============= TASK ACQUIRED: " + newDeadline + "=============");
                System.out.println("current execution total: " + tasks.size());
                System.out.println(MESSAGE_DIVIDER);

            } else if (line.startsWith("todo")) {
                String[] todoSplit = line.split(" ", 2);
                if (todoSplit.length != 2) {
                    throw new TodoInvalidFormatException();
                }
                String todoDescription = todoSplit[1];
                ToDo newToDo = new ToDo(todoDescription);
                tasks.add(newToDo);

                System.out.println(MESSAGE_DIVIDER);
                System.out.println("[DUKE:]");
                System.out.println("...understood.");
                System.out.println("============= TASK ACQUIRED: " + newToDo + "=============");
                System.out.println("current execution total: " + tasks.size());
                System.out.println(MESSAGE_DIVIDER);

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
                tasks.add(newEvent);

                System.out.println(MESSAGE_DIVIDER);
                System.out.println("[DUKE:]");
                System.out.println("...understood.");
                System.out.println("============= TASK ACQUIRED: " + newEvent + "=============");
                System.out.println("current execution total: " + tasks.size());
                System.out.println(MESSAGE_DIVIDER);
            }

            if (line.startsWith("print commands")) {
                System.out.println("VALID COMMANDS ARE:");
                for (int i = 1; i < validCommands.length; i++) {
                    System.out.print(validCommands[i] +" ");
                }
                System.out.print("\n");
            }

            if (line.contains("list")) {
                printList(tasks); //print the current list

            } else if (line.startsWith("done")) {
                String[] number = line.split(" ");
                if (number.length != 2 || !isNumerical(number[1])) {
                    throw new TaskNumberInvalidException();
                }
                int taskNumber = Integer.parseInt(number[1]);
                if (taskNumber <= tasks.size()) {
                    taskDone[doneTask(taskNumber, tasks)] = 1; //mark task as done in memory
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
                    deleteTask(taskNumber, tasks); //mark task as done in memory
                } else {
                    throw new NoSuchTaskException();
                }
            } else if (!line.startsWith("bye")) {
                System.out.println(MESSAGE_DIVIDER);
                System.out.println("[The Templar:]");
                System.out.println("What further assistance do you require?");
                System.out.println(MESSAGE_DIVIDER);
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

    /*
    method returns which task (in tasks array) has been completed and also prints the result
    *
    @params taskNumber the input string
    @params tasks the array of tasks
    *
    @return the task (in tasks array) that has been marked done
     */
    public static int doneTask(int taskNumber, ArrayList<Task> tasks) {
        tasks.get(taskNumber - 1).setDone(true); //mark task as done
        System.out.println("TARGET NEUTRALISED: " + taskNumber + ". " + tasks.get(taskNumber - 1));
        return taskNumber - 1;

    }


    public static void deleteTask(int taskNumber, ArrayList<Task> tasks) {
        System.out.println("TARGET REMOVED: " + taskNumber + ". " + tasks.get(taskNumber - 1));
        tasks.remove(taskNumber-1);
    }



    /*
    method prints the current updated task list when called
    *
    @params tasks the array of tasks
     */
    public static void printList(ArrayList<Task> tasks) {
        System.out.println(MESSAGE_DIVIDER);
        System.out.println("PENDING HIT LIST:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.size() != 0) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
        }
        System.out.println(MESSAGE_DIVIDER);

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

