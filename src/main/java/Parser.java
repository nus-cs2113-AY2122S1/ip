import exceptions.DukeException;
import exceptions.EmptyTimeException;

public class Parser {

    public static String parseExtractCommand(String userInput) {
        String[] params = userInput.split(" ", 2);
        return params[0];
    }

    public static int parseExtractIndex(String userInput) throws DukeException, IndexOutOfBoundsException{
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        String position = params[1];
        int index = Integer.parseInt(position) - 1;
        if (index < 0 || index >= TaskManager.tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        return index;
    }

    public static String parseExtractString(String userInput) throws DukeException {
        String[] params = userInput.split(" ", 2);
        if (params.length < 2) {
            throw new DukeException();
        }
        return params[1];
    }

    public static String[] parseExtractInfoAndTime(String userInput) throws EmptyTimeException {
        String[] separateTime = userInput.split("/", 2);
        if (separateTime.length<2) {
            throw new EmptyTimeException();
        }
        String description = separateTime[0];
        String[]separateAtBy = separateTime[1].split(" ", 2);
        if (separateAtBy.length<2) {
            throw new EmptyTimeException();
        }
        String time = separateAtBy[1];
        return new String[]{description, time};
    }

}
