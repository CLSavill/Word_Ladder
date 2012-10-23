package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import java.util.Scanner;

/**
 * @author Chris Savill - chs17 CS21120 WordLadder menu launcher class
 */
public class Menu {
    //////////////////////// Variables ///////////////////////////

    /**
     * int primitive to store the menu choice
     */
    private int menuChoice = 0;
    /**
     * GenerateCycler class used to provide access to the method that runs the
     * generation functionality
     */
    private GenerateCycler generateCycler;
    /**
     * DiscoveryCycler class used to provide access to the method that runs the
     * discovery functionality
     */
    private DiscoveryCycler discoveryCycler;
    /**
     * Scanner class used to retrieve input from user
     */
    private Scanner input = new Scanner(System.in);

    //////////////////////// Methods ///////////////////////////
    /**
     * Method to launch a menu
     */
    public void initialiseMenu() {
        System.out.println("Welcome to WordLadder!");
        System.out.println("----------------------");

        do {
            System.out.println("\nPlease select one of the following options: ");
            System.out.println("Option 1: Generate a word ladder.");
            System.out.println("Option 2: Discover the shortest word ladder between 2 words.");
            System.out.println("Option 3: Exit.\n");

            while (!input.hasNextInt()) {
                System.out.println("Please enter in a valid option.");
                input.next();
            }

            menuChoice = input.nextInt();

            switch (menuChoice) {
                case 1:
                    generateCycler = new GenerateCycler();
                    generateCycler.generateGraphSetupCycle();
                    break;
                case 2:
                    discoveryCycler = new DiscoveryCycler();
                    discoveryCycler.discoveryGraphSetupCycle();
                    break;
                case 3:
                    System.out.println("Exiting WordLadder...");
                    break;
                default:
                    System.out.println("Invalid option selected, please select a valid option.");
            }
        } while (menuChoice != 3);
    }
}
