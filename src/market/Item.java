package market;

public class Item {
    private int itemId;
    private final String name;
    private double basePrice;
    private double currentPrice;
    private int quantityAvailable;
    private int baseQuantity;
    private double weight;

    public Item(String name, double basePrice, int quantityAvailable, double weight) {
        this.itemId = 0;
        this.name = name;
        this.basePrice = basePrice;
        this.currentPrice = basePrice;
        this.quantityAvailable = quantityAvailable;
        this.baseQuantity = quantityAvailable;
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

    public void updateCurrentPrice() {
        double randomPercentage = (Math.random() * 40 - 20) / 100.0;
        currentPrice = Math.floor(basePrice * (1 + randomPercentage));
    }

    public void updateQuantityAvailable() {
        quantityAvailable = baseQuantity - (int) (Math.random() * baseQuantity);
    }

    public int getBaseQuantity() {
        return baseQuantity;
    }

    public double getPriceChangePercentage() {
        return Math.round((currentPrice - basePrice) / basePrice * 100);
    }

    public void setQuantityAvailable(int quantity) {
        quantityAvailable = quantity;
    }
}
