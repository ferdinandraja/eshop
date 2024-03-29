package id.ac.ui.cs.advprog.eshop.repository;


import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }
    public Product edit(Product product, String identification) {
        for (int i=0; i < productData.size(); i++) {
            Product currentItem = productData.get(i);
            if (currentItem.getProductId().equals(identification)) {
                return productData.set(i, product);
            }
        }
        // Product not found
        return null;
    }


    public Iterator<Product> findAll() {
        return productData.iterator();
    }
    public boolean delete(Product product) {
        return productData.remove(product);
    }
}