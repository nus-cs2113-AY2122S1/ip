public class GetTasks extends DukeCommand {

    public static void followCommand(String getUserInput) throws DukeException {
        String userCommand = (getUserInput.split(" "))[0];
        switch (userCommand) {
        case "done":
            description = getUserInput.split(" ");
            if (description.length < 2 || Integer.parseInt(description[1]) == 0) {
                throw new InvalidTaskNumberException();
            }
            int taskNumber = Integer.parseInt(description[1]) - 1;
            if (tasks[taskNumber] == null) {
                throw new InvalidTaskNumberException();
            }
            if (tasks[taskNumber].getStatusIcon().equals("X")) {
                System.out.println(HORIZONTAL_LINE_TOP
                        + " I've already marked the task as done!\n"
                        + HORIZONTAL_LINE_BOTTOM);
            } else {
                tasks[taskNumber].markAsDone();
                System.out.println(tasks[taskNumber].printDone());
            }
            break;
        case "todo":
            description = getUserInput.split(" ");
            addTask(new ToDo(description[1], taskCount));
            break;
        case "deadline":
            int by = getUserInput.indexOf("/");
            separate = getUserInput.split("/by");
            description = separate[0].trim().split(" ");
            if ((by == -1 && (description[1] != null))
                    || (separate.length == 1)
                    || (separate[1].trim().isEmpty())) {
                throw new DeadlineTimingException();
            }
            String dueDate = separate[1].trim();
            addTask(new Deadline(description[1], dueDate, taskCount));
            break;
        case "event":
            int at = getUserInput.indexOf("/");
            separate = getUserInput.split("/at");
            description = separate[0].trim().split(" ");
            if ((at == -1 && (description[1] != null))
                    || (separate.length == 1)
                    || (separate[1].trim().isEmpty())) {
                throw new EventTimingException();
            }
            String eventTiming = separate[1].trim();
            addTask(new Event(description[1], eventTiming, taskCount));
            break;
        default:
            throw new InvalidCommandException();
        }
    }

}

