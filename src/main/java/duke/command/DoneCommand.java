package duke.command;

import duke.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.markAsDone(index, true);
    }
}
