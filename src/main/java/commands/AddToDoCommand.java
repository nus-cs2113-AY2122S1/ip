package commands;

import exceptions.EmptyParamsException;
import storage.Storage;
import tasks.ToDo;
import ui.TextUi;

import java.io.IOException;

public class AddToDoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_DESC = "Adds a task of \"TODO\" type to the list";

    private final String toDoDescription;

    public AddToDoCommand(String toDoDescription) {
        this.toDoDescription = toDoDescription;
    }

    @Override
    public void execute() {
        try {
            checkParam(toDoDescription);
            ToDo newToDo = new ToDo(toDoDescription);
            taskList.addTask(newToDo);
            TextUi.showTaskAddedMessage(newToDo, taskList.getSize());
            Storage.appendToDataFile("T / 0 / " + toDoDescription);
        } catch (IOException e){
            TextUi.showIOExceptionMessage(e);
        } catch(EmptyParamsException e) {
            TextUi.showEmptyParamMessage(COMMAND_WORD);
        }
    }

    public static void checkParam(String taskParam) throws EmptyParamsException{
        if(taskParam.equals("")) {
            throw new EmptyParamsException();
        }
    }
}
