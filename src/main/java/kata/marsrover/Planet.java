package kata.marsrover;

import lombok.Builder;

import java.util.HashMap;
import java.util.Map;

@Builder
public class Planet implements Environment {
    final String name;
    final int width;
    final int height;

    @Builder.Default
    Map<Position, Thing> thingsAt = new HashMap<>();

    public void addThingAt(Rover.ThingAt thingAt) {
        thingsAt.put(thingAt.position(), thingAt.thing());
    }

    @Override
    public Thing queryPosition(Position position) {
        return thingsAt.get(position);
    }
}
