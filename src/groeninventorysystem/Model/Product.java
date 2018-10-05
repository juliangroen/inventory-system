package groeninventorysystem.Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    private final ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private final IntegerProperty productID = new SimpleIntegerProperty(0);
    private final StringProperty name = new SimpleStringProperty("");
    private final DoubleProperty price = new SimpleDoubleProperty(0.0);
    private final IntegerProperty inStock = new SimpleIntegerProperty(0);
    private final IntegerProperty min = new SimpleIntegerProperty(0);
    private final IntegerProperty max = new SimpleIntegerProperty(0);

    public Product() {

    }

    public Product(int productID, String name, double price, int inStock, int min, int max) {
        setProductID(productID);
        setName(name);
        setPrice(price);
        setInStock(inStock);
        setMin(min);
        setMax(max);
    }

    public final int getProductID() {
        return productID.get();
    }

    public final void setProductID(int value) {
        productID.set(value);
    }

    public IntegerProperty productIDProperty() {
        return productID;
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

    public void addAssociatedPart(Part part) {

        associatedParts.add(part);
        
    }

    public boolean removeAssociatedPart(int partID) {

        try {

            associatedParts.forEach((p) -> {

                if (p.getPartID() == partID) {

                    associatedParts.remove(p);

                }

            });

            return true;

        } catch (Exception ex) {

            return false;

        }

    }

    public Part lookupAssociatedPart(int partID) {

        // Set int to negetive to ensure exception is thrown.
        int index = -1;

        try {

            for (Part part : associatedPartsProperty()) {
                
                if(part.getPartID() == partID) {

                    index = associatedPartsProperty().indexOf(part);

                }

            }

            return associatedPartsProperty().get(index);

        } catch (ArrayIndexOutOfBoundsException ex) {

            //return null to trigger if statement in MainScreenController
            return null;

        }

    }

    // Addiitonal method to return the associatedPart list.

    public ObservableList<Part> associatedPartsProperty() {

        return associatedParts;

    }

}
