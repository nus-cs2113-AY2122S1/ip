public class TaskEmptyException extends ValidCommandException{
    private String taskType;

    TaskEmptyException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
             return "     ☹ OOPS!!! The description of a " + this.taskType + " cannot be empty";
    }


}
