package duke.command;

import duke.exception.InvalidIndexException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command with parameters corresponding to user input.
 */
public abstract class Command {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    /**
     * Executes the command.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     * @throws InvalidIndexException If the command tries to access an invalid index in the list of tasks.
     */
    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidIndexException;

    /**
     * Returns true if the {@code Command} is a {@code ByeCommand}.
     *
     * @return Boolean value showing whether the {@code Command} is the {@code ByeCommand}.
     */
    public boolean isBye() {
        return false;
    }
}
