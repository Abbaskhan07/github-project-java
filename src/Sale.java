public class Sale {

    private String saleName;

    public Sale(String saleName) {
        this.saleName = saleName;
    }

    public void showProduct(Product product) {
        System.out.println(product);
    }

    public String getSaleName() {
        return saleName;
    }
}
