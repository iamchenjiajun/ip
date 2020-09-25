package duke.command;

import duke.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.printTasks();
    }
}
