package duke.command;

import duke.exception.InvalidIndexException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidIndexException {
        taskManager.deleteTask(index);
    }
}
