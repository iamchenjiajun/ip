package duke.command;

import duke.storage.Storage;
import duke.taskmanager.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command for printing tasks containing a search string in the list of tasks.
 */
public class FindCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Prints a list of tasks containing the search string in their description.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.findTask(searchString);
    }
}
