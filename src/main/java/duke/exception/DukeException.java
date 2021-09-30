package duke.exception;

public class DukeException extends Exception {
    private String type;

    public DukeException(String type) {
        this.type = type;
    }

    /**
     * Finds and returns the error message according to the exception type.
     *
     * @return Returns the error message.
     */
    public String getMessage() {
        switch(this.type){
        case "no task selected":
            return "Patchi: You need to select a task... (~ono~)B";
        case "task doesn't exist":
            return "Patchi: That task doesn't seem to exist...... (ono)B";
        case "missing description":
            return "Patchi: You need to add a task description... (ono)B";
        case "missing timing":
            return "Patchi: You need to add a timing for this kind of task! (ono)B";
        case "missing search term":
            return "Patchi: You need to add a search term! (ono)B";
        case "invalid command":
            return "Patchi: I'm sorry, I don't understand what that means... (ono)B";
        case "invalid timing":
            return "Patchi: Please enter your timing in the format: yyyy-mm-dd! (ono)B";
        default:
            return "Invalid exception type";
        }
    }
}
