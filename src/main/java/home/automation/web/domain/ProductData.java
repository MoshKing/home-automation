package home.automation.web.domain;

public class ProductData {

    private String name;
    private int qty;
    private String size;
    private String itemPrice;
    private String sku;
    private String color;
    private String total;


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTotal() {
        total = new StringBuilder("$")
                .append(Double.parseDouble(itemPrice.replaceAll(ProjectConstants.REPLACE_DOLLAR, "")) * qty)
                .toString();
        return total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}