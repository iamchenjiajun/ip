package duke.command;

import duke.exception.InvalidArgumentException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidArgumentException {
        taskManager.markAsDone(index);
    }
}
