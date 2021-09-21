package duke.exception;

/**
 * An Exception that will be thrown if a data entry read from DukeData.txt has less than 3 piece of essential info.
 * The 3 essential information are task type, done status and description.
 */
public class DukeMissingDataException extends Exception {
}
