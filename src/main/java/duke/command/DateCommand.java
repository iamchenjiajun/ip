package duke.command;

import duke.exception.DateTimeFormatException;
import duke.storage.Storage;
import duke.taskmanager.TaskManager;
import duke.ui.Ui;

/**
 * Represents a command that lists tasks with a given date.
 */
public class DateCommand extends Command {
    public static final int EXPECTED_ARGUMENT_LENGTH = 2;
    private final String date;

    public DateCommand(String date) {
        this.date = date;
    }

    /**
     * Lists the tasks with a given date.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     * @throws DateTimeFormatException If the command provides a date in an invalid format.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DateTimeFormatException {
        taskManager.printTasksOnDate(date);
    }
}
