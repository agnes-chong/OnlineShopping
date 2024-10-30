package onlineshopping;

public class Commodity {
    
    public Commodity() {
    }
    
    private int codeId;
    private String name;
    private double pricePerUnit; // Store price per unit
    private int amount;
    private double total;

    public Commodity(int codeId, String name, double pricePerUnit, int amount) {
        this.codeId = codeId;
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.amount = amount;
        this.total = pricePerUnit * amount;  // Calculate total at the time of creation
    }

    public int getCodeId() {
        return codeId;
    }

    public String getName() {
        return name;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotal() {
        return total;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        this.total = pricePerUnit * amount;  // Recalculate the total when the amount is updated
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "codeId=" + codeId +
                ", name='" + name + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", amount=" + amount +
                ", total=" + total +
                '}';
    }
}