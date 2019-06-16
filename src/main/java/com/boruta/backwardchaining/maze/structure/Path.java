package com.boruta.backwardchaining.maze.structure;

import com.boruta.backwardchaining.navigation.structure.Position;

public class Path {
    @org.kie.api.definition.type.Position(0)
    private Position position;
    @org.kie.api.definition.type.Position(1)
    private Position target;

    public Path(Position position, Position target) {
        this.position = position;
        this.target = target;
    }

    public Position getPosition() {
        return position;
    }

}
