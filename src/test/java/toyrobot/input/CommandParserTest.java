package toyrobot.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toyrobot.domain.robot.Direction;
import toyrobot.service.RobotService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class CommandParserTest {

    private RobotService robotService;
    private CommandParser parser;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        robotService = mock(RobotService.class);
        parser = new CommandParser(robotService);
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testMoveCommand() {
        parser.executeCommand("MOVE");
        verify(robotService, times(1)).move();
    }

    @Test
    void testLeftRightCommands() {
        parser.executeCommand("LEFT");
        parser.executeCommand("RIGHT");

        verify(robotService, times(1)).left();
        verify(robotService, times(1)).right();
    }

    @Test
    void testReportCommand() {
        when(robotService.report()).thenReturn("1,2,NORTH");
        parser.executeCommand("REPORT");

        verify(robotService, times(1)).report();
        assertTrue(outContent.toString().contains("1,2,NORTH"), "Expected output to contain '1,2,NORTH', but was:\n" + outContent.toString());
    }

    @Test
    void testPlaceCommandValid() {
        parser.executeCommand("PLACE 1,2,EAST");

        verify(robotService, times(1)).place(1, 2, Direction.EAST);
    }

    @Test
    void testPlaceCommandInvalidFormat() {
        parser.executeCommand("PLACE 1,2"); // missing direction

        verify(robotService, never()).place(anyInt(), anyInt(), any(Direction.class));
        assertEquals("", outContent.toString()); // silently ignored
    }

    @Test
    void testPlaceCommandInvalidDirection() {
        parser.executeCommand("PLACE 1,2,UP");

        verify(robotService, never()).place(anyInt(), anyInt(), any(Direction.class));

        assertTrue(
                outContent.toString().contains("Invalid PLACE command: PLACE 1,2,UP"),
                "Expected output to contain 'Invalid PLACE command: PLACE 1,2,UP', but was:\n" + outContent.toString()
        );
    }

    @Test
    void testInvalidCommand() {
        parser.executeCommand("JUMP");

        assertTrue(
                outContent.toString().contains("Invalid command: JUMP"),
                "Expected output to contain 'Invalid command: JUMP', but was:\n" + outContent.toString()
        );

        verifyNoInteractions(robotService);
    }

    @Test
    void testBlankCommandIgnored() {
        parser.executeCommand("   "); // just spaces

        verifyNoInteractions(robotService);
        assertEquals("", outContent.toString());
    }
}