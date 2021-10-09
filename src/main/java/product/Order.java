package product;

import logger.CsvLogger;
import logger.Logger;

import java.util.Map;

public class Order extends Cart {
    private static int id;

    public Order(Cart cart) {
        super(new Cart(cart));
        id++;
    }

    @Override
    public boolean download() {
        Logger logger = new
//                ConsoleLogger();
//                TxtLogger();
                CsvLogger("order_" + id + ".csv");
        return logger.log(toString());
    }

    public void delete() {
        if (super.getCart() != null) {
            clear();
            if (id > 0) id--;
        }
    }

    @Override
    public String toString() {
        Map<Product, Integer> cart = super.getCart();
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
                .append("Стоимость заказа,руб, ")
                .append(getPrice())
                .append("\r\n");
        return sb.toString().replace(", ", "\t");
    }
}
