package karen.exception;

import java.io.IOException;

public abstract class ValidityAndErrorCheck {

    public static void checkTaskExceptions(String taskType, String separator, String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException {
        String[] inputWords = rawUserInput.split(" ");
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (taskType.equals("todo")) {
            return;
        }
        String[] separatedDescription = rawUserInput.split(separator, 2);
        if (separatedDescription.length == 1) {
            throw new IncorrectDescriptionFormatException();
        }
        if (!rawUserInput.contains(separator)) {
            throw new IncorrectDescriptionFormatException();
        }
        if (separatedDescription[0].equalsIgnoreCase(taskType)) {
            throw new IncorrectDescriptionFormatException();
        }
    }

    public static void checkCommandDescriptionExceptions(String command, String rawUserInput)
            throws NoDescriptionException, IncorrectDescriptionFormatException {
        String[] inputWords = rawUserInput.split(" ");
        if (command.equals("bye")) {
            if (inputWords.length != 1) {
                throw new IncorrectDescriptionFormatException();
            }
            return;
        }
        if (inputWords.length == 1) {
            throw new NoDescriptionException();
        }
        if (inputWords.length > 2) {
            throw new IncorrectDescriptionFormatException();
        }
    }

//    public static boolean isDate(String dateString) {
//        if (dateString.matches("\\d{2}-\\d{2}-\\d{4}")) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isDateAndTime(String dateString) {
//        if (dateString.matches("\\d{2}-\\d{2}-\\d{4} \\d{4}")) {
//            return true;
//        }
//        return false;
//    }



}
