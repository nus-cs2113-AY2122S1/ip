package duke;

import duke.exceptionHandler.DukeException;
import duke.taskOperations.StringToRemoveFormat;
import duke.taskType.Deadline;
import duke.taskType.Event;
import duke.taskType.Task;
import duke.taskType.ToDo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    public static int numberOfTasks = 0;
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void deleteTask(String userInputString, Storage dukeTaskText) throws DukeException {
        try {
            int taskNumberToDelete = Integer.parseInt(userInputString.split(" ")[1]);
            String textToRemove;
            boolean lastLine = false;

            if (taskNumberToDelete == numberOfTasks && numberOfTasks != 1) {
                lastLine = true;
            }

            if (taskNumberToDelete <= numberOfTasks) {
                tasks.get(taskNumberToDelete - 1).deletedSuccessfully(numberOfTasks - 1);

                String typeOfTask = tasks.get(taskNumberToDelete - 1).toString().substring(1,2);
                String taskIsDone = tasks.get(taskNumberToDelete - 1).toString().substring(4,5);
                String rawTaskDescription;
                if (typeOfTask.equals("E") || typeOfTask.equals("D")) {
                    rawTaskDescription = tasks.get(taskNumberToDelete - 1).toRawString().substring(7);
                }
                else {
                    rawTaskDescription = tasks.get(taskNumberToDelete - 1).toString().substring(7);
                }
                textToRemove = StringToRemoveFormat.getStringToRemove(typeOfTask, taskIsDone, rawTaskDescription);

                dukeTaskText.removeLineFromFile("./data/duke.txt", textToRemove, lastLine, taskNumberToDelete, numberOfTasks);

                tasks.remove(taskNumberToDelete - 1);
                numberOfTasks -= 1;
            } else {
                throw new DukeException("Please Enter the Legit Task Number to Delete... Or I won't talk to you!");
            }
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("Please Enter the Legit Task Number to Delete... Or I won't talk to you!");
        }
    }

    /**
     * Adds a new todo task with the description of task provided. Format is
     * strictly: todo <description>
     *
     * @param userInputString which contains the "todo" command along with
     *                        the description of the todo task. No time
     *                        details is needed here.
     */
    public static void addToDo(String userInputString, Storage dukeTaskText) throws DukeException {
        try {
            String taskName = userInputString.substring(5);

            if (taskName.equals("")) {
                throw new DukeException("The description of a todo cannot be empty.");
            }

            if (dukeTaskText.saveToDo(taskName) == true) {
                tasks.add(new ToDo(taskName));
                numberOfTasks += 1;

                tasks.get(numberOfTasks - 1).printAddingStatus(numberOfTasks - 1);
            } else {
                throw new DukeException("Please enter a different description. Or I will destroy your computer!");
            }
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Adds a new deadline task with the description of deadline task provided
     * along with the time which is due "by". The format of command is quite strict:
     * deadline <description> /by <time>
     *
     * Trail and Leading space are compulsory for /by separator
     *
     * @param userInputString which contains the "deadline" command along with
     *                        the description of the deadline task and followed
     *                        by a "/" to separate the due time "by".
     */
    public static void addDeadline(String userInputString, Storage dukeTaskText) throws DukeException {
        try {
            if (userInputString.indexOf(" /by ") == -1) {
                throw new DukeException("Don't forget to add /by (must have trail and lead whitespace) to separate description and deadline");
            }
            else {
                String taskName = userInputString.substring(9).split("/")[0];
                String by = userInputString.substring(9).split("/")[1];

                addDeadlineCheckDescription(dukeTaskText, taskName, by);
            }
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description and deadline info of deadline cannot be empty!");
        }
    }

    public static void addDeadlineCheck(Storage dukeTaskText, String taskName, String by) throws DukeException {
        String formattedDateTime;
        try {
            String date = by.split(" ")[1];
            String time = by.split(" ")[2];
            formattedDateTime = date + "T" + time;
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The deadline is not stated correctly");
        }

        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(formattedDateTime);
        } catch (DateTimeParseException formatIssue) {
            throw new DukeException("Deadline be in the format of: yyyy-mm-dd HH:mm");
        }

        if (dukeTaskText.saveDeadline(taskName, by) == true) {
            tasks.add(new Deadline(taskName, by));
            numberOfTasks += 1;

            tasks.get(numberOfTasks - 1).printAddingStatus(numberOfTasks - 1);
        } else {
            throw new DukeException("Please enter a different description. Or I will destroy your computer!");
        }
    }

    public static void addDeadlineCheckDescription(Storage dukeTaskText, String taskName, String by) throws DukeException {
        if (taskName.equals("") || by.equals("by ")) {
            throw new DukeException("The description and event time info of event cannot be empty.");
        } else {
            addDeadlineCheck(dukeTaskText, taskName, by);
        }
    }

    /**
     * Adds a new event task with the description of task provided and time
     * which it is happening "at". The format of command is quite strict:
     * event <description> /at <time>
     *
     * Trail and Leading space are compulsory for /at separator
     *
     * @param userInputString which contains the "event" command along with
     *                        the description of the event task followed by
     *                        a "/" to separate the happening time "at".
     */
    public static void addEvent(String userInputString, Storage dukeTaskText) throws DukeException {
        try {
            if (userInputString.indexOf(" /at ") == -1) {
                throw new DukeException("Don't forget to add /at (must have trail and lead whitespace) to separate description and event time");
            } else {
                String taskName = userInputString.substring(6).split("/")[0];
                String at = userInputString.substring(6).split("/")[1];

                addEventCheckDescription(dukeTaskText, taskName, at);
            }
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The description and event time info of event cannot be empty.");
        }
    }

    public static void addEventCheck(Storage dukeTaskText, String taskName, String at) throws DukeException {
        String formattedDateTime;
        try {
            String date = at.split(" ")[1];
            String time = at.split(" ")[2];
            formattedDateTime = date + "T" + time;
        } catch (IndexOutOfBoundsException indexOutOfBound) {
            throw new DukeException("The deadline is not stated correctly");
        }

        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(formattedDateTime);
        } catch (DateTimeParseException formatIssue) {
            throw new DukeException("Deadline be in the format of: yyyy-mm-dd HH:mm");
        }

        if (dukeTaskText.saveEvent(taskName, at) == true) {
            tasks.add(new Event(taskName, at));
            numberOfTasks += 1;

            tasks.get(numberOfTasks - 1).printAddingStatus(numberOfTasks - 1);
        } else {
            throw new DukeException("Please enter a different description. Or I will destroy your computer!");
        }
    }

    public static void addEventCheckDescription(Storage dukeTaskText, String taskName, String at) throws DukeException {
        if (taskName.equals("") || at.equals("at ")) {
            throw new DukeException("The description and event time info of event cannot be empty.");
        } else {
            addEventCheck(dukeTaskText, taskName, at);
        }
    }

    /**
     * Prints the current list of task when the "list" command is
     * entered to the terminal.
     */
    public static void printTaskList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print("     ");
            System.out.print((i + 1) + ".");
            tasks.get(i).printStatus();
        }
        System.out.println("    ____________________________________________________________");
    }


    /**
     * Marks a certain task based on task number as finished.
     *
     * @param userInputString which contains the "done" command along with the
     *                        finished task number.
     */
    public static void finishTask(String userInputString, Storage dukeTaskText) throws DukeException, IOException {
        int taskNumber = Integer.parseInt(userInputString.split(" ")[1]);

        if (taskNumber <= numberOfTasks) {
            tasks.get(taskNumber - 1).markAsDone();
        } else {
            throw new DukeException("Please Enter the Legit Task Number... Or I won't talk to you!");
        }

        dukeTaskText.saveFinishedTask(tasks.get(taskNumber - 1).toRawString());
    }

    public static void findTask(String userInputString) {
        String keyWord = userInputString.split(" ")[1];
        ArrayList<Task> matchedTaskList = (ArrayList<Task>) tasks.stream()
                                            .filter(n -> { return n.toString().contains(keyWord); })
                                            .collect(Collectors.toList());

        int sizeOfMatchedList = matchedTaskList.size();

        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < sizeOfMatchedList; i += 1) {
            System.out.print("     ");
            System.out.print((i + 1) + ".");
            matchedTaskList.get(i).printStatus();
        }
        System.out.println("    ____________________________________________________________");
    }
}
