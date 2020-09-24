package duke;

import duke.exception.FileFormatException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    public static final String FILE_LOCATION = "./data/";
    public static final String FILE_NAME = "duke.txt";

    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    private TaskManager taskManager;
    private final Ui ui;
    private final Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_LOCATION, FILE_NAME);

        ui.greet();

        try {
            storage.checkFileExists();
            taskManager = new TaskManager(storage.loadTasks());
            ui.showLoadSuccessful();
        } catch (IOException e) {
            ui.showLoadError();
            taskManager = new TaskManager();
        } catch (FileFormatException e) {
            ui.showFileFormatError();
            taskManager = new TaskManager();
        } finally {
            ui.showDivider();
        }
    }

    public void checkArgumentsLength(int argumentLength, int expectedLength, String errorMessage)
            throws InvalidArgumentException {
        if (argumentLength < expectedLength) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    public void checkValidInteger(String integerString, String errorMessage) throws InvalidArgumentException {
        try {
            Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    public void checkValidIntegerRange(int checkInteger, int expectedValue, String errorMessage)
            throws InvalidArgumentException {
        boolean isMoreThan = checkInteger > expectedValue;
        boolean isLessThanOne = checkInteger < 1;

        if (isMoreThan || isLessThanOne) {
            throw new InvalidArgumentException(errorMessage);
        }
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

    public void run() {
        boolean isBye;

        do {
            String line = ui.readCommand();
            isBye = line.equals(COMMAND_BYE);

            ui.showDivider();
            try {
                parseCommand(line);
            } catch (UnknownCommandException e) {
                System.out.println(e.getErrorMessage());
            } catch (InvalidArgumentException e) {
                System.out.println(e.getErrorMessage());
            }
            ui.showDivider();
        } while (!isBye);

        try {
            storage.saveTasks(taskManager.getTasks());
        } catch (IOException e) {
            ui.showSaveError();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
