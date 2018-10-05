package groeninventorysystem.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InhousePart extends Part {

    private final IntegerProperty machineID = new SimpleIntegerProperty(0);

    public InhousePart() {
    }

    public InhousePart(int partID, String name, double price, int inStock, int min, int max, int machineID) {

        super(partID, name, price, inStock, min, max);

        setMachineID(machineID);

    }

    public final int getMachineID() {
        return machineID.get();
    }

    public final void setMachineID(int value) {
        machineID.set(value);
    }

    public IntegerProperty machineIDProperty() {
        return machineID;
    }

}
