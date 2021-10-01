package duke.extract;

public class ExtractDueTime extends Extract {
    public static final int DUE_TIME_INDEX = 1;

    /**
     * Extracts the due time of a deadline in the user input
     * @param userInput input from the user
     * @param deadlineInfo String array that contains deadline description and deadline due time
     * @param dividerPosition index of "/by" in the user input
     */
    public static void extract(String userInput, String[] deadlineInfo, int dividerPosition){
        int charAfterDividerPosition = dividerPosition + 1;
        deadlineInfo[DUE_TIME_INDEX] = userInput.substring(charAfterDividerPosition);
        deadlineInfo[DUE_TIME_INDEX] = deadlineInfo[DUE_TIME_INDEX].replace("by", "");
        deadlineInfo[DUE_TIME_INDEX] = deadlineInfo[DUE_TIME_INDEX].trim();
    }
}
