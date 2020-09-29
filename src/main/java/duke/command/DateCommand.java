package duke.command;

import duke.exception.DateTimeFormatException;
import duke.storage.Storage;
import duke.taskmanager.TaskManager;
import duke.ui.Ui;

public class DateCommand extends Command {
    public static final int EXPECTED_ARGUMENT_LENGTH = 2;
    private final String date;

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DateTimeFormatException {
        taskManager.printTasksOnDate(date);
    }
}
