package duke.parser;

import duke.TaskManager;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class Parser {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    private final TaskManager taskManager;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskManager taskManager, Ui ui, Storage storage) {
        this.taskManager = taskManager;
        this.ui = ui;
        this.storage = storage;
    }

    public void parseCommand(String line) throws UnknownCommandException, InvalidArgumentException {
        String[] arguments = line.split(" ");
        String command = arguments[0];
        String argumentString = line.replaceFirst(command + " ", "");
        String description;

        switch (command) {
        case COMMAND_LIST:
            taskManager.printTasks();
            break;
        case COMMAND_BYE:
            ui.showByeMessage();
            try {
                storage.saveTasks(taskManager.getTasks());
            } catch (IOException e) {
                ui.showSaveError();
            }
            break;
        case COMMAND_DONE:
            checkArgumentsLength(arguments.length, 2, Ui.ERROR_NO_DONE_ARGUMENT);
            checkValidInteger(arguments[1], Ui.ERROR_DONE_ARGUMENT);
            int doneIndex = Integer.parseInt(arguments[1]);
            checkValidIntegerRange(doneIndex, taskManager.getTasksCount(), Ui.ERROR_DONE_ARGUMENT);
            taskManager.markAsDone(doneIndex - 1, true);
            break;
        case COMMAND_DELETE:
            checkArgumentsLength(arguments.length, 2, Ui.ERROR_NO_DONE_ARGUMENT);
            checkValidInteger(arguments[1], Ui.ERROR_DONE_ARGUMENT);
            int deleteIndex = Integer.parseInt(arguments[1]);
            checkValidIntegerRange(deleteIndex, taskManager.getTasksCount(), Ui.ERROR_DONE_ARGUMENT);
            taskManager.deleteTask(deleteIndex - 1);
            break;
        case COMMAND_ADD_TODO:
            checkArgumentsLength(arguments.length, 2, Ui.ERROR_TODO_NO_DESCRIPTION);
            taskManager.addTodo(argumentString, true);
            break;
        case COMMAND_ADD_DEADLINE:
            String[] deadlineDetails = argumentString.split(" /by ");
            checkArgumentsLength(arguments.length, 2, Ui.ERROR_DEADLINE_NO_DESCRIPTION);
            checkArgumentsLength(deadlineDetails.length, 2, Ui.ERROR_NO_DEADLINE);
            description = argumentString.replace(" /by " + deadlineDetails[1], "");
            taskManager.addDeadline(description, deadlineDetails[1], true);
            break;
        case COMMAND_ADD_EVENT:
            String[] eventDetails = argumentString.split(" /at ");
            checkArgumentsLength(arguments.length, 2, Ui.ERROR_EVENT_NO_DESCRIPTION);
            checkArgumentsLength(eventDetails.length, 2, Ui.ERROR_NO_EVENT);
            description = argumentString.replace(" /at " + eventDetails[1], "");
            taskManager.addEvent(description, eventDetails[1], true);
            break;
        default:
            throw new UnknownCommandException();
            // Fallthrough
        }
    }

    private void checkArgumentsLength(int argumentLength, int expectedLength, String errorMessage)
            throws InvalidArgumentException {
        if (argumentLength < expectedLength) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    private void checkValidInteger(String integerString, String errorMessage) throws InvalidArgumentException {
        try {
            Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    private void checkValidIntegerRange(int checkInteger, int expectedValue, String errorMessage)
            throws InvalidArgumentException {
        boolean isMoreThan = checkInteger > expectedValue;
        boolean isLessThanOne = checkInteger < 1;

        if (isMoreThan || isLessThanOne) {
            throw new InvalidArgumentException(errorMessage);
        }
    }
}
