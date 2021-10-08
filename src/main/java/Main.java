import product.Cart;
import product.Order;
import product.Product;
import product.ProductList;

public class Main {
    public static void main(String[] args) {
//        Magic
        final int PRODUCT_LIST_SIZE = 20;
        final int CART_SIZE = 5;

        ProductList prList = new ProductList();
        for (int i = 0; i < PRODUCT_LIST_SIZE; i++)
            prList.add(Product.getRandomProduct());
        Cart cart = new Cart();
//        for (int i = 0; i < CART_SIZE; i++)
//            cart.add(Product.getRandomProduct());

        cart.add(prList.get(1));
        cart.add(prList.get(2));
        cart.add(prList.get(5));
        cart.add(prList.get(2));
        cart.add(prList.get(2));
        cart.add(prList.get(1));

//        Сортировка продукции по уменьшению стоимости
        prList.sortByPrice(false);
        System.out.println("\u001b[32m" + prList);
//        Фильтрация продукции по ключевому слову
        for (Product p : prList.filter("серый")) {
            System.out.println("\u001b[35m" + p.toString());
        }
        System.out.println("\n" + "\u001b[36m" + cart);
//        Скачивание каталога продукции в файл
        prList.download();
//        Скачивание текущей корзины в файл
        cart.download();

        Cart order1 = new Order(cart);
        System.out.println("\n" + "\u001b[33m" + order1);
        cart.add(prList.get(10));
        Cart order2 = new Order(cart);
        System.out.println("\n" + "\u001b[34m" + order2);
        System.out.println("\n" + "\u001b[33m" + order1);

        //TODO: Не переписывать заказ при изменении корзины (сделать ее Cloneable)
    }
}
