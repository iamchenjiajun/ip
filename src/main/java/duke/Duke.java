package duke;

import duke.exception.FileFormatException;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    public static final String ERROR_TODO_NO_DESCRIPTION = "☹ OOPS!!! The description of a Todo cannot be empty.";
    public static final String ERROR_DEADLINE_NO_DESCRIPTION = "☹ OOPS!!! The description of a Deadline cannot be" +
            " empty.";
    public static final String ERROR_EVENT_NO_DESCRIPTION = "☹ OOPS!!! The description of an Event cannot be empty.";
    public static final String ERROR_NO_DEADLINE = "☹ OOPS!!! Your Deadline doesn't contain a deadline!";
    public static final String ERROR_NO_EVENT = "☹ OOPS!!! Your Event doesn't contain a date";
    public static final String ERROR_NO_DONE_ARGUMENT = "☹ OOPS!!! You need an argument to be done with!";
    public static final String ERROR_DONE_ARGUMENT = "☹ OOPS!!! Your done argument is invalid!";
    public static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_TASK_LOAD = "☹ OOPS!!! I'm sorry, but I couldn't load the tasks :-(";
    public static final String ERROR_TASK_FORMAT = "☹ OOPS!!! Your tasks are in the wrong format :-(";
    public static final String ERROR_TASK_SAVE = "☹ OOPS!!! I'm sorry, but I couldn't save the tasks :-(";

    private final TaskManager taskManager;
    private final Ui ui;

    public Duke() {
        taskManager = new TaskManager();
        ui = new Ui();
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
            checkArgumentsLength(arguments.length, 2, ERROR_NO_DONE_ARGUMENT);
            checkValidInteger(arguments[1], ERROR_DONE_ARGUMENT);
            int doneIndex = Integer.parseInt(arguments[1]);
            checkValidIntegerRange(doneIndex, taskManager.getTasksCount(), ERROR_DONE_ARGUMENT);
            taskManager.markAsDone(doneIndex - 1, true);
            break;
        case COMMAND_DELETE:
            checkArgumentsLength(arguments.length, 2, ERROR_NO_DONE_ARGUMENT);
            checkValidInteger(arguments[1], ERROR_DONE_ARGUMENT);
            int deleteIndex = Integer.parseInt(arguments[1]);
            checkValidIntegerRange(deleteIndex, taskManager.getTasksCount(), ERROR_DONE_ARGUMENT);
            taskManager.deleteTask(deleteIndex - 1);
            break;
        case COMMAND_ADD_TODO:
            checkArgumentsLength(arguments.length, 2, ERROR_TODO_NO_DESCRIPTION);
            taskManager.addTodo(argumentString, true);
            break;
        case COMMAND_ADD_DEADLINE:
            String[] deadlineDetails = argumentString.split(" /by ");
            checkArgumentsLength(arguments.length, 2, ERROR_DEADLINE_NO_DESCRIPTION);
            checkArgumentsLength(deadlineDetails.length, 2, ERROR_NO_DEADLINE);
            description = argumentString.replace(" /by " + deadlineDetails[1], "");
            taskManager.addDeadline(description, deadlineDetails[1], true);
            break;
        case COMMAND_ADD_EVENT:
            String[] eventDetails = argumentString.split(" /at ");
            checkArgumentsLength(arguments.length, 2, ERROR_EVENT_NO_DESCRIPTION);
            checkArgumentsLength(eventDetails.length, 2, ERROR_NO_EVENT);
            description = argumentString.replace(" /at " + eventDetails[1], "");
            taskManager.addEvent(description, eventDetails[1], true);
            break;
        default:
            throw new UnknownCommandException();
            // Fallthrough
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        boolean isBye;

        ui.greet();

        // load tasks from file
        try {
            taskManager.checkFileExists();
            taskManager.loadTasks();
            ui.showLoadSuccessful();
            ui.showDivider();
        } catch (IOException e) {
            System.out.println(ERROR_TASK_LOAD);
        } catch (FileFormatException e) {
            System.out.println(ERROR_TASK_FORMAT);
        }

        // parse and execute commands
        do {
            String line = in.nextLine();
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

        // save tasks to file
        try {
            taskManager.saveTasks();
        } catch (IOException e) {
            System.out.println(ERROR_TASK_SAVE);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
