package task;

public abstract class Task {
    private boolean isDone;
    private String description;
    private final Types type;

    public enum Types {
        DEADLINE, EVENT, TODO;

        public static String getTypesRegex(){
            String regex =  "(?i:";
            for (Types type : Types.values()){
                regex +=type.toString() + ".*|";
            }
            return regex.substring(0, regex.length()-1) + ')';
        }

        public char getChar() {
            return this.toString().charAt(0);
        }
    }

    protected Task(Types type) {
        this.description = null;
        this.isDone = false;
        this.type = type;
    }

    protected Task(String description, Types type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    private char getIsDoneChar() {
        return isDone ? 'X' : ' ';
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", type.getChar(), getIsDoneChar(), description);
    }
}
