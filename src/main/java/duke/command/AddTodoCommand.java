package duke.command;

import duke.TaskManager;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddTodoCommand extends Command {
    String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) {
        taskManager.addTodo(description, true);
    }
}
