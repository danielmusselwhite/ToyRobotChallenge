package toyrobot.domain.robot;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectionTest {
    @ParameterizedTest(name = "{0}.right() should be {1}") // so if test fails it displays name and not row number
    @CsvSource({
            "NORTH,EAST",
            "EAST,SOUTH",
            "SOUTH,WEST",
            "WEST,NORTH"
    })
    void testsTurnRight(Direction start, Direction expected) {
        assertEquals(expected, start.right(), "Unexpected Direction after turning right");
    }

    @ParameterizedTest(name = "{0}.left() should be {1}") // so if test fails it displays name and not row number
    @CsvSource({
            "NORTH,WEST",
            "WEST,SOUTH",
            "SOUTH,EAST",
            "EAST,NORTH"
    })
    void testsTurnLeft(Direction start, Direction expected) {
        assertEquals(expected, start.left(), "Unexpected Direction after turning left");
    }
}
