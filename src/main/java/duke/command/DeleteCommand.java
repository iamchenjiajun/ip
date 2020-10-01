package duke.command;

import duke.exception.InvalidIndexException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command that deletes a task from the list.
 */
public class DeleteCommand extends Command {
    public static final int EXPECTED_ARGUMENT_LENGTH = 2;
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the list at the given index.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     * @throws InvalidIndexException If the index of the task is out of range.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidIndexException {
        taskManager.deleteTask(index);
        saveTasks(taskManager, ui, storage);
    }
}
