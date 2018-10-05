package groeninventorysystem.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private final ObservableList<Product> products = FXCollections.observableArrayList();
    private final ObservableList<Part> allParts = FXCollections.observableArrayList();

    public ObservableList<Product> getProducts() {
        return products;
    }

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public void addProduct(Product product) {

        products.add(product);

    }

    public boolean removeProduct(Product product) {

        try {

            products.remove(products.indexOf(product));
            return true;

        } catch (Exception ex) {
            
            return false;

        }

    }

    public Product lookupProduct(int productID) {

        int index = -1;

        try {

            for (Product product : products) {
                
                if(product.getProductID() == productID) {

                    index = products.indexOf(product);

                }

            }

            return products.get(index);

        } catch (ArrayIndexOutOfBoundsException ex) {

            return null;

        }

    }

    public void updateProduct(int productID, Product product) {


        products.forEach((p) -> {

            if (p.getProductID() == productID) {

                int currentIndex = products.indexOf(p);

                products.set(currentIndex, product);
            }

        });


    }

    public void addPart(Part part) {
        allParts.add(part);
    }

    public boolean deletePart(Part part) {
        
        try {

            allParts.remove(allParts.indexOf(part));
            return true;

        } catch (Exception ex) {

            System.out.println(ex);
            
            return false;

        }

    }

    public Part lookupPart(int partID) {

        int index = -1;

        try {

            for (Part part : allParts) {
                
                if(part.getPartID() == partID) {

                    index = allParts.indexOf(part);

                }

            }

            return allParts.get(index);

        } catch (ArrayIndexOutOfBoundsException ex) {

            return null;

        }

    }

    public void updatePart(int partID, Part part) {


        allParts.forEach((p) -> {

            if (p.getPartID() == partID) {

                int currentIndex = allParts.indexOf(p);

                allParts.set(currentIndex, part);

            }

        });

    }

}