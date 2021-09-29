package duke.exception;

/**
 * An Exception that will be thrown if data entries being loaded from DukeData.txt contain foreign content.
 * For example, a task that is tagged as Q does not exist as the 3 possible tags are T, D and E.
 */
public class DukeCorruptedDataException extends Exception{
}
