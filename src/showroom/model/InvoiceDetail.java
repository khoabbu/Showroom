// InvoiceDetail.java
package showroom.model;

public class InvoiceDetail {
    private double unitPrice;

public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
}

public double getUnitPrice() {
    return unitPrice;
}

    private int invoiceDetailId; //
    private int invoiceId; //
    private int carId; //
    private int quantity; //
    private double priceAtSale; //
    private Car car; // Thêm đối tượng Car

    // Constructors
    public InvoiceDetail() {} //

    public InvoiceDetail(int invoiceDetailId, int invoiceId, int carId, int quantity, double priceAtSale) {
        this.invoiceDetailId = invoiceDetailId;
        this.invoiceId = invoiceId;
        this.carId = carId;
        this.quantity = quantity;
        this.priceAtSale = priceAtSale;
    }

    // Getters, and Setters (các getter/setter hiện có vẫn giữ nguyên, thêm các getter/setter mới)

    public int getInvoiceDetailId() { return invoiceDetailId; } //
    public void setInvoiceDetailId(int invoiceDetailId) { this.invoiceDetailId = invoiceDetailId; } //

    public int getInvoiceId() { return invoiceId; } //
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; } //

    public int getCarId() { return carId; } //
    public void setCarId(int carId) { this.carId = carId; } //

    public int getQuantity() { return quantity; } //
    public void setQuantity(int quantity) { this.quantity = quantity; } //

    public double getPriceAtSale() { return priceAtSale; } //
    public void setPriceAtSale(double priceAtSale) { this.priceAtSale = priceAtSale; } //

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}