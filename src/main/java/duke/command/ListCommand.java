package duke.command;

import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command that prints the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints the list of tasks.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.printTasks();
    }
}
