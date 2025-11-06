package market;

public class Item {
    private final String name;
    private double basePrice;
    private double currentPrice;
    private int quantityAvailable;

    public Item(String name, double basePrice, int quantityAvailable) {
        this.name = name;
        this.basePrice = basePrice;
        this.currentPrice = basePrice;
        this.quantityAvailable = quantityAvailable;
    }

    public String getName() {
        return name;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public int getQuantityAvailable() {
        return quantityAvailable;
    }

}
