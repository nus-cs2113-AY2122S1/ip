package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class FindCommand extends Command{
    public FindCommand() {
        super(CommandPrefix.find);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("adding");
    }

    public String getKeyWord() {
        String sentence = Ui.readCommand();
        String firstKeyword = sentence.split("",2)[0];
        return firstKeyword;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Please enter one, and only one keyword: ");
        String keyword = getKeyWord();
        System.out.println("Here are the matching tasks in your list: ");
        tasks.getTaskList().stream()
            .filter(t -> t.getDescription().contains(keyword))
                .forEach(System.out::println);
        saveListAndPrintDone(tasks);
    }
}