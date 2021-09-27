import Exceptions.EmptyTaskException;
import Exceptions.InvalidCommandException;
import Exceptions.UnknownCommandException;

public class Parser {

    public static void processLine(TaskList taskList, String line) throws UnknownCommandException, EmptyTaskException, InvalidCommandException {
        boolean isTodoTask = line.startsWith("todo");
        boolean isDeadlineTask = line.startsWith("deadline");
        boolean isEventTask = line.startsWith("event");
        boolean isProperTask = isTodoTask || isDeadlineTask || isEventTask;

        if (line.equals("list")) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are your remaining tasks:");

            for (int i = 1; i <= taskList.list.size(); i++) {
                System.out.println(
                        Integer.toString(i)
                                + ". "
                                + taskList.list.get(i - 1)
                );
            }
            System.out.println("____________________________________________________________\n");

        } else if (line.startsWith("done")) {
            try {
                int i = Integer.parseInt(line.substring(5)) - 1;
                taskList.list.get(i).markAsDone();

                System.out.println("____________________________________________________________\n"
                        + "Well done! I've marked this task as done: \n"
                        + taskList.list.get(i)
                        + System.lineSeparator()
                        + "____________________________________________________________\n"
                );
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please mark a valid task number as done!\n");

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please mark a task number as done!\n");
            }

        } else if (isProperTask) {
            //checks for task commands
            if (isTodoTask) {
                taskList.addNewTodo(line);
            } else if (isDeadlineTask) {
                taskList.addNewDeadline(line);
            } else {
                taskList.addNewEvent(line);
            }

            System.out.println("____________________________________________________________\n"
                    + "Got it! I've added this task: "
                    + System.lineSeparator()
                    + "  "
                    + taskList.list.get(taskList.list.size() - 1)
                    + System.lineSeparator()
                    + "Now you have "
                    + Integer.toString(taskList.list.size())
                    + " tasks left in the list."
                    + System.lineSeparator()
                    + "____________________________________________________________\n"
            );


        } else if (line.startsWith("help")) {
            System.out.println(Ui.instructions);

        } else if (line.startsWith("delete")) {
            try {
                int i = Integer.parseInt(line.substring(7)) - 1;
                String deletedTask = taskList.list.get(i).toString();
                taskList.list.remove(i);
                System.out.println("____________________________________________________________\n"
                        + "Noted. I've removed this task: \n"
                        + deletedTask
                        + System.lineSeparator()
                        + "Now you have " + taskList.list.size() + " tasks in the list.\n"
                        + "____________________________________________________________\n"
                );

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Choose a task to delete!\n");

            } catch (NumberFormatException e) {
                System.out.println("Invalid task number. Please choose a valid task number to delete!\n");
            }

        } else {
            throw new UnknownCommandException();
        }
    }
}
