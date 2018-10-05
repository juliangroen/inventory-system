package groeninventorysystem.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OutsourcedPart extends Part {

    private final StringProperty companyName = new SimpleStringProperty("");

    public OutsourcedPart() {
    }

    public OutsourcedPart(int partID, String name, double price, int inStock, int min, int max, String companyName) {

        super(partID, name, price, inStock, min, max);

        setCompanyName(companyName);
    }

    public OutsourcedPart (String companyName){
        setCompanyName(companyName);
    }

    public final String getCompanyName() {
        return companyName.get();
    }

    public final void setCompanyName(String value) {
        companyName.set(value);
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

}
