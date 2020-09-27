package duke.command;

import duke.exception.InvalidIndexException;
import duke.storage.Storage;
import duke.taskmanager.TaskManager;
import duke.ui.Ui;

public class FindCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidIndexException {
        taskManager.findTask(searchString);
    }
}
