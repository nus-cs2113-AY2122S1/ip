package duke.task;

import duke.Message;

public class Task {
    private boolean isDone;
    private String description;
    final Type type;

    private class

    private static final String MARK_AS_DONE_STRING = "Nice! I've marked this task as done:\n";

    public enum Type {
        DEADLINE(3, "by"),
        EVENT(3, "at"),
        TODO(2, "");

        public final int NUM_ARGS;
        public final String PREPOSITION;

        Type(int numArgs, String preposition){
            NUM_ARGS = numArgs;
            PREPOSITION = preposition;
        }

        public static String getTypesRegex() {
            String regex = "(?i:";
            for (Type type : Type.values()) {
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

        static Type getType(char firstLetter){
            for(Type type : values()){
                if(firstLetter == type.getChar()){
                    return type;
                }
            }
            return null;
        }
    }

    Task(String description, Type type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    Task(boolean isDone, String description, Type type) {
        this.isDone = isDone;
        this.description = description;
        this.type = type;
    }

    String getDescription() {
        return description;
    }

    private char getIsDoneChar() {
        return isDone ? 'X' : ' ';
    }

    void markAsDone() {
        isDone = true;
        Message.printWithSpacers(MARK_AS_DONE_STRING + this);
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", type.getChar(), getIsDoneChar(), description);
    }

    String getFormattedString() {
        return String.format("%c|%d|%s", type.getChar(), isDone ? 1 : 0, description);
    }
}
