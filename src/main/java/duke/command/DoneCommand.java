package duke.command;

import duke.exception.InvalidIndexException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command that marks a task as done.
 */
public class DoneCommand extends Command {
    public static final int EXPECTED_ARGUMENT_LENGTH = 2;
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks a task as done from the list at the given index.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     * @throws InvalidIndexException If the index of the task is out of range.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidIndexException {
        taskManager.markAsDone(index);
        saveTasks(taskManager, ui, storage);
    }
}
