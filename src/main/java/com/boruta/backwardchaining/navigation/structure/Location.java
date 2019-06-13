package com.boruta.backwardchaining.navigation.structure;

public class Location {

    public Location(String thing, String location) {
        this.thing = thing;
        this.location = location;
    }

    @org.kie.api.definition.type.Position(0)
    private String thing;
    @org.kie.api.definition.type.Position(1)
    private String location;

    public String getThing() {
        return thing;
    }

    public String getLocation() {
        return location;
    }

    public void powiedz(String f) {
        System.out.println(f);
    }
}
