package toyrobot.service;

import toyrobot.domain.robot.Direction;
import toyrobot.domain.robot.Position;
import toyrobot.domain.robot.Robot;
import toyrobot.domain.table.Table;

public class RobotService {

    private Robot robot; // null until first PLACE!
    private final Table table;

    public RobotService(Table table) {
        this.table = table;
    }


    public void place(int x, int y, Direction facing) {
        Position pos = new Position(x, y);
        if (!table.isValid(pos)) return;
        robot = new Robot(pos, facing); // reinstantiate robot in new location
    }

    public void move() {
        if (robot == null) return;

        // only allow the robot to move if they will stay within the table
        Position next = robot.getPosition().move(robot.getFacing());
        if (table.isValid(next)) {
            robot.move();  // Move the robot
        }
    }

    public void left() {
        if (robot == null) return;
        robot.turnLeft();
    }

    public void right() {
        if (robot == null) return;
        robot.turnRight();
    }

    // Return the "X,Y,F" as specified in brief
    public String report() {
        if (robot == null) return null;
        Position pos = robot.getPosition();
        Direction dir = robot.getFacing();
        return pos.x() + "," + pos.y() + "," + dir;
    }

    // if robot exists, it has been placed
    public boolean isPlaced() {
        return robot != null;
    }
}