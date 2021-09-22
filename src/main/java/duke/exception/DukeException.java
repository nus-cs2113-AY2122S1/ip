package duke.exception;

public class DukeException extends Exception {
    private String type;

    public DukeException(String type) {
        this.type = type;
    }

    public String getMessage() {
        switch(this.type){
        case "no task selected":
            return "Patchi: You need to select a task... Œ(~ˊnˋ~)B";
        case "task doesn't exist":
            return "Patchi: That task doesn't seem to exist...... Œ(ˊnˋ)B";
        case "missing description":
            return "Patchi: You need to add a task description... Œ(ˊnˋ)B";
        case "missing timing":
            return "Patchi: You need to add a timing for this kind of task! Œ(ˊnˋ)B";
        case "missing search term":
            return "Patchi: You need to add a search term! Œ(ˊnˋ)B";
        case "invalid command":
            return "I'm sorry, I don't understand what that means... Œ(ˊnˋ)B";
        default:
            return "Invalid exception type";
        }
    }
}
