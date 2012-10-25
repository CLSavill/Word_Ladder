package aber.ac.uk.dcs.cs21120.chs17.WordLadder.dataStructureTests;

import aber.dcs.cs21120.chs17.WordLadder.dataStructures.WordReader;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Chris Savill - chs17
 * @title JUnit test class for the WordReader class
 */
public class WordReaderTest {

    @Test
    public void testReadWords() {
        WordReader testReader = new WordReader();
        assertNotNull("Null expected as file should fail to load", testReader.readWords(7));
        assertNotNull("Null expected as file should fail to load", testReader.readWords(6));
        assertNotNull("Null expected as file should fail to load", testReader.readWords(5));
        assertNotNull("Null expected as file should fail to load", testReader.readWords(4));
        assertNotNull("Null expected as file should fail to load", testReader.readWords(3));
        assertNotNull("Null expected as file should fail to load", testReader.readWords(2));
        assertNotNull("Null expected as file should fail to load", testReader.readWords(1));      
    }
}
