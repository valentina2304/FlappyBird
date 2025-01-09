package models;

import java.awt.event.ActionEvent;

public class GameUpdateEvent extends ActionEvent {
    private final double deltaTime;

    public GameUpdateEvent(Object source, int id, double deltaTime) {
        super(source, id, null);
        this.deltaTime = deltaTime;
    }

    public double getDeltaTime() {
        return deltaTime;
    }
}
