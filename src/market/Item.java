package market;

public class Item {
    private int itemId;
    private final String name;
    private double basePrice;
    private double currentPrice;
    private int quantityAvailable;
    private double weight;

    public Item(String name, double basePrice, int quantityAvailable, double weight) {
        this.itemId = 0;
        this.name = name;
        this.basePrice = basePrice;
        this.currentPrice = basePrice;
        this.quantityAvailable = quantityAvailable;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }

    public void setItemId(int newId) {
        itemId = newId;
    }

    public int getItemId() {
        return itemId;
    }

}
