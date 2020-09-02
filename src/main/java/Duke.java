import java.util.Scanner;

public class Duke {
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_ADD_TODO = "todo";
    public static final String COMMAND_ADD_DEADLINE = "deadline";
    public static final String COMMAND_ADD_EVENT = "event";

    public static void showDivider() {
        System.out.println(">>>>++----------------------------------");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showDivider();
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        showDivider();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        TaskManager taskManager = new TaskManager();

        greet();

        while (!isBye) {
            String line = in.nextLine();
            String command = line.split(" ")[0];
            String argumentString = line.replaceFirst(command + " ", "");
            String description;

            showDivider();

            switch (command) {
            case COMMAND_LIST:
                taskManager.printTasks();
                break;
            case COMMAND_BYE:
                isBye = true;
                bye();
                break;
            case COMMAND_DONE:
                int listNumber = Integer.parseInt(line.split(" ")[1]);
                taskManager.markAsDone(listNumber - 1);
                break;
            case COMMAND_ADD_TODO:
                taskManager.addTodo(argumentString);
                break;
            case COMMAND_ADD_DEADLINE:
                String by = argumentString.split(" /by ")[1];
                description = argumentString.replace(" /by " + by, "");
                taskManager.addDeadline(description, by);
                break;
            case COMMAND_ADD_EVENT:
                String at = argumentString.split(" /at ")[1];
                description = argumentString.replace(" /at " + at, "");
                taskManager.addEvent(description, at);
                break;
            default:
                taskManager.addTask(line);
                break;
            }

            showDivider();
        }
    }
}
