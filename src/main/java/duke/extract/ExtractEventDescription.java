package duke.extract;

public class ExtractEventDescription extends Extract {
    /**
     * Extracts the description of an event in the user input
     * @param userInput input from the user
     * @param eventInfo String array that contains event description and event location
     * @param dividerPosition index of "/at" in the user input
     */
    public static void extract(String userInput, String[] eventInfo, int dividerPosition){
        eventInfo[DESCRIPTION_INDEX] = userInput.substring(START_OF_STRING, dividerPosition);
        eventInfo[DESCRIPTION_INDEX] = eventInfo[DESCRIPTION_INDEX].replace("event", "");
        eventInfo[DESCRIPTION_INDEX] = eventInfo[DESCRIPTION_INDEX].trim();
    }
}
