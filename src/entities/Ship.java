package entities;

import java.util.ArrayList;
import java.util.List;

import market.Item;

public class Ship {
    private final String name;
    private double fuelCapacity;
    private double currentFuel;
    private double cargoCapacity;
    private double currentCargo;
    private ArrayList<Item> cargo;

    public Ship(String name, double fuelCapacity, double cargoCapacity) {
        this.name = name;
        this.fuelCapacity = fuelCapacity;
        this.currentFuel = fuelCapacity;
        this.cargoCapacity = cargoCapacity;
        this.currentCargo = 0;
        this.cargo = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public double getCurrentCargo() {
        return currentCargo;
    }

    public List<Item> getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return "---------- Ship ----------" +
                "\nName: " + name +
                "\nFuel: " + "(" + currentFuel + " / " + fuelCapacity + ")" +
                "\nCargo: " + "(" + currentCargo + " / " + cargoCapacity + ")";
    }
}
