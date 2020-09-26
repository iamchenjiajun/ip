package duke.command;

import duke.exception.InvalidIndexException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public static final int EXPECTED_ARGUMENT_LENGTH = 2;
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidIndexException {
        taskManager.markAsDone(index);
    }
}
