package world;

import java.util.ArrayList;

import market.Item;

public class Planet {
    String name;
    ArrayList<Item> marketItems;

    public Planet(String name) {
        this.name = name;
        this.marketItems = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "---------- Planet ----------" +
                "\nName: " + name;
    }

}
