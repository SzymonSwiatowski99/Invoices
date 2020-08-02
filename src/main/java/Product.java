public class Product {
    private String name;
    private int quantity;
    private double price;
    private double vat;
    public Product(String name, int quantity, double price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.vat = 0.23;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrize() {
        return price;
    }

    public void setPrize(double price) {
        this.price = price;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }
    public String toString(){
        return "nazwa: " + name + "    cena jednostkowa: " + price + "    ilosc: " + quantity + "    cena ogolna: " + ((double)Math.round(price*quantity*100))/100  + "    wysokosc vat: "
                + ((double)Math.round(quantity*price*vat*100))/100 + "    brutto: " + ((double)Math.round(price*quantity+quantity*price*vat*100))/100;
    }

}
