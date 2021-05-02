package kata.marsrover;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import static java.util.Objects.requireNonNull;

public class Rover implements EnvironmentAware {
    private final Environment environment;
    private Position position;
    private List<WalkReport> walkReports;

    private Rover(Position position, Environment environment) {
        this.position = position;
        this.environment = environment;
        this.walkReports = new LinkedList<>();
    }

    public static Rover newRoverInZeroPosition(Environment environment) {
        requireNonNull(environment);
        return new Rover(new Position(0, 0), environment);
    }

    public static Rover newRoverInPosition(Position position, Environment environment) {
        requireNonNull(environment);
        return new Rover(position, environment);
    }

    public void moveForward(int steps) {
        move(position.up(steps), Position::up);
    }

    public void moveBackward(int steps) {
        move(position.down(steps), Position::down);
    }

    public void moveLeft(int steps) {
        move(position.left(steps), Position::left);
    }

    public void moveRight(int steps) {
        move(position.right(steps), Position::right);
    }

    private void move(Position plannedPosition, UnaryOperator<Position> walker) {
        Position.Distance distance = position.distanceFrom(plannedPosition);
        int remainingSteps = Math.max(distance.x(), distance.y());

        while(remainingSteps-- > 0) {
            var nextPosition = walker.apply(position);
            var thing = detectThing(nextPosition);
            if (thing != null) {
                addFaillingReport(new ThingAt(thing, nextPosition));
                return;
            }
            position = nextPosition;
        }
        addSuccessfulReport();
    }

    public WalkReport getLastWalkReport() {
        return walkReports.get(walkReports.size() - 1);
    }

    public Position getPosition() {
        return position;
    }

    private void addSuccessfulReport() {
        walkReports.add(new WalkReport(true, null));
    }

    private void addFaillingReport(ThingAt thingAt) {
        walkReports.add(new WalkReport(false, thingAt));
    }

    private Thing detectThing(Position plannedPosition) {
        return environment.queryPosition(plannedPosition);
    }

    record ThingAt(Thing thing, Position position) {
    }

    record WalkReport(boolean concluded, ThingAt thingAt) {
    }
}
