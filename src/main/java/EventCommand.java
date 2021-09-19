public class EventCommand implements Command {
    public static final CommandType type = CommandType.EVENT;

    private final String eventTitle;
    private final String eventTime;

    public EventCommand(String eventTitle, String eventTime) {
        this.eventTitle = eventTitle;
        this.eventTime = eventTime;
    }

    @Override
    public void run() {
        TaskManager.addEvent(eventTitle, eventTime);
    }

    @Override
    public CommandType getType() {
        return type;
    }
}
