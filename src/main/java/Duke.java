import java.util.Scanner;

public class Duke {
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

    public static void printTasks(Task[] tasks, int tasksCount) {
        for (int i=0; i<tasksCount; i++) {
            int index = i+1;
            System.out.println(index + "."+ tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean isBye = false;
        Task[] tasks = new Task[100];
        int tasksCount = 0;

        greet();

        while (!isBye) {
            String line = in.nextLine();
            showDivider();

            if (line.equals("bye")) {
                isBye = true;
                bye();
            } else if (line.equals("list")) {
                printTasks(tasks, tasksCount);
            } else if (line.startsWith("done")) {
                int argument = Integer.parseInt(line.split(" ")[1]);
                tasks[argument-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[argument-1].getStatusIcon() + " " + tasks[argument-1].getDescription());
            } else {
                tasks[tasksCount] = new Task(line);
                tasksCount++;
                System.out.println("Added " + line);
            }

            showDivider();
        }
    }
}
