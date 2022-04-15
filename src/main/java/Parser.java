/**
 * Contains all String parsing related methods.
 * Deals with user inputs and Duke's storage file.
 */
public class Parser {

    /**
     * Checks whether the last character of the user's input is a space.
     *
     * @param userInput User's input.
     * @return Boolean representing whether the last character of a user's input is a space.
     */
    public static boolean lastCharacterSpaceChecker(String userInput) {
        int lastCharacterIndex = userInput.length() - 1;
        String lastCharacter = userInput.substring(lastCharacterIndex);
        if (lastCharacter.equals(" ")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether there are consecutive spaces in the user's input.
     *
     * @param userInput User's input.
     * @return Boolean representing whether there are consecutive spaces in a user's input.
     */
    public static boolean multipleSpacesChecker(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        for (String word : splitUserInput) {
            if (word.equals("")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether there is a space error in the user's input.
     *
     * @param userInput User's input.
     * @throws DukeException If there is a space error in the user's input.
     */
    public static void spaceErrorChecker(String userInput) throws DukeException {
        boolean lastCharacterIsSpace = lastCharacterSpaceChecker(userInput);
        boolean hasMultipleSpaces = multipleSpacesChecker(userInput);
        if (lastCharacterIsSpace || hasMultipleSpaces) {
            throw new DukeException();
        }
    }

    /**
     * Checks the format of the user's list/bye command input.
     *
     * @param userInput User's input.
     * @return Boolean representing whether the format of the user's list/bye command input is correct.
     */
    public static void listOrByeFormatChecker(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength > 1) {
            throw new DukeException();
        }
    }

    /**
     * Checks the format of the user's done/delete command input.
     *
     * @param userInput User's input.
     * @return Boolean representing whether the format of the user's done/delete command input is correct.
     */
    public static void doneOrDeleteFormatChecker(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength != 2) {
            throw new DukeException();
        } else {
            try {
                Integer.parseInt(splitUserInput[1]);
            } catch (NumberFormatException e) {
                throw new DukeException();
            }
        }
    }

    /**
     * Checks the format of the user's todo command input.
     *
     * @param userInput User's input.
     * @return Boolean representing whether the format of the user's todo command input is correct.
     */
    public static void todoFormatChecker(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength < 2) {
            throw new DukeException();
        }
    }

    /**
     * Checks the format of the user's deadline command input.
     *
     * @param userInput User's input.
     * @return Boolean representing whether the format of the user's deadline command input is correct.
     */
    public static void deadlineFormatChecker(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength < 4) {
            throw new DukeException();
        } else if (!userInput.contains("/by")) {
            throw new DukeException();
        } else {
            int spaceIndex = userInput.indexOf(" ");
            int slashIndex = userInput.indexOf("/");
            if (spaceIndex + 1 == slashIndex) {
                throw new DukeException();
            }
        }
    }

    /**
     * Checks the format of the user's event command input.
     *
     * @param userInput User's input.
     * @return Boolean representing whether the format of the user's event command input is correct.
     */
    public static void eventFormatChecker(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength < 4) {
            throw new DukeException();
        } else if (!userInput.contains("/at")) {
            throw new DukeException();
        } else {
            int spaceIndex = userInput.indexOf(" ");
            int slashIndex = userInput.indexOf("/");
            if (spaceIndex + 1 == slashIndex) {
                throw new DukeException();
            }
        }
    }

    /**
     * Checks the format of the user's find command input.
     *
     * @param userInput User's input.
     * @return Boolean representing whether the format of the user's find command input is correct.
     */
    public static void findFormatChecker(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength != 2) {
            throw new DukeException();
        }
    }

    /**
     * Extracts important information from the user's input.
     *
     * @param userInput User's input.
     * @return List containing the user's command, task description and task details.
     */
    public static String[] parseUserInput(String userInput) {
        String[] parsedUserInput = new String[3];
        String[] splitUserInput = userInput.split(" ");
        String command = splitUserInput[0];
        switch (command) {
        case "list":
        case "bye":
            try {
                listOrByeFormatChecker(userInput);
                parsedUserInput[0] = command;
            } catch (DukeException d) {
                parsedUserInput[0] = "error";
                DukeException.printFormatError();
            }
            break;
        case "done":
        case "delete":
            try {
                doneOrDeleteFormatChecker(userInput);
                int doneSpaceIndex = userInput.indexOf(" ");
                String doneDetail = userInput.substring(doneSpaceIndex + 1);
                parsedUserInput[0] = command;
                parsedUserInput[1] = doneDetail;
            } catch (DukeException d) {
                parsedUserInput[0] = "error";
                DukeException.printFormatError();
            }
            break;
        case "todo":
            try {
                todoFormatChecker(userInput);
                int spaceIndex = userInput.indexOf(" ");
                String detail = userInput.substring(spaceIndex + 1);
                parsedUserInput[0] = command;
                parsedUserInput[1] = detail;
            } catch (DukeException d) {
                parsedUserInput[0] = "error";
                DukeException.printFormatError();
            }
            break;
        case "deadline":
            try {
                deadlineFormatChecker(userInput);
                int spaceIndex = userInput.indexOf(" ");
                int slashIndex = userInput.indexOf("/");
                String detail = userInput.substring(spaceIndex + 1, slashIndex - 1);
                String deadlineBy = userInput.substring(slashIndex + 4);
                parsedUserInput[0] = command;
                parsedUserInput[1] = detail;
                parsedUserInput[2] = deadlineBy;
            } catch (DukeException d) {
                parsedUserInput[0] = "error";
                DukeException.printFormatError();
            }
            break;
        case "event":
            try {
                eventFormatChecker(userInput);
                int spaceIndex = userInput.indexOf(" ");
                int slashIndex = userInput.indexOf("/");
                String detail = userInput.substring(spaceIndex + 1, slashIndex - 1);
                String eventAt = userInput.substring(slashIndex + 4);
                parsedUserInput[0] = command;
                parsedUserInput[1] = detail;
                parsedUserInput[2] = eventAt;
            } catch (DukeException d) {
                parsedUserInput[0] = "error";
                DukeException.printFormatError();
            }
            break;
        case "find":
            try {
                findFormatChecker(userInput);
                int doneSpaceIndex = userInput.indexOf(" ");
                String keyword = userInput.substring(doneSpaceIndex + 1);
                parsedUserInput[0] = command;
                parsedUserInput[1] = keyword;
            } catch (DukeException d) {
                parsedUserInput[0] = "error";
                DukeException.printFormatError();
            }
            break;
        default:
            DukeException.printCommandError();
            parsedUserInput[0] = "invalid";
            break;
        }
        return parsedUserInput;
    }

    /**
     * Converts a line in the storage file to parsedUserInput's format.
     *
     * @param fileLine Line in the storage file to be parsed.
     * @return List containing the user's command, task description and task details.
     */
    public static String[] parseFileLine(String fileLine) {
        String[] splitFileLine = fileLine.split(" ");
        String[] parsedFileLine = new String[3];
        String fileLineDescriptionAndDetails = fileLine.substring(8);
        int lineIndex = fileLineDescriptionAndDetails.indexOf("|");
        String category = splitFileLine[0];
        switch (category) {
        case "T":
            parsedFileLine[0] = "todo";
            parsedFileLine[1] = fileLineDescriptionAndDetails;
            break;
        case "D":
            parsedFileLine[0] = "deadline";
            parsedFileLine[1] = fileLineDescriptionAndDetails.substring(0, lineIndex - 1);
            parsedFileLine[2] = fileLineDescriptionAndDetails.substring(lineIndex + 2);
            break;
        case "E":
            parsedFileLine[0] = "event";
            parsedFileLine[1] = fileLineDescriptionAndDetails.substring(0, lineIndex - 1);
            parsedFileLine[2] = fileLineDescriptionAndDetails.substring(lineIndex + 2);
            break;
        default:
            break;
        }
        return parsedFileLine;
    }

}
