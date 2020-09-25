package duke.command;

import duke.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        try {
            ui.showByeMessage();
            storage.saveTasks(taskManager.getTasks());
        } catch (IOException e) {
            ui.showSaveError();
        }
    }

    @Override
    public boolean isBye() {
        return true;
    }
}
