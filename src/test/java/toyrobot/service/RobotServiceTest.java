package toyrobot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import toyrobot.domain.robot.Direction;
import toyrobot.domain.table.Table;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RobotServiceTest {

    private Table table;
    private RobotService service;

    @BeforeEach
    void setUp() {
        table = new Table(5, 5);
        service = new RobotService(table);
    }

    @ParameterizedTest(name = "PLACE {0},{1},{2} → MOVE → REPORT = {3},{4},{5}")
    @CsvSource({
            "0,0,NORTH,0,2,WEST",
            "0,0,EAST,2,1,NORTH",
            "1,2,EAST,3,3,NORTH" // corresponds to PLACE 1,2,EAST -> MOVE -> MOVE -> LEFT -> MOVE -> REPORT
    })
    void testMoves(int placeX, int placeY, Direction facing,
                   int expectedX, int expectedY, Direction expectedFacing) {
        // Place the robot
        service.place(placeX, placeY, facing);

        // Move the robot twice, then turn left and move once
        service.move();
        service.move();
        service.left();
        service.move();

        String expected = expectedX + "," + expectedY + "," + expectedFacing;
        assertEquals(expected, service.report());
    }

    @Test
    void testRotations() {
        service.place(0, 0, Direction.NORTH);
        service.left();
        assertEquals("0,0,WEST", service.report());

        service.right();
        assertEquals("0,0,NORTH", service.report());

        service.right();
        assertEquals("0,0,EAST", service.report());
    }

    @Test
    void testBoundaryMovesIgnored() {
        service.place(0, 0, Direction.SOUTH);
        service.move(); // would go off table, so don't move
        assertEquals("0,0,SOUTH", service.report());

        service.place(0, 0, Direction.WEST);
        service.move(); // would go off table, so don't move
        assertEquals("0,0,WEST", service.report());

        service.place(4, 4, Direction.NORTH);
        service.move(); // would go off table, so don't move
        assertEquals("4,4,NORTH", service.report());

        service.place(4, 4, Direction.EAST);
        service.move(); // would go off table, so don't move
        assertEquals("4,4,EAST", service.report());
    }

    @Test
    void testMultiplePlaceCommands() {
        service.place(0, 0, Direction.NORTH);
        service.move();
        assertEquals("0,1,NORTH", service.report());

        // Override with new PLACE
        service.place(2, 2, Direction.EAST);
        assertEquals("2,2,EAST", service.report());
    }

    @Test
    void testCommandsIgnoredBeforePlace() {
        assertNull(service.report());
        service.move();
        service.left();
        service.right();
        assertNull(service.report()); // still ignored
    }
}