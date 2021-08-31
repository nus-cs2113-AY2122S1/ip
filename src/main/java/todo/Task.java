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

    // public String checkType(String inputString) {
    //     String[] words = inputString.split(" ", 2);
    //     if(words[0].equals("todo")) {
    //         type[currentIndex] = "T";
    //         return "T";
    //     }else if (words[0].equals("deadline")) {
    //         type[currentIndex] = "D";
    //         return "D";
    //     }else if (words[0].equals("event")) {
    //         type[currentIndex] = "E";
    //         return "E";
    //     }else {
    //         type[currentIndex] = "?";
    //         return "?";
    //     }
    // }

    // public void listTask() {
    //     printDivider();
    //     System.out.println("Here are the tasks in your list:");
    //     for (int i = 1; i <= currentIndex; i++) {
    //         if (isDone[i] == false) {
    //             if(deadline[i] == ""){
    //                 System.out.println(i + ". [" + type[i] + "]" + "[] " + taskDescription[i]);
    //             }else {
    //                 System.out.println(i + ". [" + type[i] + "]" + "[] " + taskDescription[i] 
    //                 + "(" + deadline[i] + ")");
    //             }
    //         } else if (isDone[i] == true) {
    //             if(deadline[i] == ""){
    //                 System.out.println(i + ". [" + type[i] + "]" + "[X] " + taskDescription[i]);
    //             }else {
    //                 System.out.println(i + ". [" + type[i] + "]" + "[X] " + taskDescription[i] 
    //                 + "(" + deadline[i] + ")");
    //             }
    //         }
    //     }
    //     printTaskRemain();
    //     printDivider();
    // }

    // public void completedTask(int index) {
    //     printDivider();
    //     doneTask++;
    //     String[] words = input.split(" ");
    //     int index = Integer.parseInt(words[1]);
    //     isDone[index] = true;
    //     System.out.println("Nice! I have marked this task as done!");
    //     if(deadline[index] == ""){
    //         System.out.println("[" + type[index] + "]" + "[X] " + taskDescription[index]);
    //     }else {
    //         System.out.println("[" + type[index] + "]" + "[X] " + taskDescription[index]
    //         + "(" + deadline[index] + ")");
    //     }
    //     printTaskRemain();
    //     printDivider();
    // }

}
