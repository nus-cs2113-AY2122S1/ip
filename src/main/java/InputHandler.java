public class InputHandler {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_VIEW_LIST = "list";
    private static final String COMMAND_COMPLETE_TASK = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String EVENT_TIME = "at";
    private static final String DEADLINE_DATE = "by";
    private static final String COMMAND_HELP = "!";

    private String description;
    private final ListManager listManager;

    public InputHandler(String description, ListManager listManager){
        this.description = description;
        this.listManager = listManager;
    }

    private String taskCategory(String userInput){
        if(userInput.startsWith(COMMAND_EXIT)){
            return COMMAND_EXIT;
        }
        if(userInput.startsWith(COMMAND_VIEW_LIST)) {
            return COMMAND_VIEW_LIST;
        }
        if(userInput.startsWith(COMMAND_ADD_DEADLINE)){
            return COMMAND_ADD_DEADLINE;
        }
        if (userInput.startsWith(COMMAND_ADD_TODO)) {
            return COMMAND_ADD_TODO;
        }
        if(userInput.startsWith(COMMAND_ADD_EVENT)){
            return COMMAND_ADD_EVENT;
        }
        if (userInput.startsWith(COMMAND_COMPLETE_TASK)){
            return COMMAND_COMPLETE_TASK;
        }
        return null;
    }

    private void handleEvent(String eventInput){
        String taskDescription = eventInput.replaceFirst(COMMAND_ADD_EVENT,"").trim();
        int startOfEventTime = taskDescription.indexOf(EVENT_TIME);
        String eventDescription = taskDescription.substring(0,startOfEventTime).trim();
        String eventTime = taskDescription.replaceFirst(eventDescription,"").replaceFirst(EVENT_TIME,"").strip();
        listManager.addEvent(eventDescription,eventTime);
    }

    private void handleDeadline(String deadlineInput){
        String taskDescription = deadlineInput.replaceFirst(COMMAND_ADD_DEADLINE,"").trim();
        int startOfDeadlineDate = taskDescription.indexOf(DEADLINE_DATE);
        String deadlineDescription = taskDescription.substring(0,startOfDeadlineDate).trim();
        String deadlineDate = taskDescription.replaceFirst(deadlineDescription,"").replaceFirst(DEADLINE_DATE,"").strip();
        listManager.addDeadline(deadlineDescription,deadlineDate);
    }

    private void handleToDo(String toDoInput){
        String taskDescription = toDoInput.replaceFirst(COMMAND_ADD_TODO,"").trim();
        listManager.addTodo(taskDescription);
    }

    private void handleDone(String userInput){
        String taskDone = userInput.replaceFirst(COMMAND_COMPLETE_TASK,"").trim();
        String[] taskDoneArray = taskDone.split(",");
        for (String s: taskDoneArray) {
            int taskDoneIndex = Integer.parseInt(s);
            listManager.completeTask(taskDoneIndex - 1);
        }
    }

    public void handleInput(){
        boolean isAddingTask = false;
        description = description.replaceAll("/","");
        if(description.contains(COMMAND_ADD_DEADLINE)){
            isAddingTask = true;
            description = description.replaceAll(COMMAND_ADD_DEADLINE,"/"+ COMMAND_ADD_DEADLINE);
        }
        if(description.contains(COMMAND_ADD_TODO)){
            isAddingTask = true;
            description = description.replaceAll(COMMAND_ADD_TODO,"/"+ COMMAND_ADD_TODO);
        }
        if(description.contains(COMMAND_ADD_EVENT)){
            isAddingTask = true;
            description = description.replaceAll(COMMAND_ADD_EVENT,"/"+ COMMAND_ADD_EVENT);
        }
        if(isAddingTask) {
            String[] commandList = description.split("/");
            for (int i = 1; i < commandList.length; i++) {
                handleCommand(commandList[i]);
            }
        }else{
            handleCommand(description);
        }
    }

    private void handleCommand(String userInput){
        String inputCommand = taskCategory(userInput);
        switch(inputCommand){
        case COMMAND_EXIT:
            Duke.isOnline = false;
            break;
        case COMMAND_VIEW_LIST:
            listManager.printList();
            break;
        case COMMAND_COMPLETE_TASK:
            handleDone(userInput);
            listManager.printList();
            System.out.println(Logo.divider);
            break;
        case COMMAND_ADD_EVENT:
            handleEvent(userInput);
            break;
        case COMMAND_ADD_TODO:
            handleToDo(userInput);
            break;
        case COMMAND_ADD_DEADLINE:
            handleDeadline(userInput);
            break;
        }
    }
}

