package parser;

import java.util.*;

public class Command {
    private Action action;
    private HashMap<String, String> params;

    public Command(Action action, HashMap<String, String> params) {
        this.action = action;
        this.params = params;
    }

    public Action getAction() {
        return action;
    }

    public HashMap<String, String> getParams() {
        return params;
    }
    public String getParam(String key) {
        return params.get(key);
    }
}
