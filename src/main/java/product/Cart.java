package product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public Cart setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public Cart sort(Comparator comparator) {
        products.sort(comparator);
        return this;
    }

    public Cart add(Product product) {
        if (!products.contains(product)) products.add(product);
        return this;
    }

    public Product get(int index) {
        return products.get(index);
    }

    public Cart remove(Product product) {
        products.remove(product);
        return this;
    }

    public int size() {
        return products.size();
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
