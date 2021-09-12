public class FilterInput {

    private static final int DESCRIPTION_PARAMETERS = 2;
    private static final int FIRST_ARRAY_PARAMETER = 0;
    private static final int SECOND_ARRAY_PARAMETER = 1;
    /**
     * Returns lateral location of the specified position.
     *
     * @param words String input by user.
     */
    public static void checkCommand(String[] words) throws
            DukeException {
        String[] descriptionInput = descriptionInput(words);
        switch (words[FIRST_ARRAY_PARAMETER]) {
        case Command.COMMAND_BYE:
            Greet.printGoodbyeMessage();
            break;
        case Command.COMMAND_LIST:
            Greet.printList();
            break;
        case Command.COMMAND_DONE:
            checkDescription(words[FIRST_ARRAY_PARAMETER],descriptionInput);
            int taskNumber = Integer.parseInt(words[SECOND_ARRAY_PARAMETER]);
            //might need to catch errors in the future
            Greet.checkDoneTask(taskNumber);
            break;
        case Command.COMMAND_TODO:
            checkDescription(words[FIRST_ARRAY_PARAMETER],descriptionInput);
            Todo todo = new Todo(descriptionInput[FIRST_ARRAY_PARAMETER]);
            Greet.addTask(todo);
            break;
        case Command.COMMAND_DEADLINE:
            checkDescription(words[FIRST_ARRAY_PARAMETER],descriptionInput);
            Deadline deadline = new Deadline(descriptionInput[FIRST_ARRAY_PARAMETER],
                    descriptionInput[SECOND_ARRAY_PARAMETER]);
            Greet.addTask(deadline);
            break;
        case Command.COMMAND_EVENT:
            checkDescription(words[FIRST_ARRAY_PARAMETER],descriptionInput);
            Event event = new Event(descriptionInput[FIRST_ARRAY_PARAMETER],
                    descriptionInput[SECOND_ARRAY_PARAMETER]);
            Greet.addTask(event);
            break;
        default:
            throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private static void checkDescription(String command, String[] descriptionInput) throws DukeException {
        if (descriptionInput[FIRST_ARRAY_PARAMETER].equals("")) {
            switch(command){
            case Command.COMMAND_TODO:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_TODO);
            case Command.COMMAND_DEADLINE:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_DEADLINE);
            case Command.COMMAND_EVENT:
                throw new DukeException(ErrorMessage.EXCEPTION_MISSING_DESCRIPTION_EVENT);
            default:
                throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_COMMAND);
            }
        }
    }
    private static void checkTimeframe(String[] descriptionInput) throws DukeException {
        if (descriptionInput[SECOND_ARRAY_PARAMETER].equals("")) {
            throw new DukeException(ErrorMessage.EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_KEYWORD);
        }
    }

    private static String[] descriptionInput(String[] words) {
        String[] output = new String[DESCRIPTION_PARAMETERS];
        output[FIRST_ARRAY_PARAMETER] = "";
        output[SECOND_ARRAY_PARAMETER] = "";

        int changeString = FIRST_ARRAY_PARAMETER;
        // mistake over here
        for (int counter = 1; counter < words.length; counter++) {
            if (words[counter].equals("/by") || words[counter].equals("/at")) {
                //Switch from string0 to string1
                changeString = SECOND_ARRAY_PARAMETER;
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
