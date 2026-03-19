package toyrobot.domain.robot;

import toyrobot.domain.table.Table;

public class Robot {
    private Position position;
    private Direction facing;

    public Robot(Position position, Direction facing) {
        this.position = position;
        this.facing = facing;
    }

    // Behavior methods
    public void turnLeft() {
        facing = facing.left();
    }

    public void turnRight() {
        facing = facing.right();
    }

    public void move() {
        position = position.move(facing);
    }

    // Get state (for reporting)
    public Position getPosition() {
        return position;
    }

    public Direction getFacing() {
        return facing;
    }
}
