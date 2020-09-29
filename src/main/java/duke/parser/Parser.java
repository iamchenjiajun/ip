package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.exception.InvalidArgumentException;
import duke.exception.UnknownCommandException;
import duke.ui.Ui;

/**
 * Parses inputs from the user.
 */
public class Parser {
    /**
     * Parses a line of input from the user.
     *
     * @param line String containing user input.
     * @return An initialized command.
     * @throws UnknownCommandException If the user command is unrecognized.
     * @throws InvalidArgumentException If the arguments are invalid for the given command.
     */
    public Command parseCommand(String line) throws UnknownCommandException, InvalidArgumentException {
        String[] arguments = line.split(" ");
        String rootCommand = arguments[0];
        String argumentString = line.replaceFirst(rootCommand + " ", "");
        Command command;

        // Solution for refactoring to obtain Commands from separate functions adapted from
        // https://github.com/se-edu/addressbook-level2
        switch (rootCommand) {
        case Command.COMMAND_LIST:
            command = new ListCommand();
            break;
        case Command.COMMAND_BYE:
            command = new ByeCommand();
            break;
        case Command.COMMAND_DONE:
            command = createDoneCommand(arguments);
            break;
        case Command.COMMAND_DELETE:
            command = createDeleteCommand(arguments);
            break;
        case Command.COMMAND_DATE:
            command = createDateCommand(arguments);
            break;
        case Command.COMMAND_ADD_TODO:
            command = createAddTodoCommand(arguments, argumentString);
            break;
        case Command.COMMAND_ADD_DEADLINE:
            command = createAddDeadlineCommand(arguments, argumentString);
            break;
        case Command.COMMAND_ADD_EVENT:
            command = createAddEventCommand(arguments, argumentString);
            break;
        case Command.COMMAND_FIND:
            command = createFindCommand(arguments, argumentString);
            break;
        default:
            throw new UnknownCommandException();
            // Fallthrough
        }

        return command;
    }

    /**
     * Returns an instance of {@code DoneCommand} initialized by parsed arguments.
     *
     * @param arguments Array containing arguments.
     * @return Initialized DoneCommand.
     * @throws InvalidArgumentException If the arguments are invalid.
     */
    private Command createDoneCommand(String[] arguments) throws InvalidArgumentException {
        checkExactArgumentLength(arguments.length, DoneCommand.EXPECTED_ARGUMENT_LENGTH);
        checkValidInteger(arguments[1], Ui.ERROR_DONE_ARGUMENT);
        int doneIndex = Integer.parseInt(arguments[1]);

        return new DoneCommand(doneIndex - 1);
    }

    /**
     * Returns an instance of {@code DeleteCommand} initialized by parsed arguments.
     *
     * @param arguments Array containing arguments.
     * @return Initialized DeleteCommand.
     * @throws InvalidArgumentException If the arguments are invalid.
     */
    private Command createDeleteCommand(String[] arguments) throws InvalidArgumentException {
        checkExactArgumentLength(arguments.length, DeleteCommand.EXPECTED_ARGUMENT_LENGTH);
        checkValidInteger(arguments[1], Ui.ERROR_DELETE_ARGUMENT);
        int deleteIndex = Integer.parseInt(arguments[1]);

        return new DeleteCommand(deleteIndex - 1);
    }

    /**
     * Returns an instance of {@code DateCommand} initialized by parsed arguments.
     *
     * @param arguments Array containing arguments.
     * @return Initialized DateCommand
     * @throws InvalidArgumentException If the arguments are invalid.
     */
    private Command createDateCommand(String[] arguments) throws InvalidArgumentException {
        checkExactArgumentLength(arguments.length, DateCommand.EXPECTED_ARGUMENT_LENGTH);

        return new DateCommand(arguments[1]);
    }

    /**
     * Returns an instance of {@code AddTodoCommand} initialized by parsed arguments.
     *
     * @param arguments Array containing arguments.
     * @param argumentString String containing arguments.
     * @return Initialized AddTodoCommand.
     * @throws InvalidArgumentException If the arguments are invalid.
     */
    private Command createAddTodoCommand(String[] arguments, String argumentString) throws InvalidArgumentException {
        checkMinArgumentLength(arguments.length, AddTodoCommand.MIN_ARGUMENT_LENGTH, Ui.ERROR_TODO_NO_DESCRIPTION);

        return new AddTodoCommand(argumentString);
    }

    /**
     * Returns an instance of {@code AddDeadlineCommand} initialized by parsed arguments.
     *
     * @param arguments Array containing arguments.
     * @param argumentString String containing arguments.
     * @return Initialized AddDeadlineCommand.
     * @throws InvalidArgumentException If the arguments are invalid.
     */
    private Command createAddDeadlineCommand(String[] arguments, String argumentString)
            throws InvalidArgumentException {
        String[] deadlineDetails = argumentString.split(" /by ");
        checkMinArgumentLength(arguments.length, AddDeadlineCommand.MIN_ARGUMENT_LENGTH,
                Ui.ERROR_DEADLINE_NO_DESCRIPTION);
        checkMinArgumentLength(deadlineDetails.length, AddDeadlineCommand.MIN_ARGUMENT_LENGTH, Ui.ERROR_NO_DEADLINE);
        String description = argumentString.replace(" /by " + deadlineDetails[1], "");

        return new AddDeadlineCommand(description, deadlineDetails[1]);
    }

    /**
     * Returns an instance of {@code AddEventCommand} initialized by parsed arguments.
     *
     * @param arguments Array containing arguments.
     * @param argumentString String containing arguments.
     * @return Initialized AddEventCommand.
     * @throws InvalidArgumentException If the arguments are invalid.
     */
    private Command createAddEventCommand(String[] arguments, String argumentString) throws InvalidArgumentException {
        String[] eventDetails = argumentString.split(" /at ");
        checkMinArgumentLength(arguments.length, AddEventCommand.MIN_ARGUMENT_LENGTH, Ui.ERROR_EVENT_NO_DESCRIPTION);
        checkMinArgumentLength(eventDetails.length, AddEventCommand.MIN_ARGUMENT_LENGTH, Ui.ERROR_NO_EVENT);
        String description = argumentString.replace(" /at " + eventDetails[1], "");

        return new AddEventCommand(description, eventDetails[1]);
    }

    private Command createFindCommand(String[] arguments, String argumentString) throws InvalidArgumentException {
        checkMinArgumentLength(arguments.length, FindCommand.MIN_ARGUMENT_LENGTH, Ui.ERROR_FIND_ARGUMENT);
        return new FindCommand(argumentString);
    }

    /**
     * Checks if the argument length matches the expected argument length.
     *
     * @param argumentLength Actual argument length.
     * @param expectedLength Expected argument length.
     * @throws InvalidArgumentException If the argument length does not match the expected argument length.
     */
    private void checkExactArgumentLength(int argumentLength, int expectedLength)
            throws InvalidArgumentException {
        if (argumentLength != expectedLength) {
            throw new InvalidArgumentException(Ui.ERROR_INVALID_ARGUMENT_LENGTH);
        }
    }

    /**
     * Checks if the argument length exceeds the minimum required argument length.
     *
     * @param argumentLength Actual argument length.
     * @param minimumLength Minimum argument length.
     * @param errorMessage Error message passed to the exception.
     * @throws InvalidArgumentException If the argument length is less than the minimum argument length.
     */
    private void checkMinArgumentLength(int argumentLength, int minimumLength, String errorMessage)
            throws InvalidArgumentException {
        if (argumentLength < minimumLength) {
            throw new InvalidArgumentException(errorMessage);
        }
    }

    /**
     * Checks if a String represents a valid integer.
     *
     * @param integerString String representing the integer.
     * @param errorMessage Error message passed to the exception.
     * @throws InvalidArgumentException If the String cannot be parsed to an integer.
     */
    private void checkValidInteger(String integerString, String errorMessage) throws InvalidArgumentException {
        try {
            Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(errorMessage);
        }
    }
}
