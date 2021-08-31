package shikabot.command;

import shikabot.ui.TextUi;

import static shikabot.parser.Parser.INVALID_DEADLINE_SYNTAX;
import static shikabot.parser.Parser.INVALID_EVENT_SYNTAX;
import static shikabot.parser.Parser.INVALID_TASK;
import static shikabot.parser.Parser.NEGATIVE_INDEX_ERROR;
import static shikabot.parser.Parser.NUMBER_FORMAT_ERROR;

public class FailedCommand extends Command {

    private final TextUi ui;
    private final int errorCode;

    public FailedCommand(int errorCode) {
        this.ui = new TextUi();
        this.errorCode = errorCode;
    }

    public void execute() {
        switch (errorCode) {
        case (INVALID_DEADLINE_SYNTAX):
            ui.printDeadlineSyntaxMessage();
            break;
        case (INVALID_EVENT_SYNTAX):
            ui.printEventSyntaxMessage();
            break;
        case (NUMBER_FORMAT_ERROR):
            ui.printNumberFormatMessage();
            break;
        case (NEGATIVE_INDEX_ERROR):
            ui.printNegativeIndexMessage();
            break;
        case (INVALID_TASK):
            ui.printInvalidTaskMessage();
            break;
        default:
            break;
        }
    }
}
