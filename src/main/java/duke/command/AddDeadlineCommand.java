package duke.command;

import duke.exception.DateTimeFormatException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    String description;
    String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DateTimeFormatException {
        taskManager.addDeadline(description, by);
    }
}
