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
                int n = 0;
                for (Item item : items) {
                    n++;
                    item.setItemId(n);
                    item.updateCurrentPrice();
                }
                return items;
            }
        }

    }

    public Object getMarketItemById(int id) {
        for (Item item : marketItems) {
            if (item.getItemId() == id) {
                return item;
            }
        }
        return null;
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
        possibleItems.add(new Item("Water", 20, 10, 10));
        possibleItems.add(new Item("Food Rations", 35, 15, 8));
        possibleItems.add(new Item("Metal Ore", 120, 8, 25));
        possibleItems.add(new Item("Fuel Cells", 90, 12, 15));
        possibleItems.add(new Item("Medical Supplies", 150, 6, 5));
        possibleItems.add(new Item("Luxury Fabrics", 300, 5, 3));
        possibleItems.add(new Item("Alien Artifacts", 800, 2, 2));
        possibleItems.add(new Item("Plasma Batteries", 200, 7, 12));
        possibleItems.add(new Item("Nano Components", 400, 4, 6));
        possibleItems.add(new Item("Robot Parts", 250, 6, 18));
        possibleItems.add(new Item("Quantum Circuits", 600, 3, 4));
        possibleItems.add(new Item("Spice Crystals", 500, 5, 7));
        possibleItems.add(new Item("Rare Minerals", 700, 4, 10));
        possibleItems.add(new Item("Holo Entertainment Discs", 180, 9, 5));
        possibleItems.add(new Item("Weapon Components", 350, 5, 9));
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
        Tools.printToConsole("-------------------- Market on " + name + " -------------------");
        for (Item item : marketItems) {
            Tools.printToConsole(
                    "ID: " + item.getItemId() + " - " + item.getName() + " | Available: "
                            + item.getQuantityAvailable() + " | Price: $" + item.getCurrentPrice() + " "
                            + displayPriceChangePercentage(item));
        }
    }

    private String displayPriceChangePercentage(Item item) {
        double priceChangePercentage = item.getPriceChangePercentage();
        if (priceChangePercentage > 0) {
            return "(" + Tools.GREEN + "+" + priceChangePercentage + "%" + Tools.RESET + ")";
        } else {
            return "(" + Tools.RED + priceChangePercentage + "%" + Tools.RESET + ")";
        }
    }

}
