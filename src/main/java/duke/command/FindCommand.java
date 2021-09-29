package duke.command;

import duke.data.TaskList;
import duke.ui.Ui;

/**
 * Command to find tasks by keyword from both the task list and save file.
 *  A <code>find</code> command can be called with the prefix 'find' in Duke.
 */
public class FindCommand extends Command {
    public FindCommand() {
            super(CommandPrefix.FIND);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("finding");
    }

    /**
     * Returns first case-sensitive word in user input sentence.
     * @return firstKeyword the first word in user input sentence
     */
    public String getKeyWord() {
        String sentence = Ui.readLine();
        String firstKeyword = sentence.split("",2)[0];
        return firstKeyword;
    }

    /**
     * Returns all tasks that contain keyword in task description.
     *  @param tasks TaskList to be read
     *
     */
    @Override
    public void execute(TaskList tasks) {
        Ui.printOneKeyword();
        String keyword = getKeyWord();
        Ui.printMatchingTasksAlert();
        tasks.getTaskList().stream()
            .filter(t -> t.getDescription().contains(keyword))
                .forEach(System.out::println);
        saveListAndPrintDone(tasks);
    }
}