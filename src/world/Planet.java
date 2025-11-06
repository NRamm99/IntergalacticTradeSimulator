package world;

import java.util.ArrayList;
import java.util.List;

import market.Item;
import utils.Tools;

public class Planet {
    private static final int FULL_MARKET_SIZE = 5;
    String name;
    ArrayList<Item> marketItems;

    public Planet(String name) {
        this.name = name;
        this.marketItems = updateMarket();
    }

    private ArrayList<Item> updateMarket() {
        ArrayList<Item> items = new ArrayList<>();

        while (true) {
            Item newItem = randomItem();

            if (items.contains(newItem)) {
                continue;
            }

            items.add(newItem);

            if (items.size() >= FULL_MARKET_SIZE) {
                return items;
            }
        }

    }

    private Item randomItem() {
        ArrayList<Item> possibleItems = getPossibleItems();
        int index = (int) (Math.random() * possibleItems.size());
        return possibleItems.get(index);
    }

    public String getName() {
        return name;
    }

    private ArrayList<Item> getPossibleItems() {
        ArrayList<Item> possibleItems = new ArrayList<>();
        possibleItems.add(new Item("Food", 10.0, 100));
        possibleItems.add(new Item("Water", 5.0, 200));
        possibleItems.add(new Item("Ore", 20.0, 50));
        possibleItems.add(new Item("Medicine", 50.0, 30));
        possibleItems.add(new Item("Electronics", 100.0, 20));
        possibleItems.add(new Item("Fuel Cells", 75.0, 60));
        possibleItems.add(new Item("Textiles", 15.0, 120));
        possibleItems.add(new Item("Luxury Goods", 250.0, 10));
        possibleItems.add(new Item("Microchips", 180.0, 25));
        possibleItems.add(new Item("Alien Artifacts", 500.0, 5));
        possibleItems.add(new Item("Weapons", 300.0, 15));
        possibleItems.add(new Item("Spare Parts", 45.0, 70));
        possibleItems.add(new Item("Data Crystals", 90.0, 40));
        possibleItems.add(new Item("Robotics Components", 220.0, 18));
        possibleItems.add(new Item("Nano-Fibers", 130.0, 35));
        possibleItems.add(new Item("Holographic Modules", 160.0, 28));
        possibleItems.add(new Item("Quantum Batteries", 400.0, 8));
        possibleItems.add(new Item("Terraforming Tools", 350.0, 12));
        possibleItems.add(new Item("Rare Minerals", 280.0, 20));
        possibleItems.add(new Item("Plasma Coils", 150.0, 25));
        return possibleItems;
    }

    public List<Item> getMarketItems() {
        return marketItems;
    }

    @Override
    public String toString() {
        return "---------- Planet ----------" +
                "\nName: " + name;
    }

    public void printMarket() {
        Tools.printToConsole("-------------------- Market on " + name + " -------------------", true);
        for (Item item : marketItems) {
            Tools.printToConsole(
                    "- " + item.getName() + " | Price: " + item.getCurrentPrice() + " | Available: "
                            + item.getQuantityAvailable());
        }

    }

}
