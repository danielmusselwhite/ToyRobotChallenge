package toyrobot.domain.robot;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @ParameterizedTest(name = "Starting at ({0},{1}) moving {2} results in ({3},{4})")
    @CsvSource({
            "0,0,NORTH,0,1",
            "0,0,EAST,1,0",
            "0,0,SOUTH,0,-1",
            "0,0,WEST,-1,0",
            "3,3,NORTH,3,4",
            "3,3,EAST,4,3",
            "3,3,SOUTH,3,2",
            "3,3,WEST,2,3"
    })
    void testMove(int startX, int startY, Direction dir, int expectedX, int expectedY) {
        Position pos = new Position(startX, startY);
        Position newPos = pos.move(dir);

        assertEquals(expectedX, newPos.x(), "X coordinate mismatch after move");
        assertEquals(expectedY, newPos.y(), "Y coordinate mismatch after move");
    }
}
