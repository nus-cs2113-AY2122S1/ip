package duke.extract;

public class ExtractDeadlineDescription extends Extract{
    /**
     * Extracts the description of a deadline in the user input
     * @param userInput input from the user
     * @param deadlineInfo String array that contains deadline description and deadline due time
     * @param dividerPosition index of "/by" in the user input
     */
    public static void extract(String userInput, String[] deadlineInfo, int dividerPosition){
        deadlineInfo[DESCRIPTION_INDEX] = userInput.substring(START_OF_STRING, dividerPosition);
        deadlineInfo[DESCRIPTION_INDEX] = deadlineInfo[DESCRIPTION_INDEX].replace("deadline", "");
        deadlineInfo[DESCRIPTION_INDEX] = deadlineInfo[DESCRIPTION_INDEX].trim();
    }
}
