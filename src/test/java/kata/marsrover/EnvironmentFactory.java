package kata.marsrover;

import kata.marsrover.Rover.ThingAt;

import java.util.List;

import static java.util.Collections.singletonList;

public class EnvironmentFactory {

    public static Planet mars() {
        return Planet.builder()
                .name("Mars")
                .height(100)
                .width(100)
                .build();
    }

    public static Planet marsWithThings(List<ThingAt> thingsAt) {
        final Planet mars = mars();
        thingsAt.forEach(mars::addThingAt);
        return mars;
    }

    public static Planet marsWithThing(ThingAt thingsAt) {
        return marsWithThings(singletonList(thingsAt));
    }
}
