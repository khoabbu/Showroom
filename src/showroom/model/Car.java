package showroom.model;

public class Car {
    private int id;
    private String carName;
    private String manufacturer;
    private int yearOfManufacture;
    private String color;
    private String modelType;
    private double sellingPrice;
    private int quantityInStock;
    private String description;
    private String imagePath;

    // Getter & Setter cho imagePath
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Constructor mặc định
    public Car() {
    }

    // Constructor đầy đủ bao gồm imagePath
    public Car(int id, String carName, String manufacturer, int yearOfManufacture, String color, String modelType, double sellingPrice, int quantityInStock, String description, String imagePath) {
        this.id = id;
        this.carName = carName;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.modelType = modelType;
        this.sellingPrice = sellingPrice;
        this.quantityInStock = quantityInStock;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString() dùng để test
    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", sellingPrice=" + sellingPrice +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
