package duke;

public class Parser {
    public boolean inputIsTodo(String lc) {
        //lc: lowercase line
        return lc.startsWith("todo");
    }

    public boolean inputIsDelete(String lc) {
        return lc.startsWith("delete");
    }

    public boolean inputIsDeadline(String lc) {
        return lc.startsWith("deadline");
    }

    public boolean deadlineContainsBy(String lc) {
        return lc.contains("/by");
    }

    public boolean inputIsEvent(String lc) {
        return lc.startsWith("event");
    }

    public boolean eventContainsAt(String lc) {
        return lc.contains("/at");
    }
    public boolean inputIsDone(String lc) {
        return lc.startsWith("done");
    }

    public boolean inputIsClear(String lc) {
        return lc.startsWith("clear");
    }

    public boolean inputIsBye(String lc) {
        return lc.equals("bye");
    }

    public boolean inputIsList(String lc) {
        return lc.equals("list");
    }
}
