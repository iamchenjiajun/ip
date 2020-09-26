package duke.command;

import duke.taskmanager.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    public static final int MIN_ARGUMENT_LENGTH = 2;
    String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.addTodo(description);
    }
}
