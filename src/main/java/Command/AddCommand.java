package Command;

import duke.Deadline;
import duke.Event;
import duke.Storage;
import duke.TaskList;
import duke.Todo;
import duke.Ui;
import exception.EmptyDescriptionException;
import exception.MissingInformationException;
import exception.WrongCommandException;

public class AddCommand extends Command {
    public AddCommand(String description) {
        super(description, IS_NOT_EXIT);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        addTaskWithException(tasks, ui, storage, NOT_FOR_LOADING);
    }

    public void executeForLoading(TaskList tasks, Storage storage) {
        addTaskWithException(tasks, new Ui(), storage, FOR_LOADING);
    }

    private void addTaskWithException(TaskList tasks, Ui ui, Storage storage,
                                      boolean isForLoading) {
        try {
            addTask(getDescription(), tasks, ui, storage, isForLoading);
        } catch (WrongCommandException e) {
            ui.printWrongCommandMessage();
        }

        if(!isForLoading) {
            storage.saveTasksToDiskWithException(tasks.getTasks());
        }
    }


    private void addTask(String taskInput, TaskList tasks, Ui ui, Storage storage,
                         boolean isForLoading) throws WrongCommandException {
        if (taskInput.startsWith(COMMAND_TODO)) {
            addTodoTaskWithException(taskInput, tasks, ui, storage, isForLoading);
        } else if (taskInput.startsWith(COMMAND_DEADLINE)) {
            addDeadlineTaskWithException(taskInput, tasks, ui, storage, isForLoading);
        } else if (taskInput.startsWith(COMMAND_EVENT)) {
            addEventTaskWithException(taskInput, tasks, ui, storage, isForLoading);
        } else {
            throw new WrongCommandException();
        }
    }

    private void addTodoTaskWithException(String taskInput, TaskList tasks, Ui ui,
                                          Storage storage, boolean isForLoading) {
        try {
            addTodoTask(tasks, taskInput);

            if(!isForLoading) {
                storage.saveTasksToDiskWithException(tasks.getTasks());
                ui.printAddTaskMessage(tasks);
            }
        } catch (EmptyDescriptionException e) {
            ui.printEmptyTodoDescriptionMessage();
        }
    }

    private void addTodoTask(TaskList tasks, String taskInput)
            throws EmptyDescriptionException {
        String taskName = taskInput.substring(LENGTH_TODO).trim();
        if (taskName.length() == 0) {
            throw new EmptyDescriptionException();
        }

        tasks.getTasks().add(new Todo(taskName));
    }

    private void addDeadlineTaskWithException(String taskInput, TaskList tasks, Ui ui,
                                              Storage storage, boolean isForLoading) {
        try {
            addDeadlineTask(tasks, taskInput);

            if (!isForLoading) {
                storage.saveTasksToDiskWithException(tasks.getTasks());
                ui.printAddTaskMessage(tasks);
            }

        } catch (EmptyDescriptionException e) {
            ui.printEmptyDeadlineDescriptionMessage();
        } catch (MissingInformationException e) {
            ui.printWrongDeadlineFormatMessage();
        }
    }


    private void addDeadlineTask(TaskList tasks, String taskInput)
            throws EmptyDescriptionException, MissingInformationException {
        String taskDescriptionAndDeadline =
                taskInput.substring(LENGTH_DEADLINE).trim();

        if (taskDescriptionAndDeadline.length() == 0) {
            throw new EmptyDescriptionException();
        }

        String[] taskDescriptionAndDeadlineArray =
                taskDescriptionAndDeadline.split(SEPARATOR_BY);

        if (taskDescriptionAndDeadlineArray.length == 1) {
            throw new MissingInformationException();
        }

        String taskDescription = taskDescriptionAndDeadlineArray[0];
        String deadline = taskDescriptionAndDeadlineArray[1];
        tasks.getTasks().add(new Deadline(taskDescription, deadline));
    }

    private void addEventTaskWithException(String taskInput, TaskList tasks, Ui ui,
                                           Storage storage, boolean isForLoading) {
        try {
            addEventTask(tasks, taskInput);
            if (!isForLoading) {
                storage.saveTasksToDiskWithException(tasks.getTasks());
                ui.printAddTaskMessage(tasks);
            }

        } catch (EmptyDescriptionException e) {
            ui.printEmptyEventDescriptionMessage();
        } catch (MissingInformationException e) {
            ui.printWrongEventFormatMessage();
        }
    }

    private void addEventTask(TaskList tasks, String taskInput)
            throws EmptyDescriptionException, MissingInformationException {
        String taskDescriptionAndStartTime =
                taskInput.substring(LENGTH_EVENT).trim();
        if (taskDescriptionAndStartTime.length() == 0) {
            throw new EmptyDescriptionException();
        }

        String[] taskDescriptionAndStartTimeArray =
                taskDescriptionAndStartTime.split(SEPARATOR_AT);
        if (taskDescriptionAndStartTimeArray.length == 1) {
            throw new MissingInformationException();
        }

        String taskDescription = taskDescriptionAndStartTimeArray[0];
        String startTime = taskDescriptionAndStartTimeArray[1];
        tasks.getTasks().add(new Event(taskDescription, startTime));
    }
}
