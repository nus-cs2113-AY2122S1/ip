public class InvalidInputsException {

    // Invalid date formatting for deadline and event tasks error
    public static class InvalidDateFormatting extends DukeException {
        public InvalidDateFormatting(String msg) {
            super(msg);
        }
    }

    // Invalid done task number error
    public static class InvalidTaskNumber extends DukeException {
        public InvalidTaskNumber(String msg) {
            super(msg);
        }
    }

    // Missing keyword error
    public static class MissingKeyword extends DukeException {
        public MissingKeyword(String msg) {
            super(msg);
        }
    }
}
