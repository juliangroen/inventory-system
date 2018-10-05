package groeninventorysystem.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Part {

    private final IntegerProperty partID = new SimpleIntegerProperty(0);
    private final StringProperty name = new SimpleStringProperty("");
    private final DoubleProperty price = new SimpleDoubleProperty(0.0);
    private final IntegerProperty inStock = new SimpleIntegerProperty(0);
    private final IntegerProperty min = new SimpleIntegerProperty(0);
    private final IntegerProperty max = new SimpleIntegerProperty(0);

    public Part () {

    }

    public Part(int partID, String name, double price, int inStock, int min, int max) {
        setPartID(partID);
        setName(name);
        setPrice(price);
        setInStock(inStock);
        setMin(min);
        setMax(max);
    }

    public final int getPartID() {
        return partID.get();
    }

    public final void setPartID(int value) {
        partID.set(value);
    }

    public IntegerProperty partIDProperty() {
        return partID;
    }

    public final String getName() {
        return name.get();
    }

    public final void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public final double getPrice() {
        return price.get();
    }

    public final void setPrice(double value) {
        price.set(value);
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public final int getInStock() {
        return inStock.get();
    }

    public final void setInStock(int value) {
        inStock.set(value);
    }

    public IntegerProperty inStockProperty() {
        return inStock;
    }

    public final int getMin() {
        return min.get();
    }

    public final void setMin(int value) {
        min.set(value);
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public final int getMax() {
        return max.get();
    }

    public final void setMax(int value) {
        max.set(value);
    }

    public IntegerProperty maxProperty() {
        return max;
    }

}