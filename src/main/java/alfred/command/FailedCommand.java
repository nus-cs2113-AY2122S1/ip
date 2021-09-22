package alfred.command;

import alfred.parser.FailedCommandType;
import alfred.ui.TextUi;

public class FailedCommand extends Command {
    private FailedCommandType type;

    public FailedCommand(FailedCommandType type) {
        this.type = type;
    }

    public void execute() {
        switch (type) {
        case GENERAL:
            TextUi.invalidCommandMessage();
            break;
        case NUMBER_FORMAT:
            TextUi.invalidIndexMessage();
            break;
        case MISSING_DATE:
            TextUi.invalidDateMessage();
            break;
        case EMPTY_DESCRIPTION:
            TextUi.emptyDescriptionMessage();
            break;
        case NO_INDEX_SPECIFIED:
            TextUi.missingIndexMessage();
            break;
        default:
            return;
        }
    }
}
