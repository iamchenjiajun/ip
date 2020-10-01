package duke.command;

import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command that terminates the program.
 */
public class ByeCommand extends Command {
    /**
     * Saves the list of tasks to the file.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        ui.showByeMessage();
        saveTasks(taskManager, ui, storage);
    }

    /**
     * Returns true to indicate that this represents an instance of {@code ByeCommand}.
     *
     * @return Boolean indicating that this is an instance of {@code ByeCommand}.
     */
    @Override
    public boolean isBye() {
        return true;
    }
}
