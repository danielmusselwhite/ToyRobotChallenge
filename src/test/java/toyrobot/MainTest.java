package toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    @Test
    void sampleTest() {
        assertEquals(5,Main.add(2,3));
    }
}
