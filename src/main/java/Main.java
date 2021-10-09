import product.Cart;
import product.Order;
import product.Product;
import product.ProductList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("""
                \n\tМодуль 4 "Шаблоны проектирования\"
                \tДомашнее задание к лекции 4.4 "Magics, DRY, SOLID\"
                \tЗадача: Магазин
                """);
//        Magic
        final int PRODUCT_LIST_SIZE = 20;

        ProductList prList = new ProductList();
//        За неимением фантазии продукты созданы рандомным заполнением c жуткими названиями
        for (int i = 0; i < PRODUCT_LIST_SIZE; i++)
            prList.add(Product.getRandomProduct());
        Cart cart = new Cart();

//        Фильтрация продукции по ключевому слову
        List<Product> foodList = prList.filter("еда");
        for (Product item : foodList) {
            cart.add(item);
        }

//        Сортировка продукции по уменьшению стоимости
        prList.sortByPrice(false);
        System.out.println("\u001b[32m" + prList);
        System.out.println("\u001b[36m" + cart);
//        Скачивание каталога продукции в файл
        prList.download();
//        Скачивание текущей корзины в файл
        cart.download();

        System.out.println("\u001b[m" + "\tЗаказы");
//        Добавление ещё одного продукта в корзину
        if (cart.getCart().size() > 0)
            cart.add((Product) cart.getCart().keySet().toArray()[0]);
//        Формируем заказа из корзины
        Cart order = new Order(new Cart(cart));
        System.out.println("\n" + "\u001b[33m" + order);
        order.download();
    }
}
