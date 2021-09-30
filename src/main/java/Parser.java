public class Parser {
    public static final int BYE = 0;
    public static final int LIST = 1;
    public static final int DONE = 2;
    public static final int DELETE = 3;
    public static final int TODO = 4;
    public static final int EVENT = 5;
    public static final int DEADLINE = 6;
    public static final int FIND = 7;
    public static final int NOT_VALID = -1;

    private String userInput;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    public int command() {
        if(userInput.equals("bye")) {
            return BYE;
        }
        if(userInput.equals("list")) {
            return LIST;
        }
        if(userInput.startsWith("done")) {
            return DONE;
        }
        if(userInput.startsWith("delete")) {
            return DELETE;
        }
        if(userInput.startsWith("todo")) {
            return TODO;
        }
        if(userInput.startsWith("event")) {
            return EVENT;
        }
        if(userInput.startsWith("deadline")) {
            return DEADLINE;
        }
        if(userInput.startsWith("find")) {
            return FIND;
        }
        return NOT_VALID;
    }

    public int doneTaskIndex() {
        String n = userInput.substring(5);
        int doneIndex = Integer.parseInt(n) - 1;
        return doneIndex;
    }

    public int deleteTaskIndex() {
        String n = userInput.substring(7);
        int delIndex = Integer.parseInt(n) - 1;
        return delIndex;
    }

    public Todo getTodo() throws IllegalTaskException {
        Todo newTask = new Todo(userInput.substring(5));
        return newTask;
    }

    public Event getEvent() throws InvalidEventFormat {
        int startOfDate = userInput.indexOf('/');
        if(!userInput.contains("/at")) {
            throw new InvalidEventFormat();
        }
        String task = userInput.substring(6, startOfDate - 1);
        String date = userInput.substring(startOfDate + 4);
        Event newTask = new Event(task, date);
        return newTask;
    }

    public Deadline getDeadline() throws InvalidDeadlineFormat {
        int startOfDate = userInput.indexOf('/');
        if(!userInput.contains("/by")) {
            throw new InvalidDeadlineFormat();
        }
        String task = userInput.substring(9, startOfDate - 1);
        String date = userInput.substring(startOfDate + 4);
        Deadline newTask = new Deadline(task, date);
        return newTask;
    }

    public String getTask() {
        String newTask = userInput.substring(5);
        return newTask;
    }

}
