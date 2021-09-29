import duke.commands.*;

public class Parser {
    private static Command parse(String fullCommand){
        String[] inputArray = fullCommand.split(" ", 2);
        String inputCommand = inputArray[0];
        String inputAction = null;
        if (inputArray.length > 1){
            inputAction = inputArray[1];
        }
        switch(inputCommand){
            case "":
                break;
            case "bye":

    }
}
