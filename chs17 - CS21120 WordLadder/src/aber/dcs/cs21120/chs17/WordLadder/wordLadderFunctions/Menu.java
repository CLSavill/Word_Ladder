package aber.dcs.cs21120.chs17.WordLadder.wordLadderFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import java.util.Scanner;

/**
 * CS21120 WordLadder menu launcher class
 * 
 * @author Chris Savill - chs17
 */
public class Menu {
	// ////////////////////// Methods ///////////////////////////

	/**
	 * Method to launch a menu
	 */
	public void initialiseMenu() {
		Graph graph;
		int menuChoice = 0;
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to WordLadder!");
		System.out.println("----------------------");

		do {
			System.out
					.println("\nPlease select one of the following options: ");
			System.out.println("Option 1: Generate a word ladder.");
			System.out
					.println("Option 2: Discover the shortest word ladder between 2 words.");
			System.out.println("Option 3: Exit.\n");

			while (!input.hasNextInt()) {
				System.out.println("Please enter in a valid option.");
				input.next();
			}

			menuChoice = input.nextInt();

			switch (menuChoice) {
			case 1:
				graph = new Graph();
				graph.generateCycle();
				break;
			case 2:
				graph = new Graph();
				graph.discoveryCycle();
				break;
			case 3:
				System.out.println("Exiting WordLadder...");
				break;
			default:
				System.out
						.println("Invalid option selected, please select a valid option.");
			}
		} while (menuChoice != 3);
	}
}
