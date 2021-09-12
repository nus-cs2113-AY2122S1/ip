package duke.task;

import duke.Message;

public class Task {
    private boolean isDone;
    private String description;
    private final Types type;

    public enum Types {
        DEADLINE, EVENT, TODO;

        public static String getTypesRegex() {
            String regex = "(?i:";
            for (Types type : Types.values()) {
                regex += type.toString() + ".*|";
            }
            return regex.substring(0, regex.length() - 1) + ')';
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }

        private char getChar() {
            return super.toString().charAt(0);
        }
    }

    Task(Types type) {
        this.description = null;
        this.isDone = false;
        this.type = type;
    }

    Task(String description, Types type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    void setDescription(String description) {
        this.description = description;
    }

    private char getIsDoneChar() {
        return isDone ? 'X' : ' ';
    }

    void markAsDone() {
        isDone = true;
        Message.printWithSpacers("Nice! I've marked this task as done:\n" + this);
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", type.getChar(), getIsDoneChar(), description);
    }
}
