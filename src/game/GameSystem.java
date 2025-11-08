package game;

import java.util.Map;
import java.util.Scanner;

import entities.Player;
import market.Item;
import utils.Tools;
import world.Planet;

public class GameSystem {
    private static final int EARTH_INDEX = 0;
    private static final int MARS_INDEX = 1;
    private static final int JUPITER_INDEX = 2;
    private Player player;
    private Planet currentPlanet;
    private Planet[] planets;
    private int currentPlanetIndex;
    private Scanner input = new Scanner(System.in);

    public void start() {
        promptWelcomeSequence();

        while (true) {
            promptMenu();
        }
    }

    public GameSystem() {
        this.player = null;
        this.planets = getPlanets();
        this.currentPlanetIndex = 0;
        this.currentPlanet = planets[currentPlanetIndex];
    }

    private Planet[] getPlanets() {
        return new Planet[] {
                new Planet("Earth"),
                new Planet("Mars"),
                new Planet("Jupiter")
        };
    }

    private void promptWelcomeSequence() {
        Tools.printToConsole("==================== Welcome to the Intergalactic Trade Simulator! ====================",
                true);
        Tools.printToConsole(
                "In this game, you will explore different planets, trade goods, and manage your spaceship.", false);
        Tools.printToConsole("\nWhat is your name, Captain?");

        String name = Tools.validateName(input, "My name is");
        this.player = new Player(name);

        Tools.printToConsole("\nWelcome aboard, Captain " + player.getName() + "!");
        Tools.printToConsole("You start your journey with " + player.getCredits() + " credits and a basic spaceship.");
        Tools.waitForUser(input);
    }

    private void promptMenu() {
        Tools.titlePrinter("Intergalactic Trade Simulator", true);
        Tools.printToConsole("""
                1. View Ship Status
                2. View Current Planet

                3. Open Market (Buy Items)
                4. Open Market (Sell Items)

                5. Travel to Another Planet
                6. Next day (Coming Soon)

                0. Exit Game
                """);

        int choice = Tools.validateInt(input, "Choose an option");
        switch (choice) {
            case 1 -> viewShipStatus();
            case 2 -> viewCurrentPlanet();
            case 3 -> promptMarketBuy();
            case 4 -> promptMarketSell();
            case 5 -> promptTravelToAnotherPlanet();
            case 6 -> promptNextDay();
            case 0 -> {
                Tools.printToConsole("Thank you for playing! Safe travels, Captain " + player.getName() + "!");
                System.exit(0);
            }
            default -> Tools.printToConsole("Invalid choice. Please try again.");
        }

    }

    private void promptNextDay() {
        Tools.printToConsole(Tools.RED + "Coming Soon" + Tools.RESET);
        Tools.waitForUser(input);
    }

    private void promptTravelToAnotherPlanet() {
        Tools.printToConsole("""
                1. Earth
                2. Mars
                3. Jupiter
                """, true);
        int choice = Tools.validateInt(input, "Choose a planet to travel to");
        switch (choice) {
            case 1 -> currentPlanet = planets[EARTH_INDEX];
            case 2 -> currentPlanet = planets[MARS_INDEX];
            case 3 -> currentPlanet = planets[JUPITER_INDEX];
            default -> Tools.printToConsole("Invalid choice. Please try again.");
        }
        Tools.printToConsole("You have arrived at " + currentPlanet.getName() + "!");
        Tools.waitForUser(input);
    }

    private void promptMarketSell() {
        Tools.clearConsole();
        Tools.printToConsole("Balance: $" + player.getCredits());
        for (Map.Entry<Item, Integer> entry : player.getShip().getCargo().entrySet()) {
            currentPlanet.printItem(entry.getKey(), player);
        }
        int itemId = Tools.validateInt(input, "Enter the ID of the item you want to sell");
        Item item = (Item) currentPlanet.getMarketItemById(itemId);
        if (item == null) {
            Tools.printToConsole("Invalid item ID. Please try again.");
            Tools.waitForUser(input);
            return;
        }
        int itemAmount = Tools.validateInt(input, "Enter the amount of " + item.getName() + " you want to sell");
        sellItem(item, itemAmount);
    }

    private void sellItem(Item item, int itemAmount) {
        Integer cargoAmount = player.getShip().getCargo().get(item);
        if (cargoAmount == null || itemAmount > cargoAmount) {
            Tools.printToConsole("You don't have enough " + item.getName() + " in your cargo to sell " + itemAmount
                    + ". Please try again.", true);
            Tools.waitForUser(input);
            return;
        }
        player.sellItems(item, itemAmount);
        player.getShip().removeItemsFromCargo(item, itemAmount);
        currentPlanet.addItemsToMarket(item, itemAmount);
    }

    private void promptMarketBuy() {
        Tools.clearConsole();
        Tools.printToConsole("Balance: $" + player.getCredits());
        currentPlanet.printMarket();

        int itemId = Tools.validateInt(input, "Enter the ID of the item you want to buy");
        Item item = (Item) currentPlanet.getMarketItemById(itemId);
        if (item == null) {
            Tools.printToConsole("Invalid item ID. Please try again.");
            return;
        }

        int itemAmount = Tools.validateInt(input, "Enter the amount of " + item.getName() + " you want to buy");
        buyItem(item, itemAmount);
    }

    private void buyItem(Item item, int itemAmount) {
        if (player.getCredits() < (itemAmount * item.getCurrentPrice())) {
            Tools.printToConsole("You don't have enough credits to buy " + itemAmount + " " + item.getName()
                    + ". Please try again.", true);
            Tools.waitForUser(input);
            return;
        }
        if (player.getShip().getCurrentCargo() + itemAmount * item.getWeight() > player.getShip().getCargoCapacity()) {
            Tools.printToConsole("You don't have enough space in your cargo to add " + itemAmount + " " + item.getName()
                    + ". Please try again.", true);
            Tools.waitForUser(input);
            return;
        }
        if (item.getQuantityAvailable() < itemAmount) {
            Tools.printToConsole("There are only " + item.getQuantityAvailable() + " " + item.getName()
                    + " available. Please try again.", true);
            Tools.waitForUser(input);
            return;
        }

        player.buyItems(item, itemAmount);
        player.getShip().addItemsToCargo(item, itemAmount);
        currentPlanet.removeItemsFromMarket(item, itemAmount);

    }

    private void viewShipStatus() {
        Tools.printToConsole(player.getShip().toString(), true);
        Tools.waitForUser(input);
    }

    private void viewCurrentPlanet() {
        Tools.printToConsole(currentPlanet.toString(), true);
        Tools.waitForUser(input);
    }

}
