package entities;

import market.Item;

public class Player {
    private String name;
    private double credits;
    private Ship ship;

    public Player(String name) {
        this.name = name;
        this.credits = 500.0;
        this.ship = new Ship("Basic Shuttle", 100.0, 50.0);
    }

    public String getName() {
        return name;
    }

    public double getCredits() {
        return credits;
    }

    public Ship getShip() {
        return ship;
    }

    public void buyItems(Item item, int itemAmount) {
        credits -= (itemAmount * item.getCurrentPrice());
    }

    public void sellItems(Item item, int itemAmount) {
        credits += (itemAmount * item.getCurrentPrice());
    }

}
