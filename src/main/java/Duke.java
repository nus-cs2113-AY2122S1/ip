import java.util.Scanner;

public class Duke {

    Task[] t = new Task[100];
    int listIndex = 0;

    public void run() {
        String userInput;
        Scanner in = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while(true){
            userInput = in.nextLine();
            if(userInput.contentEquals("bye")){
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            else if(userInput.contains("done")){
                try {
                    this.doTask(userInput);
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }
            }

            else if(userInput.startsWith("todo")){
                try {
                    this.addTodo(userInput);
                }catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(userInput.startsWith("event")){
                try {
                    this.addEvent(userInput);
                }catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(userInput.startsWith("deadline")){
                try {
                    this.addDeadline(userInput);
                }catch(DukeException e){
                    System.out.println(e.getMessage());
                }
            }
            else if(userInput.startsWith("list")){
                this.printList();
            }
            else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Sorry I dont understand");
                System.out.println("____________________________________________________________");
                }
            }

    }

    public void doTask(String input) throws DukeException{
        String[] parsedInput = input.split(" ");
        if(parsedInput.length < 2){
            throw new DukeException("Sorry done ____ cannot  be empty");
        }
        if(Integer.parseInt(parsedInput[1]) >= listIndex + 1){
            throw new DukeException("That is not in the list");
        }
        int task_index = Integer.parseInt(parsedInput[1]);
        t[task_index - 1].taskDone();
        System.out.println("____________________________________________________________");
        System.out.println("List so far: ");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t[task_index - 1]);
        System.out.println("____________________________________________________________");
    }

    public void addTodo(String input)throws DukeException{

        String taskDescription = input.replaceFirst("todo", "");
        taskDescription = taskDescription.stripLeading();
        if(taskDescription.isEmpty()){
            throw new DukeException("Description cannot be empty!");
        }
        t[listIndex] = new Task(taskDescription);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t[listIndex]);
        System.out.println("Now you have " + (listIndex + 1) + " tasks in the list");
        System.out.println("____________________________________________________________");
        listIndex++;
    }

    public void addEvent(String input) throws DukeException{
        input = input.replaceFirst("event", "");
        String[] parsedInput = input.split("/at");

        try {
            parsedInput[1].isEmpty();

        }catch(ArrayIndexOutOfBoundsException a){
            System.out.println("Description cannot be empty!");
            return;
        }

        if(parsedInput[1].stripLeading().isEmpty()){
            throw new DukeException("Description cannot be empty!");
        }
        t[listIndex] = new Event(parsedInput[0], parsedInput[1].stripLeading());
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t[listIndex]);
        System.out.println("Now you have " + (listIndex + 1) + " tasks in the list");
        System.out.println("____________________________________________________________");
        listIndex++;
    }

    public void addDeadline(String input) throws DukeException{
        input = input.replaceFirst("deadline", "");
        String[] parsedInput = input.split("/by");
        try {
            parsedInput[1].isEmpty();
        }catch(ArrayIndexOutOfBoundsException a){
            System.out.println("Description cannot be empty!");
            return;
        }
        if(parsedInput[1].stripLeading().isEmpty()){
            throw new DukeException("Description cannot be empty!");
        }
        t[listIndex] = new Deadline(parsedInput[0], parsedInput[1].stripLeading());
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println(t[listIndex]);
        System.out.println("Now you have " + (listIndex + 1) + " tasks in the list");
        System.out.println("____________________________________________________________");
        listIndex++;
    }

    public void printList(){
        System.out.println("____________________________________________________________");
        System.out.println("List so far: ");
        for(int i = 0; i < listIndex; i++) {
            System.out.print(i +  1);
            System.out.println(t[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
       Duke d = new Duke();
       d.run();
    }
}
