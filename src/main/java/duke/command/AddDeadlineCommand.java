package duke.command;

import duke.exception.DateTimeFormatException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command for adding a {@code Deadline}.
 */
public class AddDeadlineCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    String description;
    String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a {@code Deadline} to the list of tasks.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DateTimeFormatException {
        taskManager.addDeadline(description, by);
    }
}
