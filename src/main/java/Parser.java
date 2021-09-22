public class Parser {

    public static boolean lastCharacterSpaceChecker(String userInput) {
        int lastCharacterIndex = userInput.length() - 1;
        String lastCharacter = userInput.substring(lastCharacterIndex);
        if (lastCharacter.equals(" ")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean multipleSpacesChecker(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        for (String word : splitUserInput) {
            if (word.equals("")) {
                return true;
            }
        }
        return false;
    }

    public static boolean spaceErrorChecker(String userInput) {
        boolean lastCharacterIsSpace = lastCharacterSpaceChecker(userInput);
        boolean hasMultipleSpaces = multipleSpacesChecker(userInput);
        if (lastCharacterIsSpace || hasMultipleSpaces) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean listOrByeFormatChecker(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength > 1) {
            DukeException.printFormatError();
            return false;
        }
        return true;
    }

    public static boolean doneOrDeleteFormatChecker(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength != 2) {
            DukeException.printFormatError();
            return false;
        } else {
            try {
                Integer.parseInt(splitUserInput[1]);
            } catch (NumberFormatException e) {
                DukeException.printIndexError();
                return false;
            }
        }
        return true;
    }

    public static boolean todoFormatChecker(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength < 2) {
            DukeException.printFormatError();
            return false;
        }
        return true;
    }

    public static boolean deadlineFormatChecker(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength < 4) {
            DukeException.printFormatError();
            return false;
        } else if (!userInput.contains("/by")) {
            DukeException.printFormatError();
            return false;
        } else {
            int spaceIndex = userInput.indexOf(" ");
            int slashIndex = userInput.indexOf("/");
            if (spaceIndex + 1 == slashIndex) {
                DukeException.printFormatError();
                return false;
            }
        }
        return true;
    }

    public static boolean eventFormatChecker(String userInput) {
        String[] splitUserInput = userInput.split(" ");
        int userInputLength = splitUserInput.length;
        if (userInputLength < 4) {
            DukeException.printFormatError();
            return false;
        } else if (!userInput.contains("/at")) {
            DukeException.printFormatError();
            return false;
        } else {
            int spaceIndex = userInput.indexOf(" ");
            int slashIndex = userInput.indexOf("/");
            if (spaceIndex + 1 == slashIndex) {
                DukeException.printFormatError();
                return false;
            }
        }
        return true;
    }

    public static String[] parseUserInput(String userInput) {
        String[] parsedUserInput = new String[3];
        String[] splitUserInput = userInput.split(" ");
        String command = splitUserInput[0];
        switch (command) {
        case "list":
        case "bye":
            boolean correctListOrByeFormat = listOrByeFormatChecker(userInput);
            if (correctListOrByeFormat) {
                parsedUserInput[0] = command;
            } else {
                parsedUserInput[0] = "error";
            }
            break;
        case "done":
        case "delete":
            boolean correctDoneOrDeleteFormat = doneOrDeleteFormatChecker(userInput);
            if (correctDoneOrDeleteFormat) {
                int doneSpaceIndex = userInput.indexOf(" ");
                String doneDetail = userInput.substring(doneSpaceIndex + 1);
                parsedUserInput[0] = command;
                parsedUserInput[1] = doneDetail;
            } else {
                parsedUserInput[0] = "error";
            }
            break;
        case "todo":
            boolean correctTodoFormat = todoFormatChecker(userInput);
            if (correctTodoFormat) {
                int spaceIndex = userInput.indexOf(" ");
                String detail = userInput.substring(spaceIndex + 1);
                parsedUserInput[0] = command;
                parsedUserInput[1] = detail;
            } else {
                parsedUserInput[0] = "error";
            }
            break;
        case "deadline":
            boolean correctDeadlineFormat = deadlineFormatChecker(userInput);
            if (correctDeadlineFormat) {
                int spaceIndex = userInput.indexOf(" ");
                int slashIndex = userInput.indexOf("/");
                String detail = userInput.substring(spaceIndex + 1, slashIndex - 1);
                String deadlineBy = userInput.substring(slashIndex + 4);
                parsedUserInput[0] = command;
                parsedUserInput[1] = detail;
                parsedUserInput[2] = deadlineBy;
            } else {
                parsedUserInput[0] = "error";
            }
            break;
        case "event":
            boolean correctEventFormat = eventFormatChecker(userInput);
            if (correctEventFormat) {
                int spaceIndex = userInput.indexOf(" ");
                int slashIndex = userInput.indexOf("/");
                String detail = userInput.substring(spaceIndex + 1, slashIndex - 1);
                String eventAt = userInput.substring(slashIndex + 4);
                parsedUserInput[0] = command;
                parsedUserInput[1] = detail;
                parsedUserInput[2] = eventAt;
            } else {
                parsedUserInput[0] = "error";
            }
            break;
        default:
            DukeException.printCommandError();
            parsedUserInput[0] = "invalid";
            break;
        }
        return parsedUserInput;
    }

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
