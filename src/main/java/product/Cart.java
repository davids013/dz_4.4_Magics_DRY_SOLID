package product;

import logger.CsvLogger;
import logger.Logger;

import java.util.*;

public class Cart implements Downloadable {
    private Map<Product, Integer> cart;

    public Cart() {
        cart = new TreeMap<Product, Integer>();
    }

    public Cart(Cart cart) {
        this.cart = new TreeMap<Product, Integer>(cart.getCart());
    }

    public final Map<Product, Integer> getCart() {
        return new TreeMap<Product, Integer>(cart);
    }

    public final Cart setCart(Map<Product, Integer> cart) {
        this.cart = new TreeMap<Product, Integer>(cart);
        return this;
    }

    public final Cart setCart(Cart cart) {
        this.cart = cart.getCart();
        return this;
    }

    public final Cart add(Product product) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + 1);
        } else cart.put(product, 1);
        return this;
    }

    public final Cart remove(Product product) {
        if (cart.containsKey(product)) {
            if (cart.get(product) > 1) {
                cart.put(product, cart.get(product) - 1);
            } else cart.remove(product);
        }
        return this;
    }

    public final Cart removeAll(Product product) {
        if (cart.containsKey(product)) cart.remove(product);
        return this;
    }

    public final int getPrice() {
        int price = 0;
        for (Product p : cart.keySet()) price += p.getPrice() * cart.get(p);
        return price;
    }

    @Override
    public boolean download() {
//        soliD
        Logger logger = new
//                ConsoleLogger();
//                TxtLogger();
                CsvLogger("YourCart.csv");
        return logger.log(toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String head : Product.getHeader()) {
            sb.append(head + ", ");
        }
        sb
                .append(String.format("%-15s", "В корзине,шт"))
                .append(String.format(", %-10s", "Итого,руб"))
                .append("\r\n");
        for (Product product : cart.keySet()) {
            sb
                    .append(product.toString())
                    .append(String.format(", %-15s", cart.get(product)))
                    .append(", ")
                    .append(cart.get(product) * product.getPrice())
                    .append("\r\n");
        }
        sb
                .append("Стоимость корзины,руб, ")
                .append(getPrice())
                .append("\r\n");
        return sb.toString().replace(", ", "\t");
    }

    public final void clear() {
        cart = null;
        System.gc();
    }
}
