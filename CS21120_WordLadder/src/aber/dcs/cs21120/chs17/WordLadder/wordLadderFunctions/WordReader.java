package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chris Savill - chs17
 * Class that contains various methods to read words from a file
 */
public class WordReader {

    //////////////////////// Methods ///////////////////////////
    /**
     * Method that reads the words of a file into a LinkedList of String type
     *
     * @param wordLength int value of the word length for use with defining the
     * data file to be used
     * @return Returns the new LinkedList of String type containing the word
     * list
     */
    public LinkedList<String> readWords(int wordLength) {
        int numberOfWords = 0;
        int numberOfRelevantWords = 0;
        String wordRead;
        boolean wordAlreadyStored;

        try {
            BufferedReader fileToBeRead = new BufferedReader(new FileReader("WordsOfLength" + wordLength + ".dat"));

            while (fileToBeRead.readLine() != null) { //Loops to the end of the file
                numberOfWords++;
            }

            fileToBeRead.close();

        } catch (IOException ex) {
            Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BufferedReader fileToBeRead = new BufferedReader(new FileReader("WordsOfLength" + wordLength + ".dat"));
            LinkedList<String> wordStore = new LinkedList();

            for (int counter = 0; counter < numberOfWords; counter++) {
                if (numberOfRelevantWords == 0) {
                    try {
                        wordStore.add(fileToBeRead.readLine()); //Adds new word to the word store LinkedList
                    } catch (IOException ex) {
                        Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    wordAlreadyStored = false;
                    try {
                        wordRead = fileToBeRead.readLine();

                        for (int counter2 = 0; counter2 < numberOfRelevantWords || wordAlreadyStored == true; counter2++) {
                            if (wordStore.get(counter2).equals(wordRead)) { //Evaluates if word is already contained in the word store LinkedList
                                wordAlreadyStored = true;
                            }
                        }
                        if (wordAlreadyStored == false) {
                            wordStore.add(wordRead);
                            numberOfRelevantWords++;
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            try {
                fileToBeRead.close();
                return wordStore;
            } catch (IOException ex) {
                Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
