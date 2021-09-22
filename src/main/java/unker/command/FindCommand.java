package unker.command;

import unker.task.Task;
import unker.task.Unker;
import unker.ui.UI;

public class FindCommand extends Command {

    protected FindCommand() {
        super("find", "find <keyword>");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException {
        if (data == null || data.isBlank()) {
            throw new InvalidCommandException("Unker need something to search with!", this);
        }
        String output = unker.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(data.toLowerCase()))
                .map(task -> "- " + task.toString()) // Prepend bullet point for aesthetics 
                .reduce((a, b) -> a + "\n" + b) // Concatenate all the strings together
                .orElse("Nothing leh... You got add anything?"); // Either return the list or this string
        ui.printSection("Here is what Unker found for you:", output);
    }
}
