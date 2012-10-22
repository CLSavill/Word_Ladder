/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder project main class
 */
package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.Graph;
import aber.dcs.cs21120.chs17.WordLadder.searchFunctions.DiscoverLadder;
import aber.dcs.cs21120.chs17.WordLadder.searchFunctions.GenerateLadder;
import java.util.Scanner;

public class CS21120_WordLadder {

    /**
     * Main method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int menuChoice = 0;
        Scanner input = new Scanner(System.in);
        GenerateLadder generateLadder;
        DiscoverLadder discoverLadder;

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
                    Graph generatorGraph = new Graph();
                    generateLadder = new GenerateLadder(generatorGraph);
                    generateLadder.generateLadder();
                    break;
                case 2:
                    Graph discoveryGraph = new Graph();
                    discoverLadder = new DiscoverLadder(discoveryGraph);
                    discoverLadder.discoverLadder();
                    break;
                case 3:
                    System.out.println("Exiting WordLadder...");
                    break;
                default:
                    System.out.println("Invalid option selected, please select a valid option.");
            }
        } while (menuChoice
                != 3);
    }
}
