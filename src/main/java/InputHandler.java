import java.util.Objects;

public class InputHandler implements InputInterface{

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_VIEW_LIST = "list";
    private static final String COMMAND_ECHO = "echo";
    private static final String COMMAND_COMPLETE_TASK = "done";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_EVENT = "event";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String EVENT_TIME = "at";
    private static final String DEADLINE_DATE = "by";

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
        if(userInput.startsWith(COMMAND_ADD_TODO)) {
            return COMMAND_ADD_TODO;
        }
        if(userInput.startsWith(COMMAND_ADD_EVENT)){
            return COMMAND_ADD_EVENT;
        }
        if(userInput.startsWith(COMMAND_COMPLETE_TASK)){
            return COMMAND_COMPLETE_TASK;
        }
        if (userInput.startsWith(COMMAND_ECHO)){
            return COMMAND_ECHO;
        }
        return null;
    }

    private void handleEvent(String eventInput) throws CommandException{
        String taskDescription = eventInput.replaceFirst(COMMAND_ADD_EVENT,"").trim();
        if(!taskDescription.contains(EVENT_TIME)){
            throw new CommandException(ErrorList.ERROR_EMPTY_EVENT_TIME);
        }
        int startOfEventTime = taskDescription.indexOf(EVENT_TIME);
        String eventDescription = taskDescription.substring(0,startOfEventTime).trim();
        if(eventDescription.isEmpty()){
            throw new CommandException(ErrorList.ERROR_EMPTY_EVENT_INPUT);
        }
        String eventTime = taskDescription.replaceFirst(eventDescription,"").replaceFirst(EVENT_TIME,"").strip();
        listManager.addEvent(eventDescription,eventTime);
    }

    private void handleDeadline(String deadlineInput) throws CommandException{
        String taskDescription = deadlineInput.replaceFirst(COMMAND_ADD_DEADLINE,"").trim();
        if(!taskDescription.contains(DEADLINE_DATE)){
            throw new CommandException(ErrorList.ERROR_EMPTY_DEADLINE_TIME);
        }
        int startOfDeadlineDate = taskDescription.indexOf(DEADLINE_DATE);
        String deadlineDescription = taskDescription.substring(0,startOfDeadlineDate).trim();
        if(deadlineDescription.isEmpty()){
            throw new CommandException(ErrorList.ERROR_EMPTY_DEADLINE_INPUT);
        }
        String deadlineDate = taskDescription.replaceFirst(deadlineDescription,"").replaceFirst(DEADLINE_DATE,"").strip();
        listManager.addDeadline(deadlineDescription,deadlineDate);
    }

    private void handleToDo(String toDoInput) throws CommandException{
        String removeCommand = toDoInput.replaceFirst(COMMAND_ADD_TODO,"").trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorList.ERROR_EMPTY_TODO_INPUT);
        }
        listManager.addTodo(removeCommand);
    }

    private void handleDone(String userInput){
        String removeCommand = userInput.replaceFirst(COMMAND_COMPLETE_TASK,"").trim();
        String[] taskDoneArray = removeCommand.split(",");
        for (String s: taskDoneArray) {
            int taskDoneIndex = Integer.parseInt(s);
            try {
                listManager.completeTask(taskDoneIndex - 1);
            }catch (CommandException e){
                e.handleException();
            }
        }
    }

    private void handleEcho(String userInput) throws CommandException{
        String removeCommand = userInput.replaceFirst(COMMAND_ECHO,"").trim();
        if(removeCommand.isEmpty()){
            throw new CommandException(ErrorList.ERROR_EMPTY_ECHO_INPUT);
        }
        System.out.println(removeCommand);
    }

    private void handleList(String userInput) throws CommandException{
        if(listManager.getListSize() == 0){
            throw new CommandException(ErrorList.ERROR_EMPTY_LIST);
        }
        String removeCommand = userInput.replaceFirst(COMMAND_VIEW_LIST,"").trim();
        if(removeCommand.contains(COMMAND_ADD_TODO)){
            try {
                listManager.printToDo();
            }catch (CommandException e){
                e.handleException();
            }
        }else if(removeCommand.contains(COMMAND_ADD_EVENT)) {
            try {
                listManager.printEvent();
            }catch (CommandException e){
                e.handleException();
            }
        }else if(removeCommand.contains(COMMAND_ADD_DEADLINE)){
            try {
                listManager.printDeadline();
            }catch (CommandException e){
                e.handleException();
            }
        }else {
            listManager.printList();
        }
    }

    public void handleInput() throws CommandException{
        if(description.isEmpty()){
            throw new CommandException(ErrorList.ERROR_NULL);
        }
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
        if(description.startsWith(COMMAND_VIEW_LIST)){
            isAddingTask = false;
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
        try {
            switch (Objects.requireNonNull(inputCommand)) {
            case COMMAND_EXIT:
                Duke.isOnline = false;
                break;
            case COMMAND_VIEW_LIST:
                try {
                    handleList(userInput);
                }catch (CommandException e){
                    e.handleException();
                }
                break;
            case COMMAND_COMPLETE_TASK:
                handleDone(userInput);
                listManager.printList();
                System.out.println(Logo.divider);
                break;
            case COMMAND_ADD_EVENT:
                try {
                    handleEvent(userInput);
                }catch (CommandException e){
                    e.handleException();
                }
                break;
            case COMMAND_ADD_TODO:
                try {
                    handleToDo(userInput);
                }catch(CommandException e){
                    e.handleException();
                }
                break;
            case COMMAND_ADD_DEADLINE:
                try {
                    handleDeadline(userInput);
                }catch (CommandException e){
                    e.handleException();
                }
                break;
            case COMMAND_ECHO:
                try {
                    handleEcho(userInput);
                }catch (CommandException e){
                    e.handleException();
                }
                break;
            }
        }catch(NullPointerException e){
            try {
                System.out.println(ErrorList.ERROR_UNKNOWN_COMMAND);
                handleEcho(userInput);
            }catch (CommandException commandException){
                commandException.handleException();
            }
        }
    }
}

