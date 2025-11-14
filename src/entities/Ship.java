package entities;

import java.util.HashMap;
import java.util.Map;

import market.Item;

public class Ship {
    private final String name;
    private double fuelCapacity;
    private double currentFuel;
    private double cargoCapacity;
    private double currentCargo;
    private HashMap<Item, Integer> cargo;

    public Ship(String name, double fuelCapacity, double cargoCapacity) {
        this.name = name;
        this.fuelCapacity = fuelCapacity;
        this.currentFuel = fuelCapacity;
        this.cargoCapacity = cargoCapacity;
        this.currentCargo = 0;
        this.cargo = new HashMap<>();
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

    public Map<Item, Integer> getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return "---------- Ship ----------" +
                "\nName: " + name +
                "\nFuel: " + "(" + currentFuel + " / " + fuelCapacity + ")" +
                "\nCargo: " + "(" + currentCargo + "kg / " + cargoCapacity + "kg)";
    }

    public void addItemsToCargo(Item item, int itemAmount) {
        cargo.put(item, cargo.getOrDefault(item, 0) + itemAmount);
        currentCargo += (itemAmount * item.getWeight());
    }

    public void removeItemsFromCargo(Item item, int itemAmount) {
        Integer currentAmount = cargo.get(item);
        if (currentAmount != null) {
            int newAmount = currentAmount - itemAmount;
            currentCargo -= (itemAmount * item.getWeight());
            if (newAmount <= 0) {
                cargo.remove(item);
            } else {
                cargo.put(item, newAmount);
            }
        }
    }

    public void consumeFuel(int amount) {
        currentFuel -= amount;
    }

    public boolean hasEnoughFuel(int amount) {
        return currentFuel >= amount;
    }
}
