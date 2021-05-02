package kata.marsrover;

record Position(int x, int y) {

    record Distance(int x, int y) {}

    public Distance distanceFrom(Position other) {
        final int xDistance = Math.abs(x - other.x());
        final int yDistance = Math.abs(y - other.y());
        return new Distance(xDistance, yDistance);
    }

    public Position right(int amount) {
        return new Position(x + amount, y);
    }

    public Position left(int amount) {
        return new Position(x - amount, y);
    }

    public Position down(int amount) {
        return new Position(x, y - amount);
    }

    public Position up(int amount) {
        return new Position(x, y + amount);
    }

    public Position up() {
        return up(1);
    }

    public Position down() {
        return down(1);
    }

    public Position left() {
        return left(1);
    }

    public Position right() {
        return right(1);
    }
}