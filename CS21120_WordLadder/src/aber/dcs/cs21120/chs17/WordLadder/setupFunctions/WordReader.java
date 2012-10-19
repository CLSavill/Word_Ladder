/**
 * @author Chris Savill - chs17
 * @title Class that contains various methods to read words from a file
 */
package aber.dcs.cs21120.chs17.WordLadder.setupFunctions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

//////////////////////// Constructors ///////////////////////////

public class WordReader {

    public LinkedList<String> readWords(int wordLength) {
        int numberOfWords = 0;
        int numberOfRelevantWords = 0;
        String wordRead;
        boolean wordAlreadyStored;

        try {
            BufferedReader fileToBeRead = new BufferedReader(new FileReader("WordsOfLength" + wordLength + ".dat"));

            while (fileToBeRead.readLine() != null) {
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
                        wordStore.add(fileToBeRead.readLine());
                    } catch (IOException ex) {
                        Logger.getLogger(WordReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    wordAlreadyStored = false;
                    try {
                        wordRead = fileToBeRead.readLine();
                        if (wordRead.length() == wordLength) {
                            for (int counter2 = 0; counter2 < numberOfRelevantWords || wordAlreadyStored == true; counter2++) {
                                if (wordStore.get(counter2).equals(wordRead)) {
                                    wordAlreadyStored = true;
                                }
                            }
                            if (wordAlreadyStored == false) {
                                wordStore.add(wordRead);
                                numberOfRelevantWords++;
                            }
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
