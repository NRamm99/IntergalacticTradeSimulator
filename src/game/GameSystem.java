package game;

import java.util.Scanner;

import entities.Player;
import market.Item;
import utils.Tools;
import world.Planet;

public class GameSystem {
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
                3. Open Market (Coming Soon)
                4. Travel to Another Planet (Coming Soon)
                5. Next day (Coming Soon)

                0. Exit Game
                """);

        int choice = Tools.validateInt(input, "Choose an option");
        switch (choice) {
            case 1 -> viewShipStatus();
            case 2 -> viewCurrentPlanet();
            case 3 -> promptMarket();
            case 0 -> {
                Tools.printToConsole("Thank you for playing! Safe travels, Captain " + player.getName() + "!");
                System.exit(0);
            }
            default -> Tools.printToConsole("Invalid choice. Please try again.");
        }

    }

    private void promptMarket() {
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
        if (itemAmount > item.getQuantityAvailable()) {
            Tools.printToConsole("You can't buy more than " + item.getQuantityAvailable() + " " + item.getName()
                    + " in stock. Please try again.");
            return;
        }
        buyItem(item, itemAmount);

        Tools.waitForUser(input);
    }

    private void buyItem(Item item, int itemAmount) {
        if (player.getCredits() < (itemAmount * item.getCurrentPrice())) {
            Tools.printToConsole("You don't have enough credits to buy " + itemAmount + " " + item.getName()
                    + ". Please try again.");
            return;
        }
        if (player.getShip().getCurrentCargo() + itemAmount * item.getWeight() > player.getShip().getCargoCapacity()) {
            Tools.printToConsole("You don't have enough space in your cargo to add " + itemAmount + " " + item.getName()
                    + ". Please try again.");
            return;
        }

        player.buyItems(item, itemAmount);
        player.getShip().addItemsToCargo(item, itemAmount);

    }

    private void viewShipStatus() {
        Tools.printToConsole(player.getShip().toString(), true);
        Tools.waitForUser(input);
    }

    private void viewCurrentPlanet() {
        Tools.printToConsole(planets[currentPlanetIndex].toString(), true);
        Tools.waitForUser(input);
    }

}
