package duke;

import duke.command.Parser;
import duke.task.Task;

public class Duke {

    static TaskList tasks;

    private static boolean handleOneInputLine(String line) {
        String[] splitted = line.split("\\s+");
        switch (splitted[0]) {
        case "bye":
            return true;
        case "list":
            Ui.printWithIndex(tasks.getAllTasks());
            break;
        case "done": {
            Task target = tasks.getByIndex(Integer.parseInt(splitted[1]) - 1);
            target.setDoneStatus(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target);
            break;
        }
        case "delete": {
            Task target =
                tasks.removeByIndex(Integer.parseInt(splitted[1]) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(target);
            break;
        }
        case "find": {
            String needle = splitted[1];
            Ui.printWithIndex(tasks.findByKeyword(needle));
            break;
        }
        default:
            Task newTask;
            try {
                newTask = Parser.parseTask(splitted);
            } catch (ArrayIndexOutOfBoundsException |
                     IllegalArgumentException e) {
                throw new ArrayIndexOutOfBoundsException(
                    "OOPS! Some required arguments are missing.");
            }
            tasks.add(newTask);
            System.out.println("added: " + newTask);
        }
        return false;
    }

    /*
     * Do initialisation work
     */
    private static void initialise() {
        String greeting = "Hello! I'm Duke";
        String assist = "What can I do for you?";
        System.out.println(greeting);
        System.out.println(assist);
        tasks = Storage.readFromFile();
    }

    /*
     * Do some post-operation work such as cleanup
     */
    private static void finalise() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        initialise();
        for (String next; (next = Ui.nextLine()) != null;) {
            try {
                if (handleOneInputLine(next)) {
                    finalise();
                    return;
                }
            } catch (Exception e) { Ui.printException(e); }
        }
        finalise();
    }
}
