package duke.extract;

public class ExtractDeadlineDescription extends Extract{
    public static void extract(String userInput, String[] deadlineInfo, int dividerPosition){
        deadlineInfo[DESCRIPTION_INDEX] = userInput.substring(START_OF_STRING, dividerPosition);
        deadlineInfo[DESCRIPTION_INDEX] = deadlineInfo[DESCRIPTION_INDEX].replace("deadline", "");
        deadlineInfo[DESCRIPTION_INDEX] = deadlineInfo[DESCRIPTION_INDEX].trim();
    }
}
