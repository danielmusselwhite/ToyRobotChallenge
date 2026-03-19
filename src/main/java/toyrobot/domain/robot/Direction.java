package toyrobot.domain.robot;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    /**
     *
     * @return the new Direction after turning 90 degrees left
     */
    public Direction left() {
        return switch(this){
            case NORTH -> WEST;
            case WEST -> SOUTH;
            case SOUTH -> EAST;
            case EAST -> NORTH;
        };
    }

    /**
     *
     * @return the new Direction after turning 90 degrees right
     */
    public Direction right() {
        return switch(this){
            case WEST -> NORTH;
            case SOUTH -> WEST;
            case EAST -> SOUTH;
            case NORTH -> EAST;
        };
    }
}