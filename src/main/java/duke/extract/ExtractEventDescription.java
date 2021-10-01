package duke.extract;

public class ExtractEventDescription extends Extract {
    public static void extract(String userInput, String[] eventInfo, int dividerPosition){
        eventInfo[DESCRIPTION_INDEX] = userInput.substring(START_OF_STRING, dividerPosition);
        eventInfo[DESCRIPTION_INDEX] = eventInfo[DESCRIPTION_INDEX].replace("event", "");
        eventInfo[DESCRIPTION_INDEX] = eventInfo[DESCRIPTION_INDEX].trim();
    }
}
