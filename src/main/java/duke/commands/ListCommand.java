package duke.commands;

import static duke.ui.Strings.FORMAT_DATE_OUT;
import static duke.ui.Strings.MESSAGE_LIST;
import static duke.ui.Strings.MESSAGE_LIST_DATE;
import static duke.ui.Strings.MESSAGE_LIST_DATE_EMPTY;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.TextUi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " [/on <date>]";
    public static final String MESSAGE_HELP = "Prints an indexed list of all added tasks";

    private final LocalDate date;

    public ListCommand() {
        date = null;
    }

    public ListCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {

        ArrayList<Task> tasks = taskList.getTaskList();

        if (date == null) {
            listAll(tasks, ui);
        } else {
            listDate(tasks, ui, date);
        }
    }

    /**
     * Lists all tasks saved in the tasklist
     *
     * @param tasks tasklist to be listed
     * @param ui    ui to print to
     */
    private void listAll(ArrayList<Task> tasks, TextUi ui) {
        ui.printMessage(MESSAGE_LIST);

        int index = 1;

        for (Task task : tasks) {
            ui.printMessage(String.format("%d.%s", index, task.toFormattedString()));
            index++;
        }
    }

    /**
     * Lists all tasks saved in the tasklist the occur on the date provided via the /on argument
     *
     * @param tasks tasklist to be listed
     * @param ui    ui to print to
     */
    private void listDate(ArrayList<Task> tasks, TextUi ui, LocalDate date) {
        ArrayList<String> todo = new ArrayList<>();

        int index = 1;

        for (Task task : tasks) {
            if (date.equals(task.getDeadlineDate())) {
                todo.add(String.format("%d.%s", index, task.toFormattedString()));
                index++;
            }
        }

        String formattedDate = date.format(DateTimeFormatter.ofPattern(FORMAT_DATE_OUT));

        if (todo.isEmpty()) {
            ui.printMessage(String.format(MESSAGE_LIST_DATE_EMPTY, formattedDate));
        } else {
            ui.printMessage(String.format(MESSAGE_LIST_DATE, formattedDate));
            String[] toPrint = todo.toArray(String[]::new);
            ui.printMessage(toPrint);
        }
    }
}
