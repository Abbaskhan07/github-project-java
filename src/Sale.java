public class Sale {
    private int saleId;
    private String date;
    private double amount;

    public Sale(int saleId, String date, double amount) {
        this.saleId = saleId;
        this.date = date;
        this.amount = amount;
    }

    public void printReceipt() {
        System.out.println("🧾 Чек #" + saleId + " от " + date + " на сумму: " + amount + " KZT");
    }
}