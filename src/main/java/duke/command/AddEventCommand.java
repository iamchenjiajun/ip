package duke.command;

import duke.exception.DateTimeFormatException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command for adding an {@code Event}.
 */
public class AddEventCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    String description;
    String at;

    public AddEventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    /**
     * Adds an {@code Event} to the list of tasks.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     * @throws DateTimeFormatException If the command provides a date in an invalid format.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DateTimeFormatException {
        taskManager.addEvent(description, at);
    }
}
