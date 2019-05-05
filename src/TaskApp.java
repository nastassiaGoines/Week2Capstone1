import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Scanner;

//Nastassia Goines
//Week2Capstone Lab

public class TaskApp {
	
	public static ArrayList<Task> taskList = new ArrayList<>();

	public static void main(String[] args) {

		
		

		Scanner scan = new Scanner(System.in);

		// populate list with examples
		taskList.add(new Task("Alex", "Finish Week 2 Capstone", LocalDate.parse("05/06/2019", TaskValidation.f)));
		taskList.add(new Task("Leo", "Paint Mona Lisa", LocalDate.parse("06/17/1506", TaskValidation.f)));
		taskList.add(new Task("Issac", "Write Prinicpia", LocalDate.parse("10/30/1684", TaskValidation.f)));
		taskList.add(new Task("Enrico", "Neutron-induced radioactivity", LocalDate.parse("04/25/1934", TaskValidation.f)));
		taskList.add(new Task("Alan", "Automatic Computing Engine", LocalDate.parse("05/08/1945", TaskValidation.f)));

		// Greet user
		System.out.println("Welcome to the Task Manager");
		System.out.println("===========================");

		while (true) {
			showMenu();

			int choice = chooser(scan, "What would you like to do?: ");

			if (choice == 1) {
				listTasks();
			} else if (choice == 2) {
				addTask(scan);
			} else if (choice == 3) {
				deleteTask(scan);
			} else if (choice == 4) {
				completeTask(scan);
			} else if (choice == 5) {
				editTask(scan);
			} else if (choice == 6) {
				int go = TaskValidation.getGo(scan, "Are you sure you want to quit? (y/n): ");
				if (go == 1) {
					break;
				} else if (go == 2) {
					continue;
				} else {
					System.out.println("error!");
					continue;
				}
			}
		}

		System.out.println("Goodbye!");
		scan.close();
	}

	
	public static void showMenu() {
		System.out.println();
		System.out.println("***************************");
		System.out.println("******* MENU **************");
		System.out.println("***************************");
		System.out.println("*** 1. List tasks *********");
		System.out.println("*** 2. Add task ***********");
		System.out.println("*** 3. Delete task ********");
		System.out.println("*** 4. Complete task ******");
		System.out.println("*** 5. Edit task **********");
		System.out.println("*** 6. Quit ***************");
		System.out.println("***************************");
		System.out.println("***************************");
	}

	public static int chooser(Scanner scan, String prompt) {
		char c;
		int choice = 0;
		try {
			System.out.print(prompt);
			c = scan.next().charAt(0);
			if (c == '1' | c == 'l' | c == 'L') {
				choice = 1;
			} else if (c == '2' | c == 'a' | c == 'A') {
				choice = 2;
			} else if (c == '3' | c == 'd' | c == 'D') {
				choice = 3;
			} else if (c == '4' | c == 'c' | c == 'C') {
				choice = 4;
			} else if (c == '5' | c == 'e' | c == 'E') {
				choice = 5;
			} else if (c == '6' | c == 'q' | c == 'Q') {
				choice = 6;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Try again!");
		}
		scan.nextLine();
		return choice;
	}

	public static void listTasks() {
		printHeader();
		for (int i = 0; i < taskList.size(); i++) {
			System.out.printf("%-4s%-14s%-34s%-12s%s\n", (i + 1), taskList.get(i).getName(),
					taskList.get(i).getDescription(), taskList.get(i).getDate().format(TaskValidation.f).toString(),
					taskList.get(i).isComplete());
		}
		System.out.println();
	}

	public static void addTask(Scanner scan) {
		String name = TaskValidation.getLine(scan, "Team Member Name: ");
		String description = TaskValidation.getLine(scan, "Task Description: ");
		description = String.format("%1.33s", description);
		LocalDate date = TaskValidation.getDate(scan, "Due Date: ");
		Task newTask = new Task(name, description, date);
		taskList.add(newTask);
		System.out.println("\n***Task Added***");

	}

	public static void deleteTask(Scanner scan) {
		listTasks();
		int index = TaskValidation.getInt(scan, "Which task would you like to delete? (0 to cancel): ", 0, taskList.size());
		if (index == 0) {
			System.out.println("\n***Delete Cancelled***");
		} else {
			printHeader();
			index = index - 1;
			System.out.printf("%-4s%-14s%-34s%-12s%s\n", (index + 1), taskList.get(index).getName(),
					taskList.get(index).getDescription(), taskList.get(index).getDate().format(TaskValidation.f).toString(),
					taskList.get(index).isComplete());
			System.out.println();
			int safety = TaskValidation.getGo(scan, "Are you sure? (y/n): ");
			if (safety == 2) {
				System.out.println("\n***Delete Cancelled***");
			} else if (safety == 1) {
				taskList.remove(index);
				System.out.println("\n***Task Deleted***");
			}
		}
	}

	public static void completeTask(Scanner scan) {
		listTasks();
		int index = TaskValidation.getInt(scan, "Which task would you like to complete? (0 to cancel): ", 0,
				taskList.size());
		if (index == 0) {
			System.out.println("\n***Complete Cancelled***");
		} else {
			printHeader();
			index = index - 1;
			System.out.printf("%-4s%-14s%-34s%-12s%s\n", (index + 1), taskList.get(index).getName(),
					taskList.get(index).getDescription(), taskList.get(index).getDate().format(TaskValidation.f).toString(),
					taskList.get(index).isComplete());
			System.out.println();
			int safety = TaskValidation.getGo(scan, "Are you sure? (y/n): ");
			if (safety == 2) {
				System.out.println("\n***Complete Cancelled***");
			} else if (safety == 1) {
				taskList.get(index).setComplete();
				System.out.println("\n***Task completed***");
			}
		}
	}

	public static void editTask(Scanner scan) {
		listTasks();
		int index = TaskValidation.getInt(scan, "Which task would you like to edit? (0 to cancel/ -1 to batch complete): ",
				-1, taskList.size());
		if (index == 0) {
			System.out.println("\n***Edit Cancelled***");
		} else if (index == -1) {
			int comp = TaskValidation.getInt(scan, "Enter 0 to batch incomplete, 1 to batch complete: ", 0, 1);
			if (comp == 0) {
				ArrayList<Integer> tracker = TaskValidation.getBatch(scan,
						"Enter a task number to add to the batch (0 to continue): ");
				for (int i = 0; i < tracker.size(); i++) {
					taskList.get(tracker.get(i) - 1).setIncomplete();
				}
				System.out.println("\n***Edit Recorded***");
			} else if (comp == 1) {
				ArrayList<Integer> tracker = TaskValidation.getBatch(scan,
						"Enter a task number to add to the batch (0 to continue): ");
				for (int i = 0; i < tracker.size(); i++) {
					taskList.get(tracker.get(i) - 1).setComplete();
				}
				System.out.println("\n***Edit Recorded***");
			}
		} else {
			index = index - 1;
			while (true) {
				String field = TaskValidation.getString(scan, "What would you like to edit?: ");
				if (field.equalsIgnoreCase("name") | field.charAt(0) == 'n' | field.charAt(0) == 'N') {
					String newName = TaskValidation.getLine(scan, "Team Member Name: ");
					taskList.get(index).setName(newName);
					System.out.println("\n***Edit Recorded***");
					break;
				} else if (field.equalsIgnoreCase("description") | field.charAt(0) == 'd') {
					String newDesc = TaskValidation.getLine(scan, "Description: ");
					newDesc = String.format("%1.33s", newDesc);
					taskList.get(index).setDescription(newDesc);
					System.out.println("\n***Edit Recorded***");
					break;
				} else if (field.equalsIgnoreCase("date") | field.charAt(0) == 'D') {
					LocalDate newDate = TaskValidation.getDate(scan, "Date: ");
					taskList.get(index).setDate(newDate);

					System.out.println("\n***Edit Recorded***");
					break;
				} else if (field.equalsIgnoreCase("status") | field.charAt(0) == 's' | field.charAt(0) == 'S') {
					int newStatus = TaskValidation.getInt(scan, "Enter 0 for Incomplete, 1 for Complete: ", 0, 1);
					if (newStatus == 1) {
						taskList.get(index).setComplete();
					}
					if (newStatus == 0) {
						taskList.get(index).setIncomplete();
					}
					System.out.println("\n***Edit Recorded***");
					break;
				}
			}
		}
	}

	public static void printHeader() {
		System.out.println();
		System.out.printf("%-4s%-14s%-34s%-12s%s\n", "#", "Team Member", "Description", "Due Date", "Status");
		int[] underline = new int[76];
		for (int i : underline)
			System.out.print("~");
		System.out.println();
	}


}
