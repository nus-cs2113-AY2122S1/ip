package parser;

import commands.Command;
import commands.CommandList;

public class Parser {
    /**
     * Processes the user input into a Command Object.
     *
     * @param userInput Input provided by user.
     * @return Command object containing command, description and date (if command was deadline or event)
     */
    public Command processCommand(String userInput) {
        // Splits user input by spaces
        String[] userInputSplitted = userInput.split("\\s+", 2);
        String command = userInputSplitted[0].toUpperCase();
        String description = "";

        // Ensure description exists
        if (userInputSplitted.length > 1) {
            description = userInputSplitted[1];
        }

        // Check for Deadline and Event commands
        if (command.equals(CommandList.DEADLINE) || command.equals(CommandList.EVENT)) {
            String delimiter = (command.equals(CommandList.EVENT)) ? "/at" : "/by";
            String[] descriptionAndDate = spiltString(delimiter, description);
            return new Command(command, descriptionAndDate[0], descriptionAndDate[1]);
        }
        return new Command(command, description);
    }

    /**
     * Splits the string by the delimiter provided
     *
     * @param delimiter   Delimiter to split string by
     * @param description String to be split
     * @return Array of string of size 2 after string is spilt by the delimiter
     */
    private String[] spiltString(String delimiter, String description) {
        String[] returnValues = new String[2];
        int byIndex = description.indexOf(delimiter);
        if (byIndex == -1) {
            returnValues[0] = description;
        } else{
            String taskDescription = description.substring(0, byIndex).trim(); // Remove trailing spaces
            String date = description.substring(byIndex + delimiter.length()).trim(); // Remove leading spaces
            returnValues[0] = taskDescription;
            returnValues[1] = date;
        }
        return returnValues;
    }
}
