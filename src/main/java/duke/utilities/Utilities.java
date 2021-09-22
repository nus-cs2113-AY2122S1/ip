package duke.utilities;

public class Utilities {
    /**
     * Checks if word entered in user input is a valid number
     *
     * @param strNum a word extracted from user input
     * @return true if extracted word is a valid number
     */
    public static boolean isValidNumber(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
