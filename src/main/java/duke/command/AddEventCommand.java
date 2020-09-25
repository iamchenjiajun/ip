package duke.command;

import duke.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    String description;
    String at;

    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.addEvent(description, at, true);
    }
}
