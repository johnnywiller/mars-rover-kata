package kata.marsrover;

import kata.marsrover.Rover.ThingAt;
import kata.marsrover.Rover.WalkReport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static kata.marsrover.EnvironmentFactory.mars;
import static kata.marsrover.EnvironmentFactory.marsWithThing;
import static kata.marsrover.EnvironmentFactory.marsWithThings;
import static kata.marsrover.Rover.newRoverInPosition;
import static kata.marsrover.Rover.newRoverInZeroPosition;
import static kata.marsrover.ThingsFactory.stoneAtPositionOne;
import static kata.marsrover.ThingsFactory.stoneAtPositionSeven;
import static kata.marsrover.ThingsFactory.treeAtPositionTwo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RoverTest {

    @Test
    void shouldMoveForward() {
        // given a rover in the default position
        Rover rover = newRoverInZeroPosition(mars());
        Position initialPosition = rover.getPosition();

        // when moving forward one step
        rover.moveForward(1);

        // the final position should be incremented by one
        assertThat(isInfrontBySteps(1, rover.getPosition(), initialPosition)).isTrue();
    }

    @Test
    void shouldMoveBackwards() {
        // given a rover in the default position
        Rover rover = newRoverInZeroPosition(mars());
        final Position initialPosition = rover.getPosition();

        // when moving forward one step
        rover.moveBackward(1);

        // the final position should be incremented by one
        assertTrue(isBehindBySteps(1, rover.getPosition(), initialPosition));
    }

    @Test
    void shouldMoveToLeft() {
        // given a rover in the default position
        Rover rover = newRoverInZeroPosition(mars());
        final Position initialPosition = rover.getPosition();

        // when moving forward one step
        rover.moveLeft(1);

        // the final position should be incremented by one
        assertTrue(isToTheLeftBySteps(1, rover.getPosition(), initialPosition));
    }

    @Test
    void shouldMoveToRight() {
        // given a rover in the default position
        Rover rover = newRoverInZeroPosition(mars());
        Position initialPosition = rover.getPosition();

        // when moving to the right one step
        rover.moveRight(1);

        // the final position should be incremented by one
        assertTrue(isToTheRightBySteps(1, rover.getPosition(), initialPosition));
    }

    @Test
    void shouldNotWalkUponStones() {
        // given a stone is in position seven and the rover is in position zero
        final ThingAt stone = stoneAtPositionSeven();
        Planet mars = marsWithThing(stone);
        final Rover rover = newRoverInZeroPosition(mars);

        // when the rover tries to walk ten positions forward
        rover.moveForward(10);

        // then journey not concluded and the stone is reported at position seven
        final WalkReport walkReport = rover.getLastWalkReport();
        assertThat(walkReport.concluded()).isFalse();
        assertThat(walkReport.thingAt()).isEqualTo(stone);

        // then the last position is before stone
        assertThat(rover.getPosition()).isEqualTo(stone.position().down());
    }

    @Test
    void shouldReportTheCorrectObstacle() {
        // given a planet with a stone and a tree and a rover in front of the tree
        final ThingAt tree = treeAtPositionTwo();
        final Planet mars = marsWithThings(List.of(stoneAtPositionOne(), tree));
        final Rover rover = newRoverInPosition(new Position(0, 3), mars);

        // when the rover tries to walk upon the tree, by walking backwards
        rover.moveBackward(1);

        // then tree is reported
        final WalkReport walkReport = rover.getLastWalkReport();
        assertThat(walkReport.thingAt()).isEqualTo(tree);
    }

    private boolean isToTheLeftBySteps(int steps, Position position, Position other) {
        return isInSameY(position, other) && position.x() == other.x() - steps;
    }

    private boolean isToTheRightBySteps(int steps, Position position, Position other) {
        return isInSameY(position, other) && position.x() == other.x() + steps;
    }

    private boolean isInfrontBySteps(int steps, Position position, Position other) {
        return isInSameX(position, other) && position.y() == other.y() + steps;
    }

    private boolean isBehindBySteps(int steps, Position position, Position other) {
        return isInSameX(position, other) && position.y() == other.y() - steps;
    }

    private boolean isInSameX(Position position, Position other) {
        return position.x() == other.x();
    }

    private boolean isInSameY(Position position, Position other) {
        return position.y() == other.y();
    }
}
