package shikabot.command;

import shikabot.ui.TextUi;

import static shikabot.parser.Parser.EMPTY_FIELD;
import static shikabot.parser.Parser.INVALID_DATE_SYNTAX;
import static shikabot.parser.Parser.INVALID_DEADLINE_SYNTAX;
import static shikabot.parser.Parser.INVALID_EVENT_SYNTAX;
import static shikabot.parser.Parser.INVALID_TASK;
import static shikabot.parser.Parser.NEGATIVE_INDEX_ERROR;
import static shikabot.parser.Parser.NUMBER_FORMAT_ERROR;

public class FailedCommand extends Command {

    private final int errorCode;

    public FailedCommand(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Function that prints an error message based on the type of failed command it is.
     */
    public void execute() {
        switch (errorCode) {
        case (INVALID_DEADLINE_SYNTAX):
            TextUi.printDeadlineSyntaxMessage();
            break;
        case (INVALID_EVENT_SYNTAX):
            TextUi.printEventSyntaxMessage();
            break;
        case (NUMBER_FORMAT_ERROR):
            TextUi.printNumberFormatMessage();
            break;
        case (NEGATIVE_INDEX_ERROR):
            TextUi.printNegativeIndexMessage();
            break;
        case (INVALID_TASK):
            TextUi.printInvalidTaskMessage();
            break;
        case (INVALID_DATE_SYNTAX):
            TextUi.printInvalidDateMessage();
            break;
        case (EMPTY_FIELD):
            TextUi.printEmptyFieldMessage();
            break;
        default:
            break;
        }
    }
}
