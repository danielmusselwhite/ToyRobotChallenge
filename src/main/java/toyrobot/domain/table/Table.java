package toyrobot.domain.table;

import toyrobot.domain.robot.Position;

public record Table(int width, int height) {
    public Table {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Table dimensions must be positive");
        }
    }

    /**
     * Method to ensure position is within the bounds of the table
     * @param pos The Position we are checking
     * @return True if position is withhin the Tables bounds
     */
    public boolean isValid(Position pos) {
        return pos.x() >= 0 && pos.x() < width &&
                pos.y() >= 0 && pos.y() < height;
    }
}