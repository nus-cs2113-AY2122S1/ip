package duke;

public class FilterInput {

    /**
     * Returns lateral location of the specified position.
     *
     * @param words String input by user.
     */
    public static void checkCommand(String[] words) throws
            DukeEmptyDescriptionException,
            DukeUnrecognisedCommandException,
            DukeEmptyTimeframeException {
        String[] descriptionInput = descriptionInput(words);
        switch (words[0]) {
        case "bye":
            Greet.printGoodbyeMessage();
            break;
        case "list":
            Greet.printList();
            break;
        case "done":
            checkDescription(words[0],descriptionInput);
            int taskNumber = Integer.parseInt(words[1]);
            //might need to catch errors in the future
            Greet.checkDoneTask(taskNumber);
            break;
        case "todo":
            checkDescription(words[0],descriptionInput);
            Todo todo = new Todo(descriptionInput[0]);
            Greet.addTask(todo);
            break;
        case "deadline":
            checkDescription(words[0],descriptionInput);
            Deadline deadline = new Deadline(descriptionInput[0], descriptionInput[1]);
            Greet.addTask(deadline);
            break;
        case "event":
            checkDescription(words[0],descriptionInput);
            Event event = new Event(descriptionInput[0], descriptionInput[1]);
            Greet.addTask(event);
            break;
        default:
            throw new DukeUnrecognisedCommandException();
        }
    }

    private static void checkDescription(String command, String[] descriptionInput) throws DukeEmptyDescriptionException {
        if (descriptionInput[0].equals("")) {
            throw new DukeEmptyDescriptionException(command);
        }
    }
    private static void checkTimeframe(String[] descriptionInput) throws DukeEmptyTimeframeException {
        if (descriptionInput[1].equals("")) {
            throw new DukeEmptyTimeframeException();
        }
    }

    private static String[] descriptionInput(String[] words) {
        String[] output = new String[2];
        output[0] = "";
        output[1] = "";

        int changeString = 0;
        // mistake over here
        for (int counter = 1; counter < words.length; counter++) {
            if (words[counter].equals("/by") || words[counter].equals("/at")) {
                //Switch from string0 to string1
                changeString = 1;
                continue;
            }
            output[changeString] += " ";
            output[changeString] += words[counter];

        }
        return output;
    }

    private static boolean isDescription(int counter, String[] words) {
        return counter < words.length;
    }

    private static boolean isDate(int counter, String[] words) {
        return counter < words.length;
    }


}
