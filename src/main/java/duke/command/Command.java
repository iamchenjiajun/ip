package duke.command;

import duke.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    public abstract void execute(TaskManager taskManager, Ui ui, Storage storage);

    public boolean isBye() {
        return false;
    }
}
