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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int menuChoice = 0;
        Scanner input = new Scanner(System.in);
        Graph graph;
        GenerateLadder generateLadder;
        DiscoverLadder discoverLadder;
        
        System.out.println("Welcome to WordLadder!");
        System.out.println("----------------------");
        
        while (menuChoice != 3) {
            System.out.println("Please select one of the following options: ");
            System.out.println("Option 1: Generate a word ladder.");
            System.out.println("Option 2: Discover the shortest word ladder between 2 words.");
            System.out.println("Option 3: Exit.\n");
            
            menuChoice = input.nextInt();
            
            switch (menuChoice) {
                case 1:
                    graph = new Graph();
                    generateLadder = new GenerateLadder(graph);
                    generateLadder.generateLadder();
                    break;
                case 2:
                    graph = new Graph();
                    //discoverLadder.discoverLadder();
                    //Discover function
                    break;
                case 3:
                    System.out.println("Exiting WordLadder...");
                    break;
                default:
                    System.out.println("Invalid option selected, please select a valid option.");
            }
        }
    }
}
