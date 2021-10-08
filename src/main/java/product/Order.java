package product;

import logger.CsvLogger;
import logger.Logger;

public class Order extends Cart {
    private static int id;

    public Order(Cart cart) {
        super(cart);
        id++;
    }

    @Override
    public boolean download() {
//        sOlid
        Logger logger = new
//                ConsoleLogger();
//                TxtLogger();
                CsvLogger("order_" + id + ".csv");
        return logger.log(toString());
    }
}
