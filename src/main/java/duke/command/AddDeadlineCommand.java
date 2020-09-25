package duke.command;

import duke.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {
    String description;
    String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.addDeadline(description, by, true);
    }
}
