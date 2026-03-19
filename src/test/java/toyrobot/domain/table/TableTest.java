package toyrobot.domain.table;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import toyrobot.domain.robot.Position;

import static org.junit.jupiter.api.Assertions.*;

public class TableTest {

    @ParameterizedTest(name = "Invalid table creation with width={0}, height={1} should throw")
    @CsvSource({
            "0,0",
            "-1,-1",
            "2,0",
            "0,2"
    })
    void testInvalidTableCreation(int width, int height) {
        assertThrows(IllegalArgumentException.class, () -> new Table(width, height));
    }

//
    @Test
    void testValidTableCreation(){
        var tab = new Table(1, 2);
        assertEquals(1, tab.width());
        assertEquals(2, tab.height());
    }

    @ParameterizedTest(name = "Table {0}x{1}: Position ({2},{3}) should be valid")
    @CsvSource({
            "2,3,0,0",
            "2,3,1,2"
    })
    void testsValidPos(int width, int height, int x, int y) {
        var tab = new Table(width, height);
        var pos = new Position(x, y);

        assertTrue(tab.isValid(pos));
    }

    @ParameterizedTest(name = "Table {0}x{1}: Position ({2},{3}) should be invalid")
    @CsvSource({
            "2,3,-1,0",
            "2,3,0,-1",
            "2,3,2,2",
            "2,3,1,3"
    })
    void testsInvalidPos(int width, int height, int x, int y) {
        var tab = new Table(width, height);
        var pos = new Position(x, y);

        assertFalse(tab.isValid(pos));
    }
}
