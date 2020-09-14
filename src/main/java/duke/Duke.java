package duke;

import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;

import java.util.Scanner;

public class Duke {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";
    public static final String COMMAND_DELETE = "delete";

    public static final String DUKE_DIVIDER = ">>>>++----------------------------------";
    public static final String DUKE_GREETINGS = "Hello! I'm Duke!\n"
            + "What can I do for you?";
    public static final String DUKE_LOGO = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String DUKE_BYE = "Bye. Hope to see you again soon!";

    public static final String ERROR_TODO_NO_DESCRIPTION = "☹ OOPS!!! The description of a Todo cannot be empty.";
    public static final String ERROR_DEADLINE_NO_DESCRIPTION = "☹ OOPS!!! The description of a Deadline cannot be" +
            " empty.";
    public static final String ERROR_EVENT_NO_DESCRIPTION = "☹ OOPS!!! The description of an Event cannot be empty.";
    public static final String ERROR_NO_DEADLINE = "☹ OOPS!!! Your Deadline doesn't contain a deadline!";
    public static final String ERROR_NO_EVENT = "☹ OOPS!!! Your Event doesn't contain a date";
    public static final String ERROR_NO_DONE_ARGUMENT = "☹ OOPS!!! You need an argument to be done with!";
    public static final String ERROR_DONE_ARGUMENT = "☹ OOPS!!! Your done argument is invalid!";
    public static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private static final TaskManager taskManager = new TaskManager();

    public static void showDivider() {
        System.out.println(DUKE_DIVIDER);
    }

    public static void greet() {
        System.out.println(DUKE_LOGO);
        showDivider();
        System.out.println(DUKE_GREETINGS);
        showDivider();
    }

    public static void bye() {
        System.out.println(DUKE_BYE);
    }

    public static void checkArgumentsLength(int argumentLength, int expectedLength, String errorMessage)
            throws InvalidArgumentException {
        if (argumentLength < expectedLength) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    public static void checkValidInteger(String integerString, String errorMessage) throws InvalidArgumentException {
        try {
            Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    public static void checkValidIntegerRange(int checkInteger, int expectedValue, String errorMessage)
            throws InvalidArgumentException {
        boolean isMoreThan = checkInteger > expectedValue;
        boolean isLessThanOne = checkInteger < 1;

        if (isMoreThan || isLessThanOne) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    public static void parseCommand(String line) throws UnknownCommandException, InvalidArgumentException {
        String[] arguments = line.split(" ");
        String command = arguments[0];
        String argumentString = line.replaceFirst(command + " ", "");
        String description;

        switch (command) {
        case COMMAND_LIST:
            taskManager.printTasks();
            break;
        case COMMAND_BYE:
            bye();
            break;
        case COMMAND_DONE:
            checkArgumentsLength(arguments.length, 2, ERROR_NO_DONE_ARGUMENT);
            checkValidInteger(arguments[1], ERROR_DONE_ARGUMENT);
            int doneIndex = Integer.parseInt(arguments[1]);
            checkValidIntegerRange(doneIndex, taskManager.getTasksCount(), ERROR_DONE_ARGUMENT);
            taskManager.markAsDone(doneIndex - 1);
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
            taskManager.addTodo(argumentString);
            break;
        case COMMAND_ADD_DEADLINE:
            String[] deadlineDetails = argumentString.split(" /by ");
            checkArgumentsLength(arguments.length, 2, ERROR_DEADLINE_NO_DESCRIPTION);
            checkArgumentsLength(deadlineDetails.length, 2, ERROR_NO_DEADLINE);
            description = argumentString.replace(" /by " + deadlineDetails[1], "");
            taskManager.addDeadline(description, deadlineDetails[1]);
            break;
        case COMMAND_ADD_EVENT:
            String[] eventDetails = argumentString.split(" /at ");
            checkArgumentsLength(arguments.length, 2, ERROR_EVENT_NO_DESCRIPTION);
            checkArgumentsLength(eventDetails.length, 2, ERROR_NO_EVENT);
            description = argumentString.replace(" /at " + eventDetails[1], "");
            taskManager.addEvent(description, eventDetails[1]);
            break;
        default:
            throw new UnknownCommandException();
            // Fallthrough
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isBye;

        greet();

        do {
            String line = in.nextLine();
            isBye = line.equals(COMMAND_BYE);

            showDivider();
            try {
                parseCommand(line);
            } catch (UnknownCommandException e) {
                System.out.println(e.getErrorMessage());
            } catch (InvalidArgumentException e) {
                System.out.println(e.getErrorMessage());
            }
            showDivider();
        } while (!isBye);
    }
}
