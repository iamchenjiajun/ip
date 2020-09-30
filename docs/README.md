# Duke User Guide

Duke is an interactive task management program which allows the user to store and manage tasks interactively.

## Launching Duke

After navigating to the folder containing `duke.jar` in the shell, run the following command.

`java -jar duke.jar`

Duke saves the task lists to a file with the relative directory `./data/duke.txt`.

If the directory and/or file does not exist when Duke launches, Duke creates the directory and/or file before
loading the file.

## Features 

### Adding Tasks

Adds a task to the list of tasks.

There are several types of tasks that can be added, such as a Todo, Deadline or Event.

`todo <description>` - Adds a todo to the list.

`deadline <description> /by <yyyy-MM-dd HH:mm>` - Adds a deadline to the list.

`event <description> /at <yyyy-MM-dd HH:mm>` - Adds an event to the list.

Example of adding a Todo:

`todo read book`

Output:

```
>>>>++----------------------------------
Added read book as a Todo.
[T][✗] read book
>>>>++----------------------------------
```

Example of adding a Deadline:

`deadline ip final submission /by 2020-10-02 23:59`

Output:

```
>>>>++----------------------------------
Added ip final submission as a Deadline.
[D][✗] ip final submission (by: Oct 02 2020 23:59)
>>>>++----------------------------------
```

Example of adding an Event:

`event consultation meeting with prof /at 2020-10-07 16:00`

Output:

```
>>>>++----------------------------------
Added consultation meeting with prof as an Event.
[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
>>>>++----------------------------------
```

### Listing Tasks

Lists all the tasks that are tracked by Duke.

`list` - Lists all tasks.

Example:

`list`

Output:

```
>>>>++----------------------------------
1.[E][✓] cs2113 meeting (at: Dec 05 2020 11:30)
2.[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
3.[T][✗] set up a meeting with academic advisor
4.[T][✓] submit budget request
5.[D][✗] ip final submission (by: Oct 02 2020 23:59)
>>>>++----------------------------------
```

### Marking Tasks as "Done"

Marks a task as "Done" at a given index.

`done <index>` - Marks the task at the given index as "Done".

Example:

`done 1`

Output:

```
>>>>++----------------------------------
Nice! I've marked this task as done:
[E][✓] cs2113 meeting (at: Dec 05 2020 11:30)
>>>>++----------------------------------
```

### Deleting Tasks

Deletes a task at a given index.

`delete <index>` - Deletes the task at the given index.

Example:

`delete 7`

Output:

```
>>>>++----------------------------------
Noted. I've removed this task:
[T][✗] read book
Now you have 8 tasks in the list
>>>>++----------------------------------
```

### Finding Tasks

Prints a list of tasks with description that matches a keyword.

:information_source: The command uses **case-insensitive** matching.

`find <search string>` - Searches the list for tasks that contains the search string in the description.

Example:

`find meeting`

Output:

```
>>>>++----------------------------------
Here's the tasks containing 'meeting':
1.[E][✓] cs2113 meeting (at: Dec 05 2020 11:30)
2.[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
3.[T][✗] set up a meeting with academic advisor
>>>>++----------------------------------
```

### Listing tasks with given date

Prints a list of tasks that matches the given date.

`date <yyyy-MM-dd>` - Searches the list for tasks that has a date yyyy-MM-dd

Example:

`date 2020-10-07`

Output:

```
>>>>++----------------------------------
Here's an overview of Oct 07 2020
[E][✗] consultation meeting with prof (at: Oct 07 2020 16:00)
[E][✗] collect lab kit (at: Oct 07 2020 14:00)
>>>>++----------------------------------
```

### Exiting Duke

Saves the existing list of tasks to a file and terminates the program.

Example:

`bye`

Output:

```
>>>>++----------------------------------
Bye. Hope to see you again soon!
>>>>++----------------------------------
```

## Quick Command Reference

Action | Command | Example
------------ | ------------ | -------------
Add todo | `todo <description>` | `todo read book`
Add deadline | `deadline <description> /by <yyyy-MM-dd HH:mm>` | `deadline ip final submission /by 2020-10-02 23:59`
Add event | `event <description> /at <yyyy-MM-dd HH:mm>` | `event consultation meeting with prof /at 2020-10-07 16:00`
Listing tasks | `list` | `list`
Mark task as done | `done <index>` | `done 1`
Delete task | `delete <index>` | `delete 7`
Finding tasks | `find <search string>` | `find meeting`
Date overview | `date <yyyy-MM-dd>` | `date 2020-10-7`
Exit Duke | `bye` | `bye`
