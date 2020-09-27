package duke.command;

import duke.exception.DateTimeFormatException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddEventCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    String description;
    String at;

    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DateTimeFormatException {
        taskManager.addEvent(description, at);
    }
}
