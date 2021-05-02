package kata.marsrover;

import kata.marsrover.things.Stone;
import kata.marsrover.things.Tree;

public class ThingsFactory {
    public ThingsFactory() {
    }

    static Rover.ThingAt stoneAtPositionOne() {
        return new Rover.ThingAt(new Stone(), new Position(0, 1));
    }

    static Rover.ThingAt stoneAtPositionSeven() {
        return new Rover.ThingAt(new Stone(), new Position(0, 7));
    }

    static Rover.ThingAt treeAtPositionTwo() {
        return new Rover.ThingAt(new Tree(), new Position(0, 2));
    }
}