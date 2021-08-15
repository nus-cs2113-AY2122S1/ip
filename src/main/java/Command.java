public class Command {
    private Action action;
    private String[] params;

    public Command(Action action, String[] params) {
        this.action = action;
        this.params = params;
    }

    public Action getAction() {
        return action;
    }

    public String[] getParams() {
        return params;
    }
}
