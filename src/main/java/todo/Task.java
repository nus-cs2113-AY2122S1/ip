package todo;


public class Task {

    private String taskDescription;
    protected Boolean status;


    protected Task(String description) {
        setTaskDescription(description);
        status = false;
        printDivider();
    }

    public void setDone(Boolean status) {
        this.status = status;
    }

    private String isDone(Boolean status) {
        if(status == true) {
            return "X";
        }
        return "";
    }

    public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public void printLine() {
        System.out.println(getTaskDescription());
    }

    public String toString() {
        return "[" + isDone(status) + "] " + getTaskDescription();
    }

    public final static void printDivider() {
        System.out.println("____________________________________________________________\n");
    }

    public String addDate(String inputLine) {
        String[] words = inputLine.split("/");
        if(inputLine.contains("/")){
            return words[1];
        }else{
            return "";
        }
        
    }
}
