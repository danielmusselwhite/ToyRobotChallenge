package toyrobot.domain.robot;

public record Position(int x, int y) {
    public Position move(Direction dir){
        return switch(dir){
            case NORTH -> new Position(x, y+1);
            case EAST -> new Position(x+1, y);
            case SOUTH -> new Position(x, y-1);
            case WEST -> new Position(x-1, y);
        };
    }
}
