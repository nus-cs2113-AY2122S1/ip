public class InvalidTaskIdException extends Exception{

    int invalidTaskId;

    InvalidTaskIdException(int invalidTaskId) {
        this.invalidTaskId = invalidTaskId;
    }

    @Override
    public String toString() {
        return Integer.toString(invalidTaskId);
    }
}
