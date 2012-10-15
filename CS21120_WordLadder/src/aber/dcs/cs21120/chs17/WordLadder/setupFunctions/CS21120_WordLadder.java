/**
 * @author Chris Savill - chs17
 * @title CS21120 WordLadder project main class
 */
package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import java.util.Scanner;

public class CS21120_WordLadder {

    Scanner input = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int menuChoice = 0;
        Scanner input = new Scanner(System.in);

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
                    //Generate function
                    break;
                case 2:
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

    public void generateWordLadder() {
        WordReader reader = new WordReader();
        Scanner input = new Scanner(System.in);
        String wordChosen;
        String[] wordList;
        int stepsInLadder = 0;
        boolean wordPresent = false;

        while (wordPresent == false) {
            System.out.println("Please enter in a word to generate a word ladder for: ");
            wordChosen = input.next();
            System.out.println("Please enter in the number of steps in the ladder you want generated: ");
            stepsInLadder = input.nextInt();

            wordList = reader.readWords(wordChosen.length());

            if (checkWordPresent(wordList, wordChosen) == true) {
                wordPresent = true;
            } else
            {
              System.out.println("Word is not present in file, please try another word.");   
            }
        }
        
        
    }

    private boolean checkWordPresent(String[] wordList, String wordChosen) {
        boolean wordPresent = false;
        for (int counter = 0; counter < wordList.length || wordPresent == false; counter++) {
            if (wordList[counter].equals(wordChosen)) {
                wordPresent = true;
            }
        }
        return wordPresent;
    }
}