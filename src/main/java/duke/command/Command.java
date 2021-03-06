package duke.command;

import duke.exception.DateTimeFormatException;
import duke.exception.InvalidIndexException;
import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

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
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_DATE = "date";

    /**
     * Executes the command.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     * @throws InvalidIndexException If the command tries to access an invalid index in the list of tasks.
     * @throws DateTimeFormatException If the command provides a date in an invalid format.
     */
    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage) throws InvalidIndexException,
            DateTimeFormatException;

    /**
     * Saves the tasks to file.
     *
     * @param taskManager Object managing the list of tasks.
     * @param ui Object representing the user interface.
     * @param storage A file storing the tasks.
     */
    public void saveTasks(TaskManager taskManager, Ui ui, Storage storage) {
        try {
            storage.saveTasks(taskManager.getTasks());
        } catch (IOException e) {
            ui.showSaveError();
        }
    }

    /**
     * Returns true if the {@code Command} is a {@code ByeCommand}.
     *
     * @return Boolean value showing whether the {@code Command} is the {@code ByeCommand}.
     */
    public boolean isBye() {
        return false;
    }
}
