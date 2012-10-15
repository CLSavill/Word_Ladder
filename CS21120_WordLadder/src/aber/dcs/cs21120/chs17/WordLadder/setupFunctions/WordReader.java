/**
 * @author Chris Savill - chs17
 * @title Class that contains various methods to read lines
 */
package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordReader {

    public String[] readWords(int wordLength) {
        int numberOfWords = 1000;
        int numberOfRelevantWords = 0;
        String wordRead;
        boolean wordAlreadyStored = true;

        try {
            BufferedReader fileToBeRead = new BufferedReader(new FileReader("WordList.txt"));
            String[] wordStore = new String[numberOfWords];
            String[] wordList;

            for (int counter = 0; counter < numberOfWords; counter++) {
                wordAlreadyStored = true;
                try {
                    wordRead = fileToBeRead.readLine();
                    if (wordRead.length() == wordLength) {
                        for (int counter2 = 0; counter2 < numberOfRelevantWords || wordAlreadyStored == true; counter2++) {
                            if (!wordRead.equals(wordStore[counter2])) {
                                wordAlreadyStored = false;
                            }
                        }
                        if (wordAlreadyStored == false) {
                            wordStore[numberOfRelevantWords] = wordRead;
                            numberOfRelevantWords++;
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            wordList = new String[numberOfRelevantWords];

            for (int counter = 0; counter < numberOfRelevantWords; counter++) {
                wordList[counter] = wordStore[counter];
            }

            try {
                fileToBeRead.close();
            } catch (IOException ex) {
                Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
            }

            return wordList;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
