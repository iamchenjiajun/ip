package duke.ui;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface that interacts with the user.
 */
public class Ui {
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
    public static final String TASK_LOAD_SUCCESSFUL = "Tasks loaded successfully.";

    public static final String ERROR_TODO_NO_DESCRIPTION = "☹ OOPS!!! The description of a Todo cannot be empty.";
    public static final String ERROR_DEADLINE_NO_DESCRIPTION = "☹ OOPS!!! The description of a Deadline cannot be" +
            " empty.";
    public static final String ERROR_EVENT_NO_DESCRIPTION = "☹ OOPS!!! The description of an Event cannot be empty.";
    public static final String ERROR_NO_DEADLINE = "☹ OOPS!!! Your Deadline doesn't contain a deadline!";
    public static final String ERROR_NO_EVENT = "☹ OOPS!!! Your Event doesn't contain a date";
    public static final String ERROR_DONE_ARGUMENT = "☹ OOPS!!! Your done argument is invalid!";
    public static final String ERROR_DELETE_ARGUMENT = "☹ OOPS!!! Your delete argument is invalid!";
    public static final String ERROR_INVALID_ARGUMENT_LENGTH = "☹ OOPS!!! You have an invalid number of arguments!";
    public static final String ERROR_UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String ERROR_TASK_LOAD = "☹ OOPS!!! I'm sorry, but I couldn't load the tasks :-(";
    public static final String ERROR_TASK_FORMAT = "☹ OOPS!!! Your tasks are in the wrong format :-(";
    public static final String ERROR_TASK_SAVE = "☹ OOPS!!! I'm sorry, but I couldn't save the tasks :-(";
    public static final String ERROR_DATETIME_FORMAT = "☹ OOPS!!! Your date/time is in the wrong format :-(\n" +
            "The correct format is 'yyyy-MM-dd HH:mm'!";
    public static final String ERROR_DATE_FORMAT = "☹ OOPS!!! Your date is in the wrong format :-(\n" +
            "The correct format is 'yyyy-MM-dd'!";

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints a divider.
     */
    public void showDivider() {
        System.out.println(DUKE_DIVIDER);
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        System.out.println(DUKE_LOGO);
        showDivider();
        System.out.println(DUKE_GREETINGS);
        showDivider();
    }

    /**
     * Prints a bye message.
     */
    public void showByeMessage() {
        System.out.println(DUKE_BYE);
    }

    /**
     * Prints a message indicating a successful load.
     */
    public void showLoadSuccessful() {
        System.out.println(TASK_LOAD_SUCCESSFUL);
    }

    /**
     * Prints a message indicating a load error.
     */
    public void showLoadError() {
        System.out.println(ERROR_TASK_LOAD);
    }

    /**
     * Prints a message indicating an error with the formatting of the file contents.
     */
    public void showFileFormatError() {
        System.out.println(ERROR_TASK_FORMAT);
    }

    /**
     * Prints a message indicating a save error.
     */
    public void showSaveError() {
        System.out.println(ERROR_TASK_SAVE);
    }

    /**
     * Returns a string representing a line of input from the user.
     *
     * @return Line of input from user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
