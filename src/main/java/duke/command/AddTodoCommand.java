package duke.command;

import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command for adding a {@code Todo}.
 */
public class AddTodoCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a {@code Todo} to the list of tasks.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.addTodo(description);
        saveTasks(taskManager, ui, storage);
    }
}
