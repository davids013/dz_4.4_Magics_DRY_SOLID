package product;

import logger.Logger;
import logger.TxtLogger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductList implements Downloadable {
    private List<Product> products;

    public ProductList() {
        products = new ArrayList<>();
    }

    public ProductList(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ProductList setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public boolean add(Product product) {
        return !products.contains(product) && products.add(product);
    }

    public Product get(int index) {
        return products.get(index);
    }

    public boolean remove(Product product) {
        return products.contains(product) && products.remove(product);
    }

    public int size() {
        return products.size();
    }

    public List<Product> filter(String s) {
        return products.stream().filter(x -> x.toString().contains(s)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String head : Product.getHeader()) {
            sb.append(head + ", ");
        }
        sb.append("\r\n");
        for (Product product : products) {
            sb
                    .append(product.toString())
                    .append("\r\n");
        }
        return sb.toString().replace(", ", "\t");
    }

    private void sort(Comparator comparator) {
        products.sort(comparator);
    }

    public ProductList sortByTitle(boolean isAscending) {
        if ((isAscending)) {
            sort((Comparator<Product>) (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        } else {
            sort((Comparator<Product>) (o1, o2) -> o2.getTitle().compareTo(o1.getTitle()));
        }
        return this;
    }

    public ProductList sortByPrice(boolean isAscending) {
        if ((isAscending)) {
            sort((Comparator<Product>) (o1, o2) -> Integer.compare(o1.getPrice(), o2.getPrice()));
        } else {
            sort((Comparator<Product>) (o1, o2) -> Integer.compare(o2.getPrice(), o1.getPrice()));
        }
        return this;
    }

    @Override
    public boolean download() {
//        sOlid
        Logger logger = new
//                ConsoleLogger();
                TxtLogger("Catalogue.txt");
//                CsvLogger();
        return logger.log(toString());
    }
}
