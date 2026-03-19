package toyrobot.domain.robot;

import toyrobot.domain.table.Table;

public class Robot {
    private Position _pos;
    private Direction _facing;
    private Table _table;
    private boolean _isPlaced = false;

    public Robot(Table _table)
    {
    }
}
