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
        this.cart = cart.getCart();
    }

    public Map<Product, Integer> getCart() {
        return cart;
    }

    public Cart setCart(Map<Product, Integer> cart) {
        this.cart = cart;
        return this;
    }

    public Cart setCart(Cart cart) {
        this.cart = cart.getCart();
        return this;
    }

    public Cart add(Product product) {
        if (cart.containsKey(product)) {
            cart.put(product, cart.get(product) + 1);
        } else cart.put(product, 1);
        return this;
    }

    public Cart remove(Product product) {
        if (cart.containsKey(product)) {
            if (cart.get(product) > 1) {
                cart.put(product, cart.get(product) - 1);
            } else cart.remove(product);
        }
        return this;
    }

    public Cart removeAll(Product product) {
        if (cart.containsKey(product)) cart.remove(product);
        return this;
    }

    public int getPrice() {
        int price = 0;
        for (Product p : cart.keySet()) price += p.getPrice() * cart.get(p);
        return price;
    }

    @Override
    public boolean download() {
//        sOlid
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
                .append("В корзине,шт")
                .append(", Итого,руб")
                .append("\r\n");
        for (Product product : cart.keySet()) {
            sb
                    .append(product.toString())
                    .append(", ")
                    .append(cart.get(product))
                    .append("            ")
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
}
