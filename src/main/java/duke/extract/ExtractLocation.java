package duke.extract;

public class ExtractLocation extends Extract {
    private static final int LOCATION_INDEX = 1;

    public static void extract(String userInput, String[] eventInfo, int dividerPosition){
        int charAfterDividerPosition = dividerPosition + 1;
        eventInfo[LOCATION_INDEX] = userInput.substring(charAfterDividerPosition);
        eventInfo[LOCATION_INDEX] = eventInfo[LOCATION_INDEX].replace("at", "");
        eventInfo[LOCATION_INDEX] = eventInfo[LOCATION_INDEX].trim();
    }
}
